package com.example.customerinventory.model;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

public class CustomerProfile {
    private Long id;
    private ZonedDateTime created;
    private String integrationId;
    private Map<String, Object> attributes;
    private Long accountId;
    private int closedSessions;
    private double totalSales;
    private List<Object> loyaltyMemberships;
    private List<Object> audienceMemberships;
    private ZonedDateTime lastActivity;
    private boolean sandbox;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public ZonedDateTime getCreated() { return created; }
    public void setCreated(ZonedDateTime created) { this.created = created; }
    public String getIntegrationId() { return integrationId; }
    public void setIntegrationId(String integrationId) { this.integrationId = integrationId; }
    public Map<String, Object> getAttributes() { return attributes; }
    public void setAttributes(Map<String, Object> attributes) { this.attributes = attributes; }
    public Long getAccountId() { return accountId; }
    public void setAccountId(Long accountId) { this.accountId = accountId; }
    public int getClosedSessions() { return closedSessions; }
    public void setClosedSessions(int closedSessions) { this.closedSessions = closedSessions; }
    public double getTotalSales() { return totalSales; }
    public void setTotalSales(double totalSales) { this.totalSales = totalSales; }
    public List<Object> getLoyaltyMemberships() { return loyaltyMemberships; }
    public void setLoyaltyMemberships(List<Object> loyaltyMemberships) { this.loyaltyMemberships = loyaltyMemberships; }
    public List<Object> getAudienceMemberships() { return audienceMemberships; }
    public void setAudienceMemberships(List<Object> audienceMemberships) { this.audienceMemberships = audienceMemberships; }
    public ZonedDateTime getLastActivity() { return lastActivity; }
    public void setLastActivity(ZonedDateTime lastActivity) { this.lastActivity = lastActivity; }
    public boolean isSandbox() { return sandbox; }
    public void setSandbox(boolean sandbox) { this.sandbox = sandbox; }
}
