package com.app.service;

import com.app.dto.UserDto;
import com.app.dto.UserUpdateRequest;
import com.app.model.User;
import com.app.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service layer for managing user-related operations.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Finds a user by their ID and returns a UserDto.
     * 
     * @param id the user ID
     * @return UserDto if found, otherwise null
     */
    @Transactional(readOnly = true)
    public UserDto findUserById(Long id) {
        return userRepository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    /**
     * Updates the user's totalOrders and totalSpent fields.
     *
     * @param id the user ID
     * @param updateRequest the request containing new values
     * @return updated UserDto if user exists, otherwise null
     */
    @Transactional
    public UserDto updateUserOrdersAndSpent(Long id, UserUpdateRequest updateRequest) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setTotalOrders(updateRequest.getTotalOrders());
                    user.setTotalSpent(updateRequest.getTotalSpent());
                    User saved = userRepository.save(user);
                    return toDto(saved);
                })
                .orElse(null);
    }

    /**
     * Updates user statistics after an order is placed.
     *
     * @param userId the user ID
     * @param order the placed Order
     * @param rewardResult the reward/discount result
     * @return updated User entity
     */
    @Transactional
    public User updateUserAfterOrder(Long userId, com.app.model.Order order, com.app.dto.RewardResult rewardResult) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setTotalOrders(user.getTotalOrders() + 1);
        user.setTotalSpent(user.getTotalSpent() + order.getFinalAmount());
        // Optionally update loyalty points, tier, etc. based on rewardResult
        return userRepository.save(user);
    }

    /**
     * Converts a User entity to a UserDto.
     */
    private UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .totalOrders(user.getTotalOrders())
                .totalSpent(user.getTotalSpent())
                // Add other fields as necessary
                .build();
    }
}
