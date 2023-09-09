package com.coffeeshop.controller;


import com.coffeeshop.dto.OrderDTO;
import com.coffeeshop.dto.OrderResponse;
import com.coffeeshop.model.Order;
import com.coffeeshop.service.ProcessOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

    private final ProcessOrderService processOrderService;

    @PostMapping(value ="/place-order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderDTO orderRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(processOrderService.placeOrder(orderRequest.getItems(), orderRequest.getShopId(), orderRequest.getCustomerId()));
    }
}
