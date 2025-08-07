package com.example.rewardservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartEvaluationRequestDto {
    private String userId;
    private List<CartItemDto> cartItems;
}
