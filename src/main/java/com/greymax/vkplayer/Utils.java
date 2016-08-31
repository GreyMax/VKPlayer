package com.greymax.vkplayer;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.ini4j.Wini;

import java.io.File;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Utils {

  private static Logger logger = Logger.getLogger(Utils.class);

  //TODO: extract to config file
  private static final String PASSWORD_ENCRIPTION = "iehvbyfnjh";
  public static final String PLAYER_CURRENT_VERSION = "1.3.0";

  private static Random generator = new Random(new Date().getTime());
  private static final String ABOUT_MESSAGE_DIALOG_KEY = "menu.help.about.dialog.message";
  private static final String PLAYER_VERSION_FILE_URL = "http://dl.dropbox.com/u/31483960/VKPlayer/PLAYER_VERSION.ini";
  private static final String SETTINGS_FILE = "Settings.ini";
  private static Locale DEFAULT_LOCALE = new Locale("en", "US");

  public static String getTimeFromSeconds(int sec) {

    return String.format("%s: %s",
        zf2(TimeUnit.SECONDS.toMinutes(sec)),
        zf2(TimeUnit.SECONDS.toSeconds(sec) - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(sec)))
    );
  }

  private static String zf2(long value) {

    return String.valueOf(value).length() < 2 ? "0" + String.valueOf(value) : String.valueOf(value);
  }

  public static String getAppFolderInSystem() {
    String appDir = System.getProperty("user.home") + "/.vkplayer";
    new File(appDir).mkdir();
    return appDir;
  }

  public static int getRandomIndex(int n) {
    return generator.nextInt(n);
  }

  public static List<String> checkNewVersion() {
    List<String> newVersion = new ArrayList<>();
    try {
      File f = new File(getAppFolderInSystem() + "/Version.ini");
      FileUtils.copyURLToFile(new URL(PLAYER_VERSION_FILE_URL), f);
      Wini ini = new Wini(f);
      String version = ini.get("PLAYER", "VERSION");
      if (!version.equals(PLAYER_CURRENT_VERSION)) {
        newVersion.add(version);
        newVersion.add(ini.get("PLAYER", "CHANGES"));
      }
    } catch (Exception e) {
      logger.error("Check new version:", e);
    }

    return newVersion;
  }

}
