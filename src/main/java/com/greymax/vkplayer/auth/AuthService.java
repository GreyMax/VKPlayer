package com.greymax.vkplayer.auth;

import org.springframework.social.connect.Connection;
import org.springframework.social.vkontakte.api.VKontakte;

public final class AuthService {

  private static AuthService instance;

  private Connection<VKontakte> connection;

  public static AuthService getInstance() {
    if (null == instance) {
      instance = new AuthService();
    }
    return instance;
  }

  public Connection<VKontakte> getConnection() {
    return connection;
  }

  public void setConnection(Connection<VKontakte> connection) {
    this.connection = connection;
  }
}
