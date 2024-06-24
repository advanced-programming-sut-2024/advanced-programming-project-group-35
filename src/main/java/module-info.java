module gwent {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.example.view.menuControllers to javafx.fxml;
    exports com.example;
    exports com.example.model;
    exports com.example.controller;
    exports com.example.view;
    exports com.example.view.menuControllers;
    exports com.example.model.alerts;
}