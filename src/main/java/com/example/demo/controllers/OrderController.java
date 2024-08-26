package com.example.demo.controllers;

import com.example.demo.dtos.OrderDTO;
import com.example.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Integer id) {
        OrderDTO orderDTO = orderService.readProductByID(id);
        return ResponseEntity.ok().body(orderDTO);
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orderDTOList = orderService.readAllOrders();
        return ResponseEntity.ok().body(orderDTOList);
    }

    @PutMapping
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Integer id, @RequestBody OrderDTO orderDTO) {
        OrderDTO updatedUser = orderService.updateOrder(id, orderDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        orderService.createOrder(orderDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(orderDTO.getId())
                .toUri();

        return ResponseEntity.created(location).body(orderDTO);
    }

    @DeleteMapping
    public void deleteOrderById(@PathVariable Integer id) {
        orderService.deleteOrderByID(id);
    }

}
