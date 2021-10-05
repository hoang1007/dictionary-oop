package com.gryffindor.backend.api;

import com.gryffindor.DictionaryApplication;
import com.gryffindor.Language;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GoogleTranslator {
  /**
   * Lấy bản dịch từ google translate.
   * @param wordTarget từ muốn dịch
   * @param from ngôn ngữ dịch
   * @param to ngôn ngữ muốn dịch
   * @return bản dịch
   * @throws IOException không nhận được bản dịch từ google
   */
  public static String translate(String wordTarget, Language from, Language to)
                                                throws IOException {
    String urlString = DictionaryApplication.INSTANCE.config.getGoogleApiUrl()
                    + "?q=" + URLEncoder.encode(wordTarget, "UTF-8")
                    + "&target=" + to.toShortString() + "&from=" + from.toShortString();

    URL url = new URL(urlString);

    StringBuffer response = new StringBuffer();

    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestProperty("User-Agent", "Mozilla/5.0");

    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

    for (String inputLine; (inputLine = reader.readLine()) != null; ) {
      response.append(inputLine);
    }

    reader.close();
    return response.toString();
  }
}
