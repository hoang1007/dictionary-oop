package com.gryffindor.backend.libraries;

import com.gryffindor.backend.entities.Dictionary;
import com.gryffindor.backend.entities.Word;

import java.util.List;

public class BinarySearch {

    private final Dictionary dictionaries;
    private final List<Word> words;

    public BinarySearch() {
        dictionaries = new Dictionary();
        words = dictionaries.getAllWords();
    }

    /**
     * Hàm tìm kiếm nhị phân
     */
    public Word binarySearching(String word_target) {
        int low = 0;
        int high = words.size()-1;
        int mid;

        while (low <= high) {
            mid = (low + high) / 2;

            if (words.get(mid).getWordTarget().compareTo(word_target) < 0) {
                low = mid + 1;
            } else if (words.get(mid).getWordTarget().compareTo(word_target) > 0) {
                high = mid - 1;
            } else {
                return words.get(mid);
            }
        }

        return null;
    }
}
