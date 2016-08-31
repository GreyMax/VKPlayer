package com.greymax.vkplayer.auth;

import com.greymax.vkplayer.VKPlayerPreferences;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.social.connect.Connection;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.vkontakte.api.VKontakte;
import org.springframework.social.vkontakte.connect.VKontakteConnectionFactory;

import java.util.ResourceBundle;

public final class AuthService {
  private static Logger logger = Logger.getLogger(AuthService.class);

  public static final String VK_AUTH_ACCESS_TOKEN_KEY = "access_token";
  public static final String VK_AUTH_CLIENT_ID = "3049094";

  private VKontakteConnectionFactory connectionFactory ;
  private Connection<VKontakte> connection;

  public AuthService() {
    this.connectionFactory = new VKontakteConnectionFactory(VK_AUTH_CLIENT_ID, StringUtils.EMPTY);
  }

  public Connection<VKontakte> getConnection() {
    return connection;
  }

  public String buildAuthorizeUrl(ResourceBundle rb) {
    OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
    OAuth2Parameters params = new OAuth2Parameters();
    params.setRedirectUri(rb.getString("redirectUrl"));
    params.setScope(rb.getString("scope"));
    params.set("display", rb.getString("display"));

    return oauthOperations.buildAuthorizeUrl(GrantType.IMPLICIT_GRANT, params);
  }

  public void createConnection(String token) {
    AccessGrant accessGrant = new AccessGrant(token);
    try {
      connection = connectionFactory.createConnection(accessGrant);
    } catch (Exception ex) {
      logger.error("Could not login user", ex);
      // TODO: show error dialog!!!
      throw new AuthException();
    }
    VKPlayerPreferences.setToken(token);
    logger.info(String.format("User %s successfully logged in.", connection.getDisplayName()));
  }

  public boolean isAuthorized() {
    if (connection != null)
      return connection.getApi().isAuthorized();

    String token = VKPlayerPreferences.getToken();
    if (token == null)
      return Boolean.FALSE;

    try {
      createConnection(token);
      return connection.getApi().isAuthorized();
    } catch (AuthException ex) { // Do nothing }
      return Boolean.FALSE;
    }
  }
}
