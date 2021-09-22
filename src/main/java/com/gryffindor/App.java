package com.gryffindor;

import java.io.IOException;

import com.gryffindor.backend.DictionaryCommandline;
/**
 * Our Dictionary App!
 */
public final class App {
  public static final App INSTANCE = new App();
  public final Config config;

  App() {
    config = new Config();
  }

  /**
   * Main method.
   * 
   * @param args The arguments of the program.
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    DictionaryCommandline.dictionaryBasic();
  }
}
