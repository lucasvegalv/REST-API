package com.example.demo.mappers;

import com.example.demo.dtos.OrderItemDTO;
import com.example.demo.entities.OrderItemEntity;

public class OrderItemMapper {

    public static OrderItemDTO toOrderItemDTO(OrderItemEntity orderItemEntity) {

        if(orderItemEntity == null) return null;

        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(orderItemEntity.getId());
        orderItemDTO.setOrderId(orderItemEntity.getOrderId());
        orderItemDTO.setQuantity(orderItemEntity.getQuantity());
        return orderItemDTO;
    }

    public static OrderItemEntity toOrderItemEntity(OrderItemDTO orderItemDTO) {
        if(orderItemDTO == null) return null;

        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setId(orderItemDTO.getId());
        orderItemEntity.setOrderId(orderItemDTO.getOrderId());
        orderItemEntity.setQuantity(orderItemDTO.getQuantity());
        return orderItemEntity;
    }

}
