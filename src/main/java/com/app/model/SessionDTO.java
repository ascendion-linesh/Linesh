// File: com/app/dto/SessionDTO.java
package com.app.dto;

import lombok.*;

import java.util.List;

/**
 * DTO representing a session for Talon.One evaluation.
 * Structure should match Talon.One's expected session schema.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessionDTO {
    private String sessionId;
    private Long userId;
    private List<ItemDTO> items;
    private double totalAmount;
    private ProfileDTO profile;
    // Add other session fields as required by Talon.One
}
