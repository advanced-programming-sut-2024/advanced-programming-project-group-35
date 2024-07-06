package com.example.view.menuControllers;

import com.example.controller.Controller;
import com.example.model.App;
import com.example.view.Menu;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;

public class FriendsMenuControllerView {
    public ScrollPane mainScrollPane;

    @FXML
    public void initialize() {
        //TODO یه دونه حلقه مندازی روی ریکوئست های کسی که لاگین کرده و یکی یکی میگیریشون
        // این تیکه کد برای توی حلقه هست
        //        if (App.getLoggedInUser().getFriendRequests().size() == 0) {
        //            OutputView.showInfo(Errors.NO_FRIEND_REQUESTS);
        //        } else {
        //            HBox row = new HBox();
        //            row.setPrefWidth(600);
        //            row.setSpacing(100);
        //            row.setAlignment(Pos.CENTER);
        //            Label username = new Label();
        //            label.setText(App.getLoggedInUser().getFriendRequests().get(i).getUsername());
        //            label.setTranslateX(20);
        //            HBox rightSection = new HBox();
        //            rightSection.setSpacing(10);
        //            rightSection.setAlignment(Pos.CENTER);
        //            Label status = new Label();
        //            if (!App.getLoggedInUser().getFriendRequests().get(i).isAccepted() && !App.getLoggedInUser().getFriendRequests().get(i).isAccepted()) {
        //                status.setText("Pending");
        //            } else if (App.getLoggedInUser().getFriendRequests().get(i).isAccepted()) {
        //                status.setText("Accepted");
        //            } else {
        //                status.setText("Rejected");
        //            }
        //            اینجا یه دونه ایف بذار که اگر فرستنده خودش بود رو چک کنه
        //            Button accept = new Button("Accept");
        //            accept.setOnMouseClicked(e -> {
        //                نمیدونم
        //           });
        //            Button reject = new Button("Reject");
        //            reject.setOnMouseClicked(e -> {
        //                نمیدونم
        //            });
        //            rightSection.getChildren().addAll(status, accept, reject);
        //            row.getChildren().addAll(label, rightSection);
        //            mainScrollPane.getChildren().addAll(row);
        //        }

    }
    public void openTerminal(MouseEvent mouseEvent) {
     //   App.getAppView().showTerminal();
    }

    public void backToProfileMenu(MouseEvent mouseEvent) {
        App.setCurrentMenu(Menu.PROFILE_MENU);
        Controller.PROFILE_MENU_CONTROLLER.run();
    }
}
