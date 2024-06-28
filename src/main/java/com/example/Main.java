package com.example;


import com.example.model.App;
import com.example.view.AppView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        App.loadUsers("users.json");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            App.saveUsers("users.json");
        }));
        launch(args);
    }

    @Override
    public void start(Stage stage){
        AppView appView = new AppView();
        App.setAppView(appView);
        appView.start(stage);
    }
}