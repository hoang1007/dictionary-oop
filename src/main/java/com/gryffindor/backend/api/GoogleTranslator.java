package com.gryffindor.backend.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.gryffindor.Language;
import com.gryffindor.backend.AppData;

public class GoogleTranslator {

  /**
   * @param word_target
   * @param from
   * @param to
   * @return String
   * @throws IOException
   */
  public static String translate(String word_target, Language from, Language to) throws IOException {
    String urlString = AppData.INSTANCE.config.getGoogleAPIUrl() + "?q=" + URLEncoder.encode(word_target, "UTF-8")
        + "&target=" + to.toShortString() + "&from=" + from.toShortString();

    URL url = new URL(urlString);

    StringBuffer response = new StringBuffer();

    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestProperty("User-Agent", "Mozilla/5.0");

    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

    for (String inputLine; (inputLine = reader.readLine()) != null;) {
      response.append(inputLine);
    }

    reader.close();
    return response.toString();
  }
}
