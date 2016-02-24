package com.greymax.vkplayer.auth;

import org.apache.log4j.Logger;
import org.springframework.social.connect.Connection;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.vkontakte.api.VKontakte;
import org.springframework.social.vkontakte.connect.VKontakteConnectionFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public final class AuthService {
  private static Logger logger = Logger.getLogger(AuthService.class);

  public static final String VK_AUTH_ACCESS_TOKEN_KEY = "access_token";

  private VKontakteConnectionFactory connectionFactory;
  private Connection<VKontakte> connection;

  public Connection<VKontakte> getConnection() {
    return connection;
  }

  public String buildAuthorizeUrl(ResourceBundle rb) {
    connectionFactory = new VKontakteConnectionFactory(rb.getString("clientId"), rb.getString("clientSecret"));
    OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
    OAuth2Parameters params = new OAuth2Parameters();
    params.setRedirectUri(rb.getString("redirectUrl"));
    params.setScope(rb.getString("scope"));
    params.set("display", rb.getString("display"));

    return oauthOperations.buildAuthorizeUrl(GrantType.IMPLICIT_GRANT, params);
  }

  public void createConnection(String url) {
    AccessGrant accessGrant = new AccessGrant(getTokenFromUrl(url));
    try {
      connection = connectionFactory.createConnection(accessGrant);
    } catch (Exception ex) {
      logger.error("Could not login user", ex);
      // TODO: show error dialog!!!
      throw new AuthException();
    }
    logger.info(String.format("User %s successfully logged in.", connection.getDisplayName()));
  }

  private String getTokenFromUrl(String url) {
    String queryString = url.substring(url.indexOf("#") + 1);
    Map<String, String> queryParams = new HashMap<>();
    Arrays.asList(queryString.split("&")).stream().forEach(param -> {
      String[] keyValuePair = param.split("=");
      queryParams.put(keyValuePair[0], keyValuePair[1]);
    });

    return queryParams.get(VK_AUTH_ACCESS_TOKEN_KEY);
  }
}
