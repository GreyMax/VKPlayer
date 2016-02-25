package com.greymax.vkplayer;

import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;

public class VKPlayerPreferences {
  private static final String SETTINGS_FILE = "settings.ini";
  private static final String USER_SECTION = "user";
  private static final String TOKEN = "token";

  private static Wini settingsFile;

  static {
    try {
      File f = new File(getAppFolderInSystem() + "/" + SETTINGS_FILE);
      if (!f.isFile()) f.createNewFile();
      settingsFile = new Wini(f);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static String getToken() {
    return settingsFile.get(USER_SECTION, TOKEN);
  }

  public static void setToken(String token) {
    settingsFile.put(USER_SECTION, TOKEN, token);
  }

  public static void flush() throws IOException {
    settingsFile.store();
  }

  private static String getAppFolderInSystem() {
    String appDir = System.getProperty("user.home") + "/.vkplayer";
    new File(appDir).mkdir();
    return appDir;
  }
}
