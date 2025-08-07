package com.example.rewardservice.repository;

import com.example.rewardservice.entity.RewardAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RewardActionRepository extends JpaRepository<RewardAction, Long> {
    Optional<RewardAction> findByOrderId(String orderId);
}
