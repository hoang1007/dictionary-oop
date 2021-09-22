package com.gryffindor.backend;

import com.gryffindor.Config;
import com.gryffindor.backend.utils.DictionaryManagement;

public class AppData {
  public static final AppData INSTANCE = new AppData();
  public final Config config;
  public final DictionaryManagement dictionaryManagement;

  AppData() {
    config = new Config();
    dictionaryManagement = new DictionaryManagement();
  }
}
