package com.gryffindor.backend.utils;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import com.gryffindor.Status;

public class NetworkUtils {
  public static Status networkStatus() {
    try {
      URL url = new URL("http://www.google.com");
      URLConnection connection = url.openConnection();
      connection.connect();

      return Status.ONLINE;
    } catch (IOException e) {
      return Status.OFFLINE;
    }
  }
}
