package com.gryffindor;

import com.gryffindor.backend.api.WordNetDictionary;
import com.gryffindor.backend.utils.DictionaryManagement;
import com.gryffindor.frontend.ApplicationUI;

import java.io.IOException;

import javafx.application.Application;

/**
 * Our Dictionary App!.
 */
public final class DictionaryApplication {
  public static final DictionaryApplication INSTANCE = new DictionaryApplication();

  public final Config config;
  public final DictionaryManagement dictionaryManagement;
  public final ResourcesManager resourcesManager;

  DictionaryApplication() {
    config = new Config();
    dictionaryManagement = new DictionaryManagement();
    resourcesManager = new ResourcesManager();
  }

  /**
   * Main method.
   * 
   * @param args The arguments of the program.
   * @throws Exception lỗi khi khởi chạy
   */
  public static void main(String[] args) throws Exception {
    // new DictionaryCommandline().dictionaryBasic();
    INSTANCE.runApplication();

    INSTANCE.resourcesManager.free();
  }

  /** Run UI. */
  void runApplication() {
    Application.launch(ApplicationUI.class);
  }

  public DictionaryManagement getDictionaryManagement() {
    return dictionaryManagement;
  }
}