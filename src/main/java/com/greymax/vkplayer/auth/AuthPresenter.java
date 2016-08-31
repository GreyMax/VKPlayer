package com.greymax.vkplayer.auth;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.inject.Inject;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;

import static com.greymax.vkplayer.auth.AuthEvent.SUCCESS;
import static com.greymax.vkplayer.auth.AuthService.VK_AUTH_ACCESS_TOKEN_KEY;
import static java.util.stream.Collectors.toMap;
import static javafx.concurrent.Worker.State.SUCCEEDED;

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
            if (newState == SUCCEEDED) {
                String url = webEngine.getLocation();
                if (url.contains(VK_AUTH_ACCESS_TOKEN_KEY)) {
                    authService.createConnection(getTokenFromUrl(url));
                    loginHandler.handle(new AuthEvent(SUCCESS));
                }
            }
        });
    }

    private String getTokenFromUrl(String url) {
        String queryString = url.substring(url.indexOf("#") + 1);
        Map<String, String> queryParams = Arrays.stream(queryString.split("&"))
                .map(param -> param.split("="))
                .collect(toMap(keyValuePair -> keyValuePair[0], keyValuePair -> keyValuePair[1]));

        return queryParams.get(VK_AUTH_ACCESS_TOKEN_KEY);
    }

    public void setLoginHandler(EventHandler<Event> handler) {
        this.loginHandler = handler;
    }
}
