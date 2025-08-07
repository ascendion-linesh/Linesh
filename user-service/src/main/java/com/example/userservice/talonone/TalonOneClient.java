package com.example.userservice.talonone;

import com.example.userservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class TalonOneClient {
    // TODO: Replace with real Talon.One SDK or REST API integration
    public void registerUser(User user) {
        // Simulate Talon.One registration
        // In production, call Talon.One API with user details and handle response
        System.out.println("Registering user with Talon.One: " + user.getEmail());
    }
}
