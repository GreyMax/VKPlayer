package com.greymax.vkplayer.auth;

import javafx.concurrent.Worker;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;

import java.util.*;

public class VkAuthScene extends Scene {

  private static final String VK_AUTH_BASE_URL = "https://oauth.vk.com/authorize";
  private static final String VK_AUTH_REDIRECT_URL = "https://oauth.vk.com/blank.html";
  private static final String VK_AUTH_CLIENT_ID = "3049094";
  private static final String VK_AUTH_SCOPE = "audio,status,friends,wall,video";
  private static final String VK_AUTH_DISPLAY_TYPE = "page";
  private static final String VK_AUTH_RESPONSE_TYPE = "token";

  private static final String VK_AUTH_ACCESS_TOKEN_KEY = "access_token";
  private static final String VK_AUTH_USER_ID_KEY = "user_id";

  private List<EventHandler> loginEventHandlers = new ArrayList<>();
  private Token token;

  public VkAuthScene() {
    super(new WebView(), 660, 380);
    initScene();
  }

  public void addLoginListener(EventHandler eventHandler) {
    loginEventHandlers.add(eventHandler);
  }

  public Token getToken() {
    return this.token;
  }

  private void initScene() {
    WebView webView = (WebView) this.getRoot();
    WebEngine webEngine = webView.getEngine();
    webEngine.load(buildAuthUrl());
    webEngine.getLoadWorker().stateProperty().addListener((observable, oldState, newState) -> {
      if (newState == Worker.State.SUCCEEDED) {
        String url = webEngine.getLocation();
        if (url.contains("error")) {
          loginEventHandlers.stream().forEach(eventHandler -> {
            eventHandler.handle(new Event(VkAuthEvent.FAIL.getValue()));
          });
        }
        if (url.contains(VK_AUTH_ACCESS_TOKEN_KEY)) {
          this.token = parseToken(url);
          loginEventHandlers.stream().forEach(eventHandler -> {
            eventHandler.handle(new Event(VkAuthEvent.SUCCESS.getValue()));
          });
        }
      }
    });
  }

  private String buildAuthUrl() {
    String url = "";
    try {
      HttpMethod method = new GetMethod(VK_AUTH_BASE_URL);

      List<NameValuePair> params = new ArrayList<>();
      params.add(new NameValuePair("client_id", VK_AUTH_CLIENT_ID));
      params.add(new NameValuePair("scope", VK_AUTH_SCOPE));
      params.add(new NameValuePair("display", VK_AUTH_DISPLAY_TYPE));
      params.add(new NameValuePair("response_type", VK_AUTH_RESPONSE_TYPE));
      params.add(new NameValuePair("redirect_url", VK_AUTH_REDIRECT_URL));

      method.setQueryString(params.toArray(new NameValuePair[params.size()]));
      url = method.getURI().getEscapedURI();
    } catch (org.apache.commons.httpclient.URIException e) {
      e.printStackTrace();
    }

    return url;
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
