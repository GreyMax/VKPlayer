package com.greymax.vkplayer.auth;

import javafx.concurrent.Worker;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthPresenter implements Initializable {

  private EventHandler<Event> loginHandler;

  @Inject
  private AuthService authService;

  @FXML
  public WebView webView;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    WebEngine webEngine = webView.getEngine();
    webEngine.load(authService.buildAuthorizeUrl(resources));
    webEngine.getLoadWorker().stateProperty().addListener((observable, oldState, newState) -> {
      if (newState == Worker.State.SUCCEEDED) {
        String url = webEngine.getLocation();
        if (url.contains(AuthService.VK_AUTH_ACCESS_TOKEN_KEY)) {
          authService.createConnection(url);
          loginHandler.handle(new Event(AuthEventTypes.SUCCESS.getValue()));
        }
      }
    });
  }

  public void setLoginHandler(EventHandler<Event> handler) {
    this.loginHandler = handler;
  }
}
