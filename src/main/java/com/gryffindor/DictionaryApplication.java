package com.gryffindor;

import com.gryffindor.backend.utils.DictionaryManagement;
import com.gryffindor.frontend.ApplicationUI;

import javafx.application.Application;

/**
 * Our Dictionary App!.
 */
public final class DictionaryApplication {
  public static final DictionaryApplication INSTANCE;

  public final Config config;
  public final DictionaryManagement dictionaryManagement;
  public final ResourcesManager resourcesManager;
  public final ExceptionHandler exceptionHandler;

  DictionaryApplication() {
    exceptionHandler = new ExceptionHandler();
    Thread.setDefaultUncaughtExceptionHandler(exceptionHandler);

    config = new Config();
    dictionaryManagement = new DictionaryManagement();
    resourcesManager = new ResourcesManager();
  }

  static {
    INSTANCE = new DictionaryApplication();
    INSTANCE.dictionaryManagement.addDataFromFile();
    System.out.println("SIZE OF LIST WORDS : = " + INSTANCE.dictionaryManagement.getDictionary().getAllWords().size());
  }

  /**
   * Main method.
   * 
   * @param args The arguments of the program.
   * @throws Exception lỗi khi khởi chạy
   */
  public static void main(String[] args) throws Exception {
    INSTANCE.resourcesManager.loadSuggest();
    System.out.println(INSTANCE.config.getRootPath());
    INSTANCE.runApplication();
    INSTANCE.resourcesManager.free();
    INSTANCE.exceptionHandler.free();
  }

  /** Run UI. */
  void runApplication() {
    Application.launch(ApplicationUI.class);
  }

  public DictionaryManagement getDictionaryManagement() {
    return dictionaryManagement;
  }
}