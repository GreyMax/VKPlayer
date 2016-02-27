package com.greymax.vkplayer;

import com.airhacks.afterburner.injection.Injector;
import com.airhacks.afterburner.views.FXMLView;
import com.greymax.vkplayer.auth.*;
import com.greymax.vkplayer.player.PlayerView;
import com.jfoenix.controls.JFXDecorator;
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
    if (authService.isAuthorized())
      showPlayer(stage);
    else
      showAuthForm(stage);
  }

  private void showPlayer(Stage stage) {
    setView(stage, new PlayerView());
  }

  private void showAuthForm(Stage stage) {
    AuthView authView = new AuthView();
    setView(stage, authView);

    AuthPresenter authViewPresenter = (AuthPresenter) authView.getPresenter();
    authViewPresenter.setLoginHandler(event -> {
      if (event.getEventType().equals(AuthEvent.SUCCESS)) showPlayer(stage);
    });
  }

  private void setView(Stage stage, FXMLView view) {
    Scene scene = new Scene(new JFXDecorator(stage, view.getView()), Constants.APP.MIN_WIDTH, Constants.APP.MIN_HEIGHT);
    scene.getStylesheets().add(VKPlayer.class.getResource("/resources/css/jfoenix-fonts.css").toExternalForm());
    scene.getStylesheets().add(VKPlayer.class.getResource("/resources/css/jfoenix-design.css").toExternalForm());
    scene.getStylesheets().add(VKPlayer.class.getResource("colors.css").toExternalForm());
    scene.getStylesheets().add(VKPlayer.class.getResource("window.css").toExternalForm());
    stage.setScene(scene);
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
