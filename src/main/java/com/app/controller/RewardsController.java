package com.app.controller;

import com.app.dto.CartRequest;
import com.app.dto.RewardsResponse;
import com.app.service.RewardsService;
import jakarta.validation.Valid;
lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rewards")
@RequiredArgsConstructor
public class RewardsController {

    private final RewardsService rewardsService;

    /**
     * Evaluates the cart for personalized rewards and discounts.
     *
     * @param cartRequest the validated cart request payload
     * @return RewardsResponse containing applicable rewards and discounts
     */
    @PostMapping("/evaluate")
    public ResponseEntity<RewardsResponse> evaluateRewards(
            @Valid @RequestBody CartRequest cartRequest) {
        RewardsResponse response = rewardsService.evaluateCartForRewards(cartRequest);
        return ResponseEntity.ok(response);
    }

    /**
     * Handles validation errors for CartRequest.
     *
     * @param ex the MethodArgumentNotValidException
     * @return a response entity with error details and BAD_REQUEST status
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .reduce((msg1, msg2) -> msg1 + "; " + msg2)
                .orElse("Invalid request");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    /**
     * Handles generic exceptions and returns INTERNAL_SERVER_ERROR.
     *
     * @param ex the Exception
     * @return a response entity with error details and INTERNAL_SERVER_ERROR status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericExceptions(Exception ex) {
        // In production, log the exception with a logger
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred: " + ex.getMessage());
    }
}
