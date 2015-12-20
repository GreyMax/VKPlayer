package com.greymax.vkplayer;

import com.greymax.vkplayer.auth.VkAuthScene;
import com.greymax.vkplayer.ui.VKPlayerScene;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class VKPlayer extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    ApplicationProperties applicationProperties = ApplicationProperties.getInstance();
    stage.setTitle(applicationProperties.getString(Constants.APP.TITLE));
    stage.setResizable(Boolean.TRUE);
    stage.getIcons().add(new Image(applicationProperties.getString(Constants.APP.ICON)));

    VkAuthScene vkAuthScene = new VkAuthScene();
    vkAuthScene.addLoginListener(event -> {
      Integer minHeight = applicationProperties.getInt(Constants.APP.MIN_HEIGHT);
      Integer minWidth = applicationProperties.getInt(Constants.APP.MIN_WIDTH);
      stage.setScene(new VKPlayerScene(minWidth, minHeight));
      stage.setMinHeight(minHeight);
      stage.setMinWidth(minWidth);
      stage.sizeToScene();
    });

    stage.setScene(vkAuthScene);
    stage.sizeToScene();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
