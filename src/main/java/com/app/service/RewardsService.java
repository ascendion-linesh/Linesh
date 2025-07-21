package com.app.service;

import com.app.dto.CartRequest;
import com.app.dto.RewardsResponse;
import com.app.dto.OrderRequest;
import com.app.dto.RewardResult;
import com.app.talonone.TalonOneClient;
lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service layer for managing rewards and discounts via Talon.One API.
 */
@Service
@RequiredArgsConstructor
public class RewardsService {

    private final TalonOneClient talonOneClient;

    /**
     * Evaluates the cart for personalized rewards and discounts using Talon.One.
     *
     * @param cartRequest the cart request DTO
     * @return RewardsResponse containing applicable rewards and discounts
     */
    @Transactional(readOnly = true)
    public RewardsResponse evaluateCartForRewards(CartRequest cartRequest) {
        // Update user profile in Talon.One
        talonOneClient.updateProfile(cartRequest.getUserId(), cartRequest.getProfileData());

        // Evaluate session for rewards/discounts
        RewardsResponse rewardsResponse = talonOneClient.evaluateSession(cartRequest);

        return rewardsResponse;
    }

    /**
     * Evaluates rewards/discounts for an order request.
     *
     * @param orderRequest the order request DTO
     * @return RewardResult containing discount and reward info
     */
    @Transactional(readOnly = true)
    public RewardResult evaluateRewards(OrderRequest orderRequest) {
        // Map OrderRequest to CartRequest if necessary
        CartRequest cartRequest = CartRequest.builder()
                .userId(orderRequest.getUserId())
                .items(orderRequest.getItems())
                .totalAmount(orderRequest.getTotalAmount())
                .profileData(orderRequest.getProfileData())
                .build();

        RewardsResponse rewardsResponse = evaluateCartForRewards(cartRequest);

        // Convert RewardsResponse to RewardResult
        return RewardResult.builder()
                .discountAmount(rewardsResponse.getDiscountAmount())
                .rewards(rewardsResponse.getRewards())
                .loyaltyUsed(rewardsResponse.isLoyaltyUsed())
                .build();
    }

    /**
     * Confirms loyalty point usage for a user via Talon.One.
     *
     * @param userId the user ID
     * @param total the total amount to confirm
     */
    @Transactional
    public void confirmLoyalty(String userId, double total) {
        talonOneClient.confirmLoyalty(userId, total);
    }
}
