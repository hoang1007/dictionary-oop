package com.gryffindor;

import java.io.IOException;
import java.util.Scanner;
import java.util.List;

import com.gryffindor.backend.*;

/**
 * Our Dictionary App.
 */
public final class App {
  /**
   * Main method.
   * 
   * @param args The arguments of the program.
   * @throws IOException
   */

  public static void main(String[] args) throws IOException {
    DictionaryCommandline dic = new DictionaryCommandline();
    dic.getDictionaryManagement().insertFromFile();
    dic.showAllWords();
    dic.getDictionaryManagement().insertFromCommandline();
    dic.getDictionaryManagement().dictionaryExportToFile();
    // error: font Vietnamese in function (insertFromCommandLine and
    // insertFromFile)
  }
}
