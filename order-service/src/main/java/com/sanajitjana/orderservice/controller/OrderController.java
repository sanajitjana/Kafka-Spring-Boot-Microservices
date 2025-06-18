package com.sanajitjana.orderservice.controller;

import com.sanajitjana.orderservice.model.OrderEvent;
import com.sanajitjana.orderservice.service.OrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderProducer producer;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderEvent orderEvent) {
        orderEvent.setStatus("PENDING");
        producer.sendOrder(orderEvent);
        return ResponseEntity.ok("Order Sent to Kafka");
    }

}
