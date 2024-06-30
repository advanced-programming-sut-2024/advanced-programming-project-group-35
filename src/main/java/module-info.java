module gwent {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;

    opens com.example.view.menuControllers to javafx.fxml;
    opens com.example.model to com.google.gson;
    opens com.example.model.user to com.google.gson, javafx.base;
    opens com.example.model.card.enums to com.google.gson;

    exports com.example;
    exports com.example.model;
    exports com.example.controller;
    exports com.example.view;
    exports com.example.view.menuControllers;
    exports com.example.model.alerts;
    exports com.example.model.card.enums;
    exports com.example.model.game.place;
    opens com.example.model.game.place to com.google.gson;
}