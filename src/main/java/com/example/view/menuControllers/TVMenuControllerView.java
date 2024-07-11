package com.example.view.menuControllers;

import com.example.controller.Controller;
import com.example.model.App;
import com.example.view.Menu;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class TVMenuControllerView {

    @FXML
    public void initialize() {
//        App.getAppView().setMenuTitle("TV Menu");
    }
    public void backToProfileMenu(MouseEvent mouseEvent) {
        App.setCurrentMenu(Menu.PROFILE_MENU);
        Controller.PROFILE_MENU_CONTROLLER.run();
    }

    public void openTerminal(MouseEvent mouseEvent) {
        App.getAppView().showTerminal();
    }
}
