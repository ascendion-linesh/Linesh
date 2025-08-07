package com.example.rewardservice.service;

import com.example.rewardservice.dto.*;
import com.example.rewardservice.talonone.TalonOneClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class RewardsService {

    @Autowired
    private TalonOneClient talonOneClient;

    public CartEvaluationResponseDto evaluateCart(CartEvaluationRequestDto requestDto) {
        // Call Talon.One API to evaluate cart and get discounts/rewards
        return talonOneClient.evaluateCart(requestDto);
    }
}
