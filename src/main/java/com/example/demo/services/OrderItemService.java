package com.example.demo.services;

import com.example.demo.dtos.OrderItemDTO;
import com.example.demo.entities.OrderItemEntity;
import com.example.demo.mappers.OrderItemMapper;
import com.example.demo.repositories.IOrderItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderItemService {

    private final IOrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(IOrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    // CRUD Methods

    // CREATE
    public void createOrderItem(OrderItemDTO orderItemDTO) {
        OrderItemEntity orderItemEntity = OrderItemMapper.toOrderItemEntity(orderItemDTO);
        orderItemRepository.save(orderItemEntity);
    }

    // READ BY ID
    public OrderItemDTO readOrderItemByID(Integer id) {
        Optional<OrderItemEntity> orderItemEntityOptional = orderItemRepository.findById(id);

        // Controlamos el Optional
        if (orderItemEntityOptional.isPresent()) {
            OrderItemEntity orderItemEntity = orderItemEntityOptional.get();
            OrderItemDTO orderItemDTO = OrderItemMapper.toOrderItemDTO(orderItemEntity);
            return orderItemDTO;
        } else {
            throw new EntityNotFoundException("OrderItem not found with id: " + id);
        }

    }

    // READ ALL
    public List<OrderItemDTO> readAllOrderItems() {
        List<OrderItemEntity> orderItemEntityList = orderItemRepository.findAll();
        List<OrderItemDTO> orderItemDTOList = orderItemEntityList.stream()
                .map(OrderItemMapper::toOrderItemDTO)
                .collect(Collectors.toList());

        return orderItemDTOList;
    }

    // UPDATE
    public OrderItemDTO updateOrderItem(@PathVariable Integer id, OrderItemDTO orderItemDTO) {

        Optional<OrderItemEntity> orderItemEntityOptional = orderItemRepository.findById(id);
        OrderItemDTO updatedOrderItemDTO = null;

        if(orderItemEntityOptional.isPresent()) {
            OrderItemEntity orderItemEntity = orderItemEntityOptional.get();

            orderItemEntity.setOrderId(orderItemDTO.getOrderId());
            orderItemEntity.setPrice(orderItemDTO.getPrice());
            orderItemEntity.setQuantity(orderItemDTO.getQuantity());
            orderItemEntity.setProductId(orderItemDTO.getProductId());

            orderItemEntity = orderItemRepository.save(orderItemEntity);
            updatedOrderItemDTO = OrderItemMapper.toOrderItemDTO(orderItemEntity);
        }
        return updatedOrderItemDTO;
    }

    // DELETE BY ID
    public void deleteOrderItemByID(Integer id) {
        orderItemRepository.deleteById(id);
    }

    // DELETE ALL
    public void deleteAllOrderItems() {
        orderItemRepository.deleteAll();
    }
}
