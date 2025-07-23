package com.example.customerinventory.service;

import com.example.customerinventory.model.*;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.*;

@Service
public class CustomerInventoryService {
    public CustomerInventoryResponse getCustomerInventory(String integrationId, boolean profile, boolean referrals, boolean coupons, boolean loyalty, boolean giveaways, boolean achievements) {
        // In a real application, fetch from DB. Here, stubbed data for demonstration.
        if (!"customer1".equals(integrationId)) {
            return null;
        }
        CustomerInventoryResponse response = new CustomerInventoryResponse();
        if (profile) {
            CustomerProfile prof = new CustomerProfile();
            prof.setId(6L);
            prof.setCreated(ZonedDateTime.parse("2020-02-07T08:15:22Z"));
            prof.setIntegrationId("URNGV8294NV");
            prof.setAttributes(new HashMap<>());
            prof.setAccountId(31L);
            prof.setClosedSessions(3);
            prof.setTotalSales(299.99);
            prof.setLoyaltyMemberships(new ArrayList<>());
            prof.setAudienceMemberships(new ArrayList<>());
            prof.setLastActivity(ZonedDateTime.parse("2020-02-08T14:15:20Z"));
            prof.setSandbox(false);
            response.setProfile(prof);
        }
        if (loyalty) {
            LoyaltyInfo loyaltyInfo = new LoyaltyInfo();
            loyaltyInfo.setCards(new ArrayList<>());
            loyaltyInfo.setPrograms(new HashMap<>());
            response.setLoyalty(loyaltyInfo);
        }
        if (referrals) {
            ReferralInfo ref = new ReferralInfo();
            ref.setCode("REF123456");
            ref.setIssuedAt(ZonedDateTime.now());
            ref.setRedeemed(false);
            response.setReferrals(Collections.singletonList(ref));
        }
        if (coupons) {
            CouponInfo coupon = new CouponInfo();
            coupon.setCode("COUPON2024");
            coupon.setDiscount(10.0);
            coupon.setRedeemed(false);
            response.setCoupons(Collections.singletonList(coupon));
        }
        if (giveaways) {
            GiveawayInfo giveaway = new GiveawayInfo();
            giveaway.setName("Free Mug");
            giveaway.setIssuedAt(ZonedDateTime.now().minusDays(2));
            giveaway.setRedeemed(false);
            response.setGiveaways(Collections.singletonList(giveaway));
        }
        if (achievements) {
            AchievementInfo achievement = new AchievementInfo();
            achievement.setName("First Purchase");
            achievement.setAchievedAt(ZonedDateTime.now().minusMonths(1));
            response.setAchievements(Collections.singletonList(achievement));
        }
        return response;
    }
}
