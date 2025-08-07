package com.example.rewardservice.controller;

import com.example.rewardservice.dto.CartEvaluationRequestDto;
import com.example.rewardservice.dto.CartEvaluationResponseDto;
import com.example.rewardservice.service.RewardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rewards")
public class RewardsController {

    @Autowired
    private RewardsService rewardsService;

    @Operation(summary = "Evaluate cart for discounts and rewards", description = "Evaluates the cart session and returns applicable discounts and rewards.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Evaluation successful", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CartEvaluationResponseDto.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping(value = "/evaluate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartEvaluationResponseDto> evaluateCart(@org.springframework.web.bind.annotation.RequestBody CartEvaluationRequestDto requestDto) {
        CartEvaluationResponseDto response = rewardsService.evaluateCart(requestDto);
        return ResponseEntity.ok(response);
    }
}
