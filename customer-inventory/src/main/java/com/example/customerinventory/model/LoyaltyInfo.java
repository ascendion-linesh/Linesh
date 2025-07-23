package com.example.customerinventory.model;

import java.util.List;
import java.util.Map;

public class LoyaltyInfo {
    private List<Object> cards;
    private Map<String, Object> programs;

    public List<Object> getCards() { return cards; }
    public void setCards(List<Object> cards) { this.cards = cards; }
    public Map<String, Object> getPrograms() { return programs; }
    public void setPrograms(Map<String, Object> programs) { this.programs = programs; }
}
