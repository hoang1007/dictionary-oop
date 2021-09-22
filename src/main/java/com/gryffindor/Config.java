package com.gryffindor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
  private String googleAPIUrl;

  public Config() {
    Properties properties = new Properties();
    try (InputStream is = new FileInputStream("src/resources/config.properties")) {
      properties.load(is);

      init(properties);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  void init(Properties properties) {
    googleAPIUrl = properties.getProperty("googleapiurl");
  }

  public String getGoogleAPIUrl() {
    return googleAPIUrl;
  }
}
