// File: com/app/dto/ProfileDTO.java
package com.app.dto;

import lombok.*;

/**
 * DTO representing user profile data for Talon.One.
 * Structure should match Talon.One's expected profile schema.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String tier;
    private int loyaltyPoints;
    // Add other profile fields as required by Talon.One
}
