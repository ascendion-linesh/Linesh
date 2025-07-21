package com.app.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Order entity representing a purchase transaction.
 */
@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many orders belong to one user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // One order has many items
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;

    private double totalAmount;

    private double discountApplied;

    private double finalAmount;

    private String status; // e.g., PLACED, CANCELLED, etc.

    private String rewardDetails; // JSON or stringified details of applied rewards
}
