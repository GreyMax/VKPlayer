package com.greymax.vkplayer.controllers.auth;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
}
