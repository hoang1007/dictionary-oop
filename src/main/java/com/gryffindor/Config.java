package com.gryffindor;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class Config {
  private final String resourcesPath;
  private String googleApiUrl;
  private String style;
  private String imagesPath;
  private String contributeThanks;

  /** Khởi tạo config. */
  public Config() {
    resourcesPath = getClass().getResource("/images").toExternalForm().replace("/images", "");

    Properties properties = new Properties();
    try (InputStream is = getClass().getResourceAsStream("/config.properties")) {
      InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
      properties.load(reader);

      init(properties);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  void init(Properties properties) {
    googleApiUrl = properties.getProperty("googleapiurl");
    contributeThanks = properties.getProperty("contribute-thanks");

    style = resourcesPath + "/styles/styles.css";
    imagesPath = resourcesPath + "/images";
  }

  public String getGoogleApiUrl() {
    return googleApiUrl;
  }

  public String getStyle() {
    return style;
  }

  public String getImagesPath() {
    return imagesPath;
  }

  public String getContributeThanks() {
    return contributeThanks;
  }
}
