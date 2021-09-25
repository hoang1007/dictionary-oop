package com.gryffindor;

import java.io.IOException;
import java.util.Scanner;
import java.util.List;

import com.gryffindor.backend.DictionaryCommandline;
import com.gryffindor.backend.api.GoogleTranslator;
import com.gryffindor.backend.utils.DictionaryManagement;
import com.gryffindor.backend.entities.*;

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
  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    DictionaryManagement dic = new DictionaryManagement();
    dic.insertFromFile();
    dic.dictionaryLookup();
  }
}
