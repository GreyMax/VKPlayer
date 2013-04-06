package com.greymax.vkplayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Auth extends Application {

  @Override
  public void start(final Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("ui/auth/index.fxml"));
    root.getStylesheets().add("css/auth/index.css");
    primaryStage.setTitle("Login form");
    primaryStage.setResizable(false);
    primaryStage.initStyle(StageStyle.UNDECORATED);
    primaryStage.getIcons().add(new Image("image/ico.png"));
    primaryStage.setScene(new Scene(root, 300, 210));
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

}
