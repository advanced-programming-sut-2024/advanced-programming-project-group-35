package com.example.model.alerts;

public enum Emotes {
    HA_HA_HA("t1.png"),
    THANKS("t2.png"),
    OOPS("t3.png"),
    GOOD_ONE("t4.png"),
    DIRIN_LALALA("t5.png"),
    BORING("t6.png"),
    SHHHHHH("t7.png"),
    ANY_WAY("t8.png");
    private final String address;

    Emotes(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
