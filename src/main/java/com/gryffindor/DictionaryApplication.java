package com.gryffindor;

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
   * @throws IOException lỗi khi khởi chạy đồ họa
   */
  public static void main(String[] args) throws IOException {
    // new DictionaryCommandline().dictionaryBasic();
    INSTANCE.runApplication();

    INSTANCE.resourcesManager.free();
  }

  /** Run UI. */
  void runApplication() {
    Application.launch(ApplicationUI.class);
  }
}