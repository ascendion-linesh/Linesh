package com.example.customerinventory.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomerInventoryController {
    @GetMapping("/api/v1/customer_profiles/{integrationId}/inventory")
    public ResponseEntity<?> getCustomerInventory(
            @PathVariable String integrationId,
            @RequestParam(required = false, defaultValue = "false") boolean profile,
            @RequestParam(required = false, defaultValue = "false") boolean referrals,
            @RequestParam(required = false, defaultValue = "false") boolean coupons,
            @RequestParam(required = false, defaultValue = "false") boolean loyalty,
            @RequestParam(required = false, defaultValue = "false") boolean giveaways,
            @RequestParam(required = false, defaultValue = "false") boolean achievements,
            @RequestHeader(value = "Authorization", required = true) String apiKey
    ) {
        // Simulate API key validation (api_key_v1)
        if (apiKey == null || !apiKey.startsWith("api_key_v1")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("401 Unauthorized - Invalid API key");
        }
        // Simulated response structure
        Map<String, Object> response = new HashMap<>();
        if (profile) {
            Map<String, Object> profileMap = new HashMap<>();
            profileMap.put("id", 6);
            profileMap.put("created", "2020-02-07T08:15:22Z");
            profileMap.put("integrationId", integrationId);
            profileMap.put("attributes", new HashMap<>());
            profileMap.put("accountId", 31);
            profileMap.put("closedSessions", 3);
            profileMap.put("totalSales", 299.99);
            profileMap.put("loyaltyMemberships", new Object[]{});
            profileMap.put("audienceMemberships", new Object[]{});
            profileMap.put("lastActivity", "2020-02-08T14:15:20Z");
            profileMap.put("sandbox", false);
            response.put("profile", profileMap);
        }
        if (loyalty) {
            Map<String, Object> loyaltyMap = new HashMap<>();
            loyaltyMap.put("cards", new Object[]{});
            loyaltyMap.put("programs", new HashMap<>());
            response.put("loyalty", loyaltyMap);
        }
        if (referrals) {
            response.put("referrals", new Object[]{new HashMap<>()});
        }
        if (coupons) {
            response.put("coupons", new Object[]{new HashMap<>()});
        }
        if (giveaways) {
            response.put("giveaways", new Object[]{new HashMap<>()});
        }
        if (achievements) {
            response.put("achievements", new Object[]{new HashMap<>()});
        }
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404 Not found");
        }
        return ResponseEntity.ok(response);
    }
}
