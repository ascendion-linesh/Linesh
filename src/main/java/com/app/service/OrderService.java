package com.app.service;

import com.app.dto.OrderRequest;
import com.app.dto.RewardResult;
import com.app.model.Order;
import com.app.model.User;
import com.app.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service layer for managing order-related operations.
 */
@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserService userService;
    private final RewardsService rewardsService;
    private final OrderRepository orderRepository;

    /**
     * Saves an order after evaluating rewards/discounts and updates user statistics.
     *
     * @param orderRequest the order request DTO
     * @param rewardResult the result of reward/discount evaluation
     * @return the saved Order entity
     */
    @Transactional
    public Order saveOrder(OrderRequest orderRequest, RewardResult rewardResult) {
        // Retrieve user
        User user = userService.findUserById(orderRequest.getUserId()) != null
                ? userRepository().findById(orderRequest.getUserId()).orElseThrow(() -> new EntityNotFoundException("User not found"))
                : null;
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        // Calculate final amount after applying discount
        double discount = rewardResult != null ? rewardResult.getDiscountAmount() : 0.0;
        double totalAmount = orderRequest.getTotalAmount();
        double finalAmount = Math.max(0, totalAmount - discount);

        // Create Order entity
        Order order = Order.builder()
                .user(user)
                .items(orderRequest.getItems())
                .totalAmount(totalAmount)
                .discountApplied(discount)
                .finalAmount(finalAmount)
                .status("PLACED")
                .build();

        // Save order
        Order savedOrder = orderRepository.save(order);

        // Confirm loyalty point usage if applicable
        if (rewardResult != null && rewardResult.isLoyaltyUsed()) {
            rewardsService.confirmLoyalty(user.getId().toString(), finalAmount);
        }

        return savedOrder;
    }

    // Helper to access UserRepository for user lookup
    private com.app.repository.UserRepository userRepository() {
        // This assumes you have access to UserRepository, otherwise inject it directly
        throw new UnsupportedOperationException("UserRepository injection required");
    }
}
