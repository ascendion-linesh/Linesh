// File: com/app/dto/RewardsResponse.java
package com.app.dto;

import lombok.*;

import java.util.List;

/**
 * DTO representing the response from Talon.One for rewards/discounts.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RewardsResponse {
    private double discountAmount;
    private List<RewardInfo> rewards;
    private boolean loyaltyUsed;
}
