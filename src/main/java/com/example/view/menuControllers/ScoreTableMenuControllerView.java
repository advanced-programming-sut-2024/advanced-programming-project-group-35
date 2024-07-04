package com.example.view.menuControllers;

import com.example.controller.Controller;
import com.example.controller.ScoreTableController;
import com.example.model.App;
import com.example.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class ScoreTableMenuControllerView {
    @FXML
    private TableView scoreboardTable;
    ScoreTableController controller = (ScoreTableController) Controller.SCORE_TABLE_MENU_CONTROLLER.getController();

    @FXML
    public void initialize() {
        controller = (ScoreTableController) Controller.SCORE_TABLE_MENU_CONTROLLER.getController();
        //scoreboardTable = (TableView) App.getAppView().getPrimaryStage().getScene().lookup("#scoreboardTable");
        disableScrolling(scoreboardTable);
        controller.setScoreboardTable(scoreboardTable, App.getAllUsers());
    }

    public void backToMainMenu(MouseEvent mouseEvent) throws Exception {
        controller.backToMainMenu();
    }
    private void disableScrolling(TableView<User> tableView) {
        tableView.skinProperty().addListener((observable, oldValue, newValue) -> {
            ScrollBar horizontalScrollBar = (ScrollBar) tableView.lookup(".scroll-bar:horizontal");
            ScrollBar verticalScrollBar = (ScrollBar) tableView.lookup(".scroll-bar:vertical");
            if (horizontalScrollBar != null) {
                horizontalScrollBar.setDisable(true);
                horizontalScrollBar.setOpacity(0);
            }
            if (verticalScrollBar != null) {
                verticalScrollBar.setDisable(true);
                verticalScrollBar.setOpacity(0);
            }
        });
    }

    public void openTerminal(MouseEvent mouseEvent) {
        App.getAppView().showTerminal();
    }
}
