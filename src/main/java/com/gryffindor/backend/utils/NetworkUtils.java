package com.gryffindor.backend.utils;

import com.gryffindor.Status;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class NetworkUtils {
  /**
   * Kiểm tra kết nối internet của máy.
   * @return trạng thái kết nối của máy
   * @see Status
   */
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
