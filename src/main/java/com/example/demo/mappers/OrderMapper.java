package com.example.demo.mappers;

import com.example.demo.dtos.OrderDTO;
import com.example.demo.entities.OrderEntity;

public class OrderMapper {

    public static OrderDTO toOrderDTO(OrderEntity order) {

        if(order == null) {
            return null;
        };

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setTotal(order.getTotal());
        orderDTO.setUserId(order.getUserId());
        return orderDTO;
    }

    public static OrderEntity toOrderEntity(OrderDTO orderDTO) {
        if(orderDTO == null) {
            return null;
        }

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderDTO.getId());
        orderEntity.setOrderDate(orderDTO.getOrderDate());
        orderEntity.setTotal(orderDTO.getTotal());
        orderEntity.setUserId(orderDTO.getUserId());
        return orderEntity;
    }

}
