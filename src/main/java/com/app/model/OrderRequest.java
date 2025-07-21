// File: com/app/dto/OrderRequest.java
package com.app.dto;

import lombok.*;
import java.util.List;

/**
 * DTO for placing an order.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    private Long userId;
    private List<ItemDTO> items;
    private double totalAmount;
    private ProfileDTO profileData; // For Talon.One profile sync
}
