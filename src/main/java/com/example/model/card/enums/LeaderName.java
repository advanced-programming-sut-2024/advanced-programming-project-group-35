package com.example.model.card.enums;

public enum LeaderName {

    ;
    private final String imageAddress;

    LeaderName(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public String getImageAddress() {
        return imageAddress;
    }
}
