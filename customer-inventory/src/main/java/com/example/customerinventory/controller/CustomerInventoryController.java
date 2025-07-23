package com.example.customerinventory.controller;

import com.example.customerinventory.model.CustomerInventoryResponse;
import com.example.customerinventory.service.CustomerInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/customer_profiles")
public class CustomerInventoryController {

    private final CustomerInventoryService customerInventoryService;

    @Autowired
    public CustomerInventoryController(CustomerInventoryService customerInventoryService) {
        this.customerInventoryService = customerInventoryService;
    }

    @GetMapping("/{integrationId}/inventory")
    public ResponseEntity<?> getCustomerInventory(
            @PathVariable String integrationId,
            @RequestParam(required = false, defaultValue = "false") boolean profile,
            @RequestParam(required = false, defaultValue = "false") boolean referrals,
            @RequestParam(required = false, defaultValue = "false") boolean coupons,
            @RequestParam(required = false, defaultValue = "false") boolean loyalty,
            @RequestParam(required = false, defaultValue = "false") boolean giveaways,
            @RequestParam(required = false, defaultValue = "false") boolean achievements
    ) {
        if (integrationId == null || integrationId.isBlank()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("IntegrationId is required");
        }
        CustomerInventoryResponse response = customerInventoryService.getCustomerInventory(
                integrationId, profile, referrals, coupons, loyalty, giveaways, achievements);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
        return ResponseEntity.ok(response);
    }
}
