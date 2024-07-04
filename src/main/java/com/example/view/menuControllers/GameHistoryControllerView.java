package com.example.view.menuControllers;

import com.example.model.App;
import javafx.scene.input.MouseEvent;

public class GameHistoryControllerView {
    public void openTerminal(MouseEvent mouseEvent) {
        App.getAppView().showTerminal();
    }
}
