package com.gryffindor;

import java.io.IOException;

import com.gryffindor.backend.DictionaryCommandline;

/**
 * Our Dictionary App!
 */
public final class App {
  /**
   * Main method.
   * 
   * @param args The arguments of the program.
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    new DictionaryCommandline().dictionaryBasic();
  }
}
