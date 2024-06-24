package com.example;


import com.example.model.App;
import com.example.view.AppView;
import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage){
        AppView appView = new AppView();
        App.setAppView(appView);
        appView.start(stage);
    }
}