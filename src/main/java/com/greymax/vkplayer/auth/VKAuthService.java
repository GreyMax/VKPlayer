package com.greymax.vkplayer.auth;

import org.springframework.social.connect.Connection;
import org.springframework.social.vkontakte.api.VKontakte;

public final class VKAuthService {

  private static VKAuthService instance;

  private Connection<VKontakte> connection;

  public static VKAuthService getInstance() {
    if (null == instance) {
      instance = new VKAuthService();
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
