package com.gryffindor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class Config {
  private final String resourcesPath;
  private String googleApiUrl;
  private String style;
  private String imagesPath;
  private String contributeThanks;
  private String tessdata;

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
    
    try {
      URI uri = new URI(resourcesPath + "/tessdata");

      tessdata = new File(uri.getSchemeSpecificPart()).getPath();
      System.out.println(tessdata);
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
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

  public String getTessdata() {
    return tessdata;
  }
}
