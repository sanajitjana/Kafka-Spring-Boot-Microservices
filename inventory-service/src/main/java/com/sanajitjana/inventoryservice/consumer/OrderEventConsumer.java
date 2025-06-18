package com.sanajitjana.inventoryservice.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventConsumer {

    @KafkaListener(topics = "order-created", groupId = "inventory-group")
    public void consume(String event) {
        System.out.println("📦 Received order event in inventory-service: " + event);
    }

}
