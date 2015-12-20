package com.greymax.vkplayer.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class VKPlayerScene extends Scene {

  public VKPlayerScene(Integer width, Integer height) {
    super(new StackPane(), width, height);
    init();
  }

  private void init() {
    try {
      Parent parent = FXMLLoader.load(this.getClass().getResource("main/index.fxml"));
      parent.getStylesheets().add("/css/main/playlist.css");
      parent.getStylesheets().add("/css/components/SearchBox.css");

      StackPane root = (StackPane) this.getRoot();
      root.getChildren().add(parent);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
