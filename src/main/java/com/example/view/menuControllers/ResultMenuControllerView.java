package com.example.view.menuControllers;

import com.example.Main;
import com.example.controller.Controller;
import com.example.model.App;
import com.example.model.game.Table;
import com.example.view.Menu;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ResultMenuControllerView {
    public Label player1UsernameLabel;
    public Label player2UsernameLabel;
    public Label player1Round1;
    public Label player1Round2;
    public Label player1Round3;
    public Label player2Round1;
    public Label player2Round2;
    public Label player2Round3;
    public ImageView resultImageView;
    private Table table;

    @FXML
    public void initialize() {
        table = App.getAppView().getGameMenuControllerView().controller.getTable();
//        if (table.getWinner() == table.getCurrentPlayer())
//            resultImageView.setImage(new Image(Main.class.getResource("/images/icons/end_win.png").toExternalForm()));
//        else if (table.getWinner() == table.getOpponent()){
//            resultImageView.setImage(new Image(Main.class.getResource("/images/icons/end_lose.png").toExternalForm()));
//        } else {
//            resultImageView.setImage(new Image(Main.class.getResource("/images/icons/end_draw.png").toExternalForm()));
//        }
        player1UsernameLabel.setText(table.getCurrentPlayer().getUsername());
        player2UsernameLabel.setText(table.getOpponent().getUsername());
        player1Round1.setText(String.valueOf(table.getRounds().get(0).getScores().get(table.getCurrentPlayer())));
        player1Round2.setText(String.valueOf(table.getRounds().get(1).getScores().get(table.getCurrentPlayer())));
        player1Round3.setText(String.valueOf(table.getRounds().get(2).getScores().get(table.getCurrentPlayer())));
        player2Round1.setText(String.valueOf(table.getRounds().get(0).getScores().get(table.getOpponent())));
        player2Round2.setText(String.valueOf(table.getRounds().get(1).getScores().get(table.getOpponent())));
        player2Round3.setText(String.valueOf(table.getRounds().get(2).getScores().get(table.getOpponent())));
    }

    public void backToMainMenu(MouseEvent mouseEvent) {
        App.setCurrentMenu(Menu.MAIN_MENU);
        Controller.MAIN_MENU_CONTROLLER.run();
    }

}
