// File: com/app/dto/CartRequest.java
package com.app.dto;

import lombok.*;

import java.util.List;

/**
 * DTO representing a cart for reward evaluation.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartRequest {
    private Long userId;
    private List<ItemDTO> items;
    private double totalAmount;
    private ProfileDTO profileData;
}
