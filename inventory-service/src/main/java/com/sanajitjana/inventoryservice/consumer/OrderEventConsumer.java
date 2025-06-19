package com.sanajitjana.inventoryservice.consumer;

import com.sanajitjana.inventoryservice.model.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventConsumer {

    @KafkaListener(topics = "order-created", groupId = "inventory-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(OrderEvent orderEvent) {
        System.out.println("📦 Received order: " + orderEvent);
    }

}
