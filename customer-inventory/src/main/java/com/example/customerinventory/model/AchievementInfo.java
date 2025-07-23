package com.example.customerinventory.model;

import java.time.ZonedDateTime;

public class AchievementInfo {
    private String name;
    private ZonedDateTime achievedAt;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public ZonedDateTime getAchievedAt() { return achievedAt; }
    public void setAchievedAt(ZonedDateTime achievedAt) { this.achievedAt = achievedAt; }
}
