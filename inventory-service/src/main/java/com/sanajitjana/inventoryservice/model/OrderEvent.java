package com.sanajitjana.inventoryservice.model;

import lombok.Data;

@Data
public class OrderEvent {
    private String orderId;
    private String userId;
    private String status;
}
