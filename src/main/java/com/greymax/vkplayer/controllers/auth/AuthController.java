package com.greymax.vkplayer.controllers.auth;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AuthController {

    public TextField loginField;
    public PasswordField passwordField;
    public Button loginButton;
    public Button exitButton;

    public void login(ActionEvent event) {

    }

    public void exit(ActionEvent event) {
        Platform.exit();
    }

    public void minimize(ActionEvent event) {
        ((Stage)((Button) event.getSource()).getScene().getWindow()).setIconified(true);
    }

    public void clickOnHeader(MouseEvent mouseEvent) {
        System.out.println("========================================");
    }

    public void moveWindow(MouseEvent mouseEvent) {
        System.out.println("========================================");
    }
}
