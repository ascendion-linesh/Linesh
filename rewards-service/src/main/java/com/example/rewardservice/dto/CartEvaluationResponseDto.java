package com.example.rewardservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartEvaluationResponseDto {
    private String userId;
    private List<DiscountDto> discounts;
    private List<RewardDto> rewards;
}
