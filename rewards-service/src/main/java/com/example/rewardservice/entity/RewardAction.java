package com.example.rewardservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reward_actions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RewardAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String actionType;

    @Column(nullable = false)
    private int points;
}
