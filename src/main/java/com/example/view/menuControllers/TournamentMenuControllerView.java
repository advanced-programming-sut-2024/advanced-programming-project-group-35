package com.example.view.menuControllers;

import com.example.controller.Controller;
import com.example.model.App;
import com.example.view.Menu;
import javafx.scene.input.MouseEvent;

public class TournamentMenuControllerView {

    public void openTerminal(MouseEvent mouseEvent) {
        App.getAppView().showTerminal();
    }

    public void backToPreGameMenu(MouseEvent mouseEvent) {
        App.setCurrentMenu(Menu.PREGAME_MENU);
        Controller.PRE_GAME_MENU_CONTROLLER.run();
    }
}
