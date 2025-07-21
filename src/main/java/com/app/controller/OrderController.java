package com.app.controller;

import com.app.dto.OrderRequest;
import com.app.dto.OrderResponse;
import com.app.dto.RewardResult;
import com.app.model.Order;
import com.app.model.User;
import com.app.service.OrderService;
import com.app.service.RewardsService;
import com.app.service.UserService;
import jakarta.validation.Valid;
lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final RewardsService rewardsService;
    private final UserService userService;

    /**
     * Place an order, evaluate rewards, save order, and update user.
     */
    @PostMapping
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        try {
            // 1. Evaluate rewards/discounts for the order
            RewardResult rewardResult = rewardsService.evaluateRewards(orderRequest);

            // 2. Save the order (with applied rewards/discounts)
            Order savedOrder = orderService.saveOrder(orderRequest, rewardResult);

            // 3. Update the user (e.g., loyalty points, tier, etc.)
            User updatedUser = userService.updateUserAfterOrder(orderRequest.getUserId(), savedOrder, rewardResult);

            // 4. Build response DTO
            OrderResponse response = OrderResponse.builder()
                    .orderId(savedOrder.getId())
                    .userId(updatedUser.getId())
                    .totalAmount(savedOrder.getTotalAmount())
                    .discountApplied(rewardResult.getDiscountAmount())
                    .finalAmount(savedOrder.getFinalAmount())
                    .rewards(rewardResult.getRewards())
                    .status(savedOrder.getStatus())
                    .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException ex) {
            // For business validation errors (e.g., user/order not found, invalid state)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ErrorResponse("Bad Request", ex.getMessage())
            );
        } catch (Exception ex) {
            // For unexpected errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ErrorResponse("Internal Server Error", "An unexpected error occurred.")
            );
        }
    }

    // Optionally, handle validation errors globally or locally
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMsg = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .reduce((msg1, msg2) -> msg1 + "; " + msg2)
                .orElse("Validation error");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponse("Validation Failed", errorMsg)
        );
    }

    // ErrorResponse DTO (can be moved to a common package)
    public static class ErrorResponse {
        private final String error;
        private final String message;

        public ErrorResponse(String error, String message) {
            this.error = error;
            this.message = message;
        }

        public String getError() {
            return error;
        }

        public String getMessage() {
            return message;
        }
    }
}
