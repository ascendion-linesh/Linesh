package com.example.customerinventory.model;

import java.time.ZonedDateTime;

public class GiveawayInfo {
    private String name;
    private ZonedDateTime issuedAt;
    private boolean redeemed;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public ZonedDateTime getIssuedAt() { return issuedAt; }
    public void setIssuedAt(ZonedDateTime issuedAt) { this.issuedAt = issuedAt; }
    public boolean isRedeemed() { return redeemed; }
    public void setRedeemed(boolean redeemed) { this.redeemed = redeemed; }
}
