package com.example.customerinventory.model;

import java.time.ZonedDateTime;

public class ReferralInfo {
    private String code;
    private ZonedDateTime issuedAt;
    private boolean redeemed;

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public ZonedDateTime getIssuedAt() { return issuedAt; }
    public void setIssuedAt(ZonedDateTime issuedAt) { this.issuedAt = issuedAt; }
    public boolean isRedeemed() { return redeemed; }
    public void setRedeemed(boolean redeemed) { this.redeemed = redeemed; }
}
