package com.greymax.vkplayer.auth;

import javafx.concurrent.Worker;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.social.connect.Connection;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.vkontakte.api.VKontakte;
import org.springframework.social.vkontakte.connect.VKontakteConnectionFactory;

import java.util.*;

public class VkAuthScene extends Scene {

  private static Logger logger = Logger.getLogger(VkAuthScene.class);

  private static final String VK_AUTH_REDIRECT_URL = "https://oauth.vk.com/blank.html";
  private static final String VK_AUTH_CLIENT_ID = "3049094";
  private static final String VK_AUTH_CLIENT_SECRET = StringUtils.EMPTY; //Not needed for desktop applications
  private static final String VK_AUTH_SCOPE = "audio,status,friends,wall,video";
  private static final String VK_AUTH_DISPLAY_TYPE = "page";

  private static final String VK_AUTH_ACCESS_TOKEN_KEY = "access_token";
  private static final String VK_AUTH_USER_ID_KEY = "user_id";

  private VKontakteConnectionFactory connectionFactory;
  private List<EventHandler> loginEventHandlers = new ArrayList<>();

  public VkAuthScene() {
    super(new WebView(), 660, 380);
    this.connectionFactory = new VKontakteConnectionFactory(VK_AUTH_CLIENT_ID, VK_AUTH_CLIENT_SECRET);
    initScene();
  }

  public void addLoginListener(EventHandler eventHandler) {
    loginEventHandlers.add(eventHandler);
  }

  private void initScene() {
    WebView webView = (WebView) this.getRoot();
    WebEngine webEngine = webView.getEngine();
    webEngine.load(buildAuthorizeUrl());
    webEngine.getLoadWorker().stateProperty().addListener((observable, oldState, newState) -> {
      if (newState == Worker.State.SUCCEEDED) {
        this.checkUrl(webEngine.getLocation());
      }
    });
  }

  private String buildAuthorizeUrl() {
    OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
    OAuth2Parameters params = new OAuth2Parameters();
    params.setRedirectUri(VK_AUTH_REDIRECT_URL);
    params.setScope(VK_AUTH_SCOPE);
    params.set("display", VK_AUTH_DISPLAY_TYPE);

    return oauthOperations.buildAuthorizeUrl(GrantType.IMPLICIT_GRANT, params);
  }

  private void checkUrl(String url) {
    if (url.contains("error")) {
      loginEventHandlers.stream().forEach(eventHandler -> {
        eventHandler.handle(new Event(VkAuthEvent.FAIL.getValue()));
      });
    }
    else if (url.contains(VK_AUTH_ACCESS_TOKEN_KEY)) {
      Token token = parseToken(url);
      AccessGrant accessGrant = new AccessGrant(token.getToken());
      Connection<VKontakte> connection = this.connectionFactory.createConnection(accessGrant);
      logger.info(String.format("User %s successfully logged in.", connection.getDisplayName()));
      AuthService.getInstance().setConnection(connection);


      loginEventHandlers.stream().forEach(eventHandler -> {
        eventHandler.handle(new Event(VkAuthEvent.SUCCESS.getValue()));
      });
    }
  }

  private Token parseToken(String url) {
    String queryString = url.substring(url.indexOf("#") + 1);
    Map<String, String> queryParams = new HashMap<>();
    Arrays.asList(queryString.split("&")).stream().forEach(param -> {
      String[] keyValuePair = param.split("=");
      queryParams.put(keyValuePair[0], keyValuePair[1]);
    });

    return new Token(queryParams.get(VK_AUTH_USER_ID_KEY), queryParams.get(VK_AUTH_ACCESS_TOKEN_KEY));
  }
}
