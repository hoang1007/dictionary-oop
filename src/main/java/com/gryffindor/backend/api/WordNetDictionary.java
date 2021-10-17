package com.gryffindor.backend.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.data.Word;
import net.sf.extjwnl.dictionary.Dictionary;

public class WordNetDictionary {
  private static Dictionary dictionary;

  static {
    try {
      dictionary = Dictionary.getDefaultResourceInstance();
    } catch (JWNLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Lấy từ đồng nghĩa.
   *
   * @param word
   * @param pos
   * @return List<String>
   */
  public static List<String> getSynonyms(String word, POS pos) {
    Synset synset = Util.getCommonSynset(word, pos);

    if (synset == null) {
      return List.of();
    } else {
      return Util.getLemmas(synset);
    }
  }

  /**
   * Lấy các từ đồng nghĩa.
   *
   * @param word
   * @return List<String>
   */
  public static List<String> getSynonyms(com.gryffindor.backend.entities.Word word) {
    List<String> adj = getSynonyms(word.getWordTarget(), POS.ADJECTIVE);
    List<String> noun = getSynonyms(word.getWordTarget(), POS.NOUN);
    List<String> adv = getSynonyms(word.getWordTarget(), POS.ADVERB);
    List<String> verb = getSynonyms(word.getWordTarget(), POS.VERB);

    return Stream.of(adj, noun, adv, verb).flatMap(Collection::stream).collect(Collectors.toList());
  }

  /**
   * Lấy toàn bộ từ trong database.
   *
   * @return List<String>
   */
  public static List<String> getAllWords() {
    List<String> words = new ArrayList<>();

    try {
      Iterator<IndexWord> wordItr = dictionary.getIndexWordIterator(POS.NOUN);
      for (IndexWord word = wordItr.next(); word != null; word = wordItr.next()) {
        words.add(word.getLemma());
      }
    } catch (JWNLException e) {
      e.printStackTrace();
    }

    return words;
  }

  static class Util {
    /**
     * Getter.
     *
     * @param word
     * @param pos
     * @return
     */
    private static Synset getCommonSynset(String word, POS pos) {
      try {
        IndexWord indexWord = dictionary.lookupIndexWord(pos, word);

        if (indexWord != null) {
          return indexWord.getSenses().get(0);
        }
      } catch (JWNLException e) {
        e.printStackTrace();
      }

      return null;
    }

    // private static List<Synset> getCommonSynsets(String word, POS pos) {
    // try {
    // IndexWord indexWord = dictionary.lookupIndexWord(pos, word);

    // return indexWord.getSenses();
    // } catch (JWNLException e) {
    // e.printStackTrace();
    // return null;
    // }
    // }

    /**
     * @param synset
     * @return
     */
    private static List<String> getLemmas(Synset synset) {
      List<Word> words = synset.getWords();

      List<String> lemmas = new ArrayList<>();

      for (Word word : words) {
        String lemma = word.getLemma().replace('_', ' ');

        lemmas.add(lemma);
      }

      return lemmas;
    }
  }
}
