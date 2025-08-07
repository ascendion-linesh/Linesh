package com.example.rewardservice.talonone;

import com.example.rewardservice.dto.*;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class TalonOneClient {
    // TODO: Replace with real Talon.One API integration
    public CartEvaluationResponseDto evaluateCart(CartEvaluationRequestDto requestDto) {
        // Simulate Talon.One evaluation logic
        DiscountDto discount = new DiscountDto("CAMP123", 20.0);
        RewardDto reward = new RewardDto("loyaltyPoints", 50);
        return new CartEvaluationResponseDto(
                requestDto.getUserId(),
                Collections.singletonList(discount),
                Collections.singletonList(reward)
        );
    }
}
