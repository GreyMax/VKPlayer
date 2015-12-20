package com.greymax.vkplayer;

import com.greymax.vkplayer.auth.VkAuthScene;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class VKPlayer extends Application {

  public static final String VK_PLAYER_ICON = "image/ico.png";
  public static final String VK_PLAYER_TITLE_KEY = "title";

  @Override
  public void start(Stage stage) throws Exception {
    ApplicationProperties applicationProperties = ApplicationProperties.getInstance();
//    Parent root = FXMLLoader.load(getClass().getResource("ui/main/index.fxml"));
//    root.getStylesheets().add("/css/main/playlist.css");
//    root.getStylesheets().add("/css/components/SearchBox.css");
    stage.setTitle(applicationProperties.getSting(VK_PLAYER_TITLE_KEY));
    stage.setResizable(Boolean.TRUE);
    stage.getIcons().add(new Image(VK_PLAYER_ICON));

    VkAuthScene vkAuthScene = new VkAuthScene();
    vkAuthScene.addLoginListener(event -> {
      stage.hide();
    });

    stage.setScene(vkAuthScene);
    stage.sizeToScene();
    stage.show();
  }
}
