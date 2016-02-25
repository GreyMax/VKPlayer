package com.greymax.vkplayer;

import com.airhacks.afterburner.injection.Injector;
import com.greymax.vkplayer.auth.*;
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
    stage.setMinHeight(Constants.APP.MIN_HEIGHT);
    stage.setMinWidth(Constants.APP.MIN_WIDTH);

    AuthService authService = Injector.instantiateModelOrService(AuthService.class);
    if (authService.isAuthorized()) {
      showPlayer(stage);
    }
    else {
      AuthView authView = new AuthView();
      Scene scene = new Scene(authView.getView(), Constants.APP.MIN_WIDTH, Constants.APP.MIN_HEIGHT);
      AuthPresenter authViewPresenter = (AuthPresenter) authView.getPresenter();
      authViewPresenter.setLoginHandler(event -> {
        if (event.getEventType().equals(AuthEventTypes.SUCCESS.getValue())) showPlayer(stage);
      });

      stage.setScene(scene);
      stage.sizeToScene();
      stage.show();
    }
  }

  private void showPlayer(Stage stage) {
    PlayerView playerView = new PlayerView();
    Scene playerScene = new Scene(playerView.getView(), Constants.APP.MIN_WIDTH, Constants.APP.MIN_HEIGHT);
    stage.setScene(playerScene);
    stage.sizeToScene();
    stage.show();
  }

  @Override
  public void stop() throws Exception {
    Injector.forgetAll();
    VKPlayerPreferences.flush();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
