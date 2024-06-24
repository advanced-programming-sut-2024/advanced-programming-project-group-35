package com.example.model.alerts;

public enum AlertType {
    INFO("info"),
    WARNING("warning"),
    ERROR("error"),
    ;
    private final String type;

    AlertType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
