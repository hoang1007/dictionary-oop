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
  private String alertStyle;
  private String imagesPath;

  private String contributeThanks;
  private String wordTargetSign;
  private String wordExplainSign;
  private String exampleSign;
  private String wordClassSign;
  private String wordSpellingSign;
  private String exampleDelim;

  private InputStream dictionaryDataStream = null;
  private InputStream googleServiceStream = null;
  private InputStream suggestDataStream = null;
  private InputStream dictionaryJsonStream = null;

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
    alertStyle = resourcesPath + "/styles/alert.css";
    imagesPath = resourcesPath + "/images";
    dictionaryDataStream = getClass().getResourceAsStream("/dictionaries.txt");
    googleServiceStream = getClass().getResourceAsStream("/serviceAccount.json");
    suggestDataStream = getClass().getResourceAsStream("/suggestData.json");
    dictionaryJsonStream = getClass().getResourceAsStream("/dictionary.txt");
  }

  /** Link google api để tìm kiếm từ. */
  public String getGoogleApiUrl() {
    return googleApiUrl;
  }

  /** Lấy dữ liệu từ điển dưới dạng stream. */
  public InputStream getDataDictionaryStream() {
    return dictionaryDataStream;
  }

  /** Lấy account service dưới dạng stream để kết nối với database. */
  public InputStream getGoogleServiceStream() {
    return googleServiceStream;
  }

  /** Dữ liệu gợi ý dưới dạng stream. */
  public InputStream getSuggestDataStream() {
    return suggestDataStream;
  }

  /** Lấy dữ liệu từ điển dưới dạng json stream. */
  public InputStream getDictionaryJson() {
    return dictionaryJsonStream;
  }

  /** Lấy đường dẫn của file style.css */
  public String getStyle() {
    return style;
  }

  /** Đường dẫn của tài nguyên ảnh. */
  public String getImagesPath() {
    return imagesPath;
  }

  public String getContributeThanks() {
    return contributeThanks;
  }

  // Các kí tự đầu của file text dữ liệu 
  // để thực hiện decode file text

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

  /** Đường dẫn của thư mục chứa tài nguyên. */
  public String getRootPath() {
    return resourcesPath;
  }

  /** Đường dẫn của alert.css */
  public String getAlertStyle() {
    return alertStyle;
  }
}
