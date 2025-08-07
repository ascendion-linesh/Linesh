package com.example.orderservice.talonone;

import com.example.orderservice.dto.RewardsRequest;
import com.example.orderservice.dto.RewardsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "rewards-service", url = "http://localhost:8082")
public interface RewardsServiceClient {
    @PostMapping("/rewards/calculate-discount")
    RewardsResponse calculateDiscount(@RequestBody RewardsRequest request);
}
