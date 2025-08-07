package com.example.orderservice.dto;

import lombok.Builder;
import lombok.Data;
import java.time.Instant;

@Data
@Builder
public class OrderEvent {
    private Long orderId;
    private Long userId;
    private Double totalAmount;
    private Double discount;
    private Instant createdAt;
}
