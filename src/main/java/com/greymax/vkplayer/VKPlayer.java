package com.greymax.vkplayer;

import com.airhacks.afterburner.injection.Injector;
import com.airhacks.afterburner.views.FXMLView;
import com.greymax.vkplayer.auth.AuthEvent;
import com.greymax.vkplayer.auth.AuthPresenter;
import com.greymax.vkplayer.auth.AuthService;
import com.greymax.vkplayer.auth.AuthView;
import com.greymax.vkplayer.player.PlayerView;
import com.jfoenix.controls.JFXDecorator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static com.greymax.vkplayer.Constants.APP.*;
import static com.greymax.vkplayer.auth.AuthEvent.SUCCESS;

public class VKPlayer extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(TITLE);
        stage.setResizable(true);
        stage.getIcons().add(new Image(ICON));
        stage.setMinHeight(MIN_HEIGHT);
        stage.setMinWidth(MIN_WIDTH);


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
            if (event.getEventType().equals(SUCCESS)) showPlayer(stage);
        });
    }

    private void setView(Stage stage, FXMLView view) {
        Scene scene = new Scene(new JFXDecorator(stage, view.getView()), MIN_WIDTH, MIN_HEIGHT);
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
