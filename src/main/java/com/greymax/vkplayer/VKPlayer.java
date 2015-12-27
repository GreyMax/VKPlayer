package com.greymax.vkplayer;

import com.airhacks.afterburner.injection.Injector;
import com.greymax.vkplayer.auth.AuthEventTypes;
import com.greymax.vkplayer.auth.AuthPresenter;
import com.greymax.vkplayer.auth.AuthView;
import com.greymax.vkplayer.player.PlayerView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class VKPlayer extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    stage.setTitle(Constants.APP.TITLE);
    stage.setResizable(Boolean.TRUE);
    stage.getIcons().add(new Image(Constants.APP.ICON));

    AuthView authView = new AuthView();
    Scene scene = new Scene(authView.getView(), Constants.APP.MIN_WIDTH, Constants.APP.MIN_HEIGHT);
    AuthPresenter authViewPresenter = (AuthPresenter) authView.getPresenter();
    authViewPresenter.setLoginHandler(event -> {
      if (event.getEventType().equals(AuthEventTypes.SUCCESS.getValue())) {
        PlayerView playerView = new PlayerView();
        Scene playerScene = new Scene(playerView.getView());
        stage.setScene(playerScene);
      }
    });

    stage.setScene(scene);
    stage.sizeToScene();
    stage.show();
  }

  @Override
  public void stop() throws Exception {
    Injector.forgetAll();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
