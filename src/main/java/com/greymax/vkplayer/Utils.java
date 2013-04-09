package com.greymax.vkplayer;

import com.greymax.vkplayer.objects.Settings;
import com.greymax.vkplayer.objects.User;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.ini4j.Wini;
import org.jasypt.util.text.BasicTextEncryptor;

import java.io.*;
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
    List<String> newVersion = new ArrayList<String>();
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

  public static void saveSettingsForUser(User user) {
    String PREFIX = user.getUsername();
    Settings settings = user.getSettings();
    try {
      File f = new File(getAppFolderInSystem() + "/" + SETTINGS_FILE);
      if (!f.exists())
        f.createNewFile();
      Wini ini = new Wini(f);

      ini.put("LOOKANDFEEL", PREFIX, settings.getLookAndFeel());
      ini.put("ENABLE_SONG_POPUP", PREFIX, settings.getNeedShowInformationDialog());

      for (int i = 0; i < 10; i++) {
        ini.put("EQUALIZER", PREFIX + "_" + String.valueOf(i), settings.getEqualizer()[i]);
      }

      ini.put("VOLUME", PREFIX, settings.getVolume());
      ini.put("LOCALIZATION", PREFIX, settings.getLocaleDisplayName());

      ini.store();
    } catch (IOException e) {
      logger.error("Save settings for user:", e);
    }
  }

  public static Settings getSettingsForUser(User user) {
    Settings settings = new Settings();
    String PREFIX = user.getUsername();
    try {
      File f = new File(getAppFolderInSystem() + "/" + SETTINGS_FILE);
      if (!f.exists())
        f.createNewFile();
      Wini ini = new Wini(f);

      String lookAndFeel = ini.get("LOOKANDFEEL", PREFIX);
      if (null != lookAndFeel && !"".equals(lookAndFeel))
        settings.setLookAndFeel(ini.get("LOOKANDFEEL", PREFIX));

      float[] equalizer = new float[10];
      for (int i = 0; i < 10; i++)
        if (null != ini.get("EQUALIZER", PREFIX + "_" + String.valueOf(i)))
          equalizer[i] = Float.parseFloat(ini.get("EQUALIZER", PREFIX + "_" + String.valueOf(i)));
      settings.setEqualizer(equalizer);

      Locale userLocale = new Locale("en", "US");
      String localization = ini.get("LOCALIZATION", PREFIX);
      if (StringUtils.isNotEmpty(localization))
        userLocale = new Locale(localization.split("_")[0], localization.split("_")[1]);
      settings.setLocale(userLocale);

      int volume = 100;
      String volumeStr = ini.get("VOLUME", PREFIX);
      if (StringUtils.isNotEmpty(volumeStr))
        volume = Integer.parseInt(volumeStr);
      settings.setVolume(volume);

      Boolean needShowSongPopup = Boolean.TRUE;
      String enableSongPopup = ini.get("ENABLE_SONG_POPUP", PREFIX);
      if (StringUtils.isNotEmpty(enableSongPopup))
        needShowSongPopup = Boolean.parseBoolean(enableSongPopup);
      settings.setNeedShowInformationDialog(needShowSongPopup);

    } catch (IOException e) {
      logger.error("Get settings for user:", e);
    }
    return settings;
  }

  public static String getMessageByKey(String key) {

    if (StringUtils.isEmpty(key)) return "";

    //TODO: uncomment this!!!
/*        User loggedUser = VkPlayerForm.getInstance().getLoggedUser();
        String result = key;
        ResourceBundle.clearCache();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resources/localization/localization", null == loggedUser ? DEFAULT_LOCALE : loggedUser.getSettings().getLocale());

        try {
            result = new String( resourceBundle.getString(key).getBytes("ISO-8859-1"),"Cp1251");
        } catch (UnsupportedEncodingException e) {
            logger.error("Get message by key:", e);
        }

        if (key.equals(ABOUT_MESSAGE_DIALOG_KEY)) return getMessageByKey("player.title") + " " + PLAYER_CURRENT_VERSION + result;

        return result;
*/
    return "";
  }

}
