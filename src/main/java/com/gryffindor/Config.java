package com.gryffindor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import com.gryffindor.backend.utils.PathUtils;

public class Config {
  private final String resourcesPath;

  private String googleApiUrl;
  private String style;
  private String imagesPath;
  private String dataDictionaryPath;
  private String tessdata;

  private String contributeThanks;
  private String wordTargetSign;
  private String wordExplainSign;
  private String exampleSign;
  private String wordClassSign;
  private String wordSpellingSign;
  private String exampleDelim;



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
    wordTargetSign = properties.getProperty("word-target-sign");
    wordExplainSign = properties.getProperty("word-explain-sign");
    exampleSign = properties.getProperty("example-sign");
    wordClassSign = properties.getProperty("word-class-sign");
    wordSpellingSign = properties.getProperty("word-spelling-sign");
    exampleDelim = properties.getProperty("example-delim");

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

  public String getDataDictionaryPath() {
    return dataDictionaryPath;
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

  public String getWordTargetSign() {
    return wordTargetSign;
  }

  public String getWordExplainSign() {
    return wordExplainSign;
  }

  public String getExampleSign() {
    return exampleSign;
  }

  public String getWordClassSign() {
    return wordClassSign;
  }

  public String getWordSpellingSign() {
    return wordSpellingSign;
  }

  public String getExampleDelim() {
    return exampleDelim;
  }

  public String getTessdata() {
    return tessdata;
  }
}
