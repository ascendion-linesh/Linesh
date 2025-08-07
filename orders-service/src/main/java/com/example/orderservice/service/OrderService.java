package com.example.orderservice.service;

import com.example.orderservice.dto.*;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.talonone.RewardsServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserServiceClient userServiceClient;
    private final RewardsServiceClient rewardsServiceClient;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        // 1. Validate user exists
        UserDto user = userServiceClient.getUserById(orderRequest.getUserId());
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        // 2. Calculate discount
        RewardsRequest rewardsRequest = new RewardsRequest();
        rewardsRequest.setUserId(orderRequest.getUserId());
        rewardsRequest.setCartItems(orderRequest.getCartItems());
        RewardsResponse rewardsResponse = rewardsServiceClient.calculateDiscount(rewardsRequest);
        double discount = rewardsResponse != null && rewardsResponse.getDiscount() != null ? rewardsResponse.getDiscount() : 0.0;
        // 3. Calculate total amount (simulate sum of quantities * 50.0 per item for demo)
        double totalAmount = orderRequest.getCartItems().stream().mapToDouble(i -> i.getQuantity() * 50.0).sum();
        // 4. Save order
        Order order = Order.builder()
                .userId(orderRequest.getUserId())
                .cartItems(orderRequest.getCartItems())
                .totalAmount(totalAmount)
                .discount(discount)
                .build();
        Order savedOrder = orderRepository.save(order);
        // 5. Publish event to Kafka
        OrderEvent event = OrderEvent.builder()
                .orderId(savedOrder.getId())
                .userId(savedOrder.getUserId())
                .totalAmount(savedOrder.getTotalAmount())
                .discount(savedOrder.getDiscount())
                .createdAt(savedOrder.getCreatedAt())
                .build();
        kafkaTemplate.send("orders", event);
        // 6. Return response
        return OrderResponse.builder()
                .orderId(savedOrder.getId())
                .userId(savedOrder.getUserId())
                .cartItems(savedOrder.getCartItems())
                .totalAmount(savedOrder.getTotalAmount())
                .discount(savedOrder.getDiscount())
                .createdAt(savedOrder.getCreatedAt())
                .build();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
}
