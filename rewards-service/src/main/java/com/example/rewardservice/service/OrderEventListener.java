package com.example.rewardservice.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderEventListener {

    @KafkaListener(topics = "order-events", groupId = "rewards-service-group")
    public void listenOrderEvents(ConsumerRecord<String, String> record) {
        log.info("Received order event: {}", record.value());
        // TODO: Parse event, confirm loyalty actions, ensure idempotency
        // Example: processLoyaltyActions(event);
    }
}
