package com.example.customerinventory.model;

public class CouponInfo {
    private String code;
    private double discount;
    private boolean redeemed;

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }
    public boolean isRedeemed() { return redeemed; }
    public void setRedeemed(boolean redeemed) { this.redeemed = redeemed; }
}
