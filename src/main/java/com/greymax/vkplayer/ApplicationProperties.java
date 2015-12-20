package com.greymax.vkplayer;

import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties {

  private static final String APPLICATION_PROPERTIES_FILE = "application.properties";
  private static ApplicationProperties instance = null;

  private Properties properties = new Properties();

  public static ApplicationProperties getInstance() {
    if (null == instance) {
      instance = new ApplicationProperties();
    }
    return instance;
  }

  private ApplicationProperties() {
    try {
      properties.load(this.getClass().getClassLoader().getResourceAsStream(APPLICATION_PROPERTIES_FILE));
    } catch (IOException e) {
      throw new RuntimeException("Can't read application properties", e);
    }
  }

  public String getString(Object key) {
    Object property = properties.get(key);
    return null == property ? null : property.toString();
  }

  public Integer getInt(Object key) {
    Object property = properties.get(key);
    return null == property ? null : Integer.valueOf(property.toString());
  }

  public Boolean getBoolean(Object key) {
    Object property = properties.get(key);
    return null == property ? null : Boolean.valueOf(property.toString());
  }
}
