package com.gryffindor.backend.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.data.Word;
import net.sf.extjwnl.dictionary.Dictionary;

public class WordNetDictionary {
    private static Dictionary dictionary;

    public WordNetDictionary() {
        try {
            dictionary = Dictionary.getDefaultResourceInstance();
        } catch (JWNLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getSynonyms(String word, POS pos) {
        Synset synset = Util.getCommonSynset(word, pos);

        if (synset == null) {
            return null;
        } else {
            return Util.getLemmas(synset);
        }
    }

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
        private static Synset getCommonSynset(String word, POS pos) {
            Synset synset = null;
            try {
                IndexWord indexWord = dictionary.lookupIndexWord(pos, word);
    
                if (indexWord != null) {
                    synset = indexWord.getSenses().get(0);
                }
            } catch (JWNLException e) {
                e.printStackTrace();
            }
    
            return synset;
        }

        private static List<Synset> getCommonSynsets(String word, POS pos) {
            try {
                IndexWord indexWord = dictionary.lookupIndexWord(pos, word);

                return indexWord.getSenses();
            } catch (JWNLException e) {
                e.printStackTrace();
                return null;
            }
        }

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
