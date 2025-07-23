package com.example.customerinventory.model;

import java.util.List;

public class CustomerInventoryResponse {
    private CustomerProfile profile;
    private LoyaltyInfo loyalty;
    private List<ReferralInfo> referrals;
    private List<CouponInfo> coupons;
    private List<GiveawayInfo> giveaways;
    private List<AchievementInfo> achievements;

    public CustomerProfile getProfile() { return profile; }
    public void setProfile(CustomerProfile profile) { this.profile = profile; }
    public LoyaltyInfo getLoyalty() { return loyalty; }
    public void setLoyalty(LoyaltyInfo loyalty) { this.loyalty = loyalty; }
    public List<ReferralInfo> getReferrals() { return referrals; }
    public void setReferrals(List<ReferralInfo> referrals) { this.referrals = referrals; }
    public List<CouponInfo> getCoupons() { return coupons; }
    public void setCoupons(List<CouponInfo> coupons) { this.coupons = coupons; }
    public List<GiveawayInfo> getGiveaways() { return giveaways; }
    public void setGiveaways(List<GiveawayInfo> giveaways) { this.giveaways = giveaways; }
    public List<AchievementInfo> getAchievements() { return achievements; }
    public void setAchievements(List<AchievementInfo> achievements) { this.achievements = achievements; }
}
