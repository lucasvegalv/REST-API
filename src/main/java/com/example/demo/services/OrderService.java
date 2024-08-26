package com.example.demo.services;

import com.example.demo.dtos.OrderDTO;
import com.example.demo.dtos.ProductDTO;
import com.example.demo.entities.OrderEntity;
import com.example.demo.entities.ProductEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.mappers.OrderMapper;
import com.example.demo.repositories.IOrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final IOrderRepository orderRepository;

    @Autowired
    public OrderService(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // CRUD Methods

    // CREATE
    public void createOrder(OrderDTO orderDTO) {
        OrderEntity orderEntity = OrderMapper.toOrderEntity(orderDTO);
        orderRepository.save(orderEntity);
    }

    // READ BY ID
    public OrderDTO readProductByID(Integer id) {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(id);

        if (orderEntityOptional.isPresent()) {
            OrderEntity orderEntity = orderEntityOptional.get();
            OrderDTO orderDTO = OrderMapper.toOrderDTO(orderEntity);
            return orderDTO;
        } else {
            throw new EntityNotFoundException("Order not found with id: " + id);
        }

    }

    // READ ALL
    public List<OrderDTO> readAllOrders() {
        List<OrderEntity> orderEntityList = orderRepository.findAll();
        List<OrderDTO> orderDTOList = orderEntityList.stream()
                .map(OrderMapper::toOrderDTO)
                .collect(Collectors.toList());

        return orderDTOList;
    }

    // UPDATE
    public OrderDTO updateOrder(Integer id, OrderDTO orderDTO) {

        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(id);
        OrderDTO updatedOrderDTO = null;

        if(orderEntityOptional.isPresent()) {
            OrderEntity orderEntity = orderEntityOptional.get();

            orderEntity.setUserId(orderDTO.getUserId());
            orderEntity.setOrderDate(orderDTO.getOrderDate());
            orderEntity.setTotal(orderDTO.getTotal());

            OrderEntity updatedOrder = orderRepository.save(orderEntity);
            updatedOrderDTO = OrderMapper.toOrderDTO(updatedOrder);
        }
        return updatedOrderDTO;
    }

    // DELETE BY ID
    public void deleteOrderByID(Integer id) {
        orderRepository.deleteById(id);
    }

    // DELETE ALL
    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }


}
