package com.greymax.vkplayer;

import com.greymax.vkplayer.auth.Token;
import com.greymax.vkplayer.auth.VkAuthScene;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class VKPlayer extends Application{

  public static final String APPLICATION_TITLE = "VK Player";
  public static final String APPLICATION_ICON = "image/ico.png";

  @Override
  public void start(Stage stage) throws Exception {
    stage.hide();
//    Parent root = FXMLLoader.load(getClass().getResource("ui/main/index.fxml"));
//    root.getStylesheets().add("/css/main/playlist.css");
//    root.getStylesheets().add("/css/components/SearchBox.css");
    stage.setTitle(APPLICATION_TITLE);
    stage.setResizable(Boolean.TRUE);
    stage.getIcons().add(new Image(APPLICATION_ICON));


    VkAuthScene vkAuthScene = new VkAuthScene();
    vkAuthScene.addLoginListener(event -> {
      Token token = vkAuthScene.getToken();
      stage.hide();
    });

    stage.setScene(vkAuthScene);
    stage.sizeToScene();
    stage.show();
  }
}
