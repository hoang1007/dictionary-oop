package com.gryffindor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
  private String googleApiUrl;
  private String style;

  /** Khởi tạo config. */
  public Config() {
    Properties properties = new Properties();
    try (InputStream is = getClass().getResourceAsStream("/config.properties")) {
      properties.load(is);

      init(properties);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  void init(Properties properties) {
    googleApiUrl = properties.getProperty("googleapiurl");
    style = getClass().getResource("/styles/styles.css").toExternalForm();
  }

  public String getGoogleApiUrl() {
    return googleApiUrl;
  }

  public String getStyle() {
    return style;
  }
}
