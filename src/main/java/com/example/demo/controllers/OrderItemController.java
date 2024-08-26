package com.example.demo.controllers;

import com.example.demo.dtos.OrderItemDTO;
import com.example.demo.services.OrderItemService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderitem")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping
    public ResponseEntity<List<OrderItemDTO>> getOrderItems() {
       List<OrderItemDTO> orderItems = orderItemService.readAllOrderItems();
        return ResponseEntity.ok(orderItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDTO> getOrderItemById(@PathVariable Integer id) {
        OrderItemDTO orderItemDTO = orderItemService.readOrderItemByID(id);
        return ResponseEntity.ok(orderItemDTO);
    }

    @PutMapping
    public ResponseEntity<OrderItemDTO> updateOrderItem(@PathVariable Integer id, @RequestBody OrderItemDTO orderItemDTO) {
        OrderItemDTO orderItem = orderItemService.updateOrderItem(id, orderItemDTO);
        return ResponseEntity.ok(orderItem);
    }

    @DeleteMapping
    public ResponseEntity<OrderItemDTO> deleteOrderItemById(@PathVariable Integer id) {
        orderItemService.deleteOrderItemByID(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping
    public ResponseEntity<OrderItemDTO> createOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
        orderItemService.createOrderItem(orderItemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
