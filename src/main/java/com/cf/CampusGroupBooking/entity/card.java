package com.cf.CampusGroupBooking.entity;

public class card {
    String cardTitle;
    String cardDesc;
    String cardPrice;
    String cardViews;

    public card(String cardTitle, String cardDesc, String cardPrice, String cardViews) {
        this.cardTitle = cardTitle;
        this.cardDesc = cardDesc;
        this.cardPrice = cardPrice;
        this.cardViews = cardViews;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public String getCardDesc() {
        return cardDesc;
    }

    public void setCardDesc(String cardDesc) {
        this.cardDesc = cardDesc;
    }

    public String getCardPrice() {
        return cardPrice;
    }

    public void setCardPrice(String cardPrice) {
        this.cardPrice = cardPrice;
    }

    public String getCardViews() {
        return cardViews;
    }

    public void setCardViews(String cardViews) {
        this.cardViews = cardViews;
    }

    @Override
    public String toString() {
        return "card{" +
                "cardTitle='" + cardTitle + '\'' +
                ", cardDesc='" + cardDesc + '\'' +
                ", cardPrice='" + cardPrice + '\'' +
                ", ardViews='" + cardViews + '\'' +
                '}';
    }
}
