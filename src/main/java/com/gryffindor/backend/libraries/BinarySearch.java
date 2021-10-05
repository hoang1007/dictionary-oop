package com.gryffindor.backend.libraries;

import com.gryffindor.backend.entities.Dictionary;
import com.gryffindor.backend.entities.Word;

import java.util.List;

public class BinarySearch {

    private final Dictionary dictionaries;

    public BinarySearch() {
        dictionaries = new Dictionary();
    }

    /**
     * Hàm tìm kiếm nhị phân
     * compareTo(): hàm so sánh xâu theo thứ tự bảng chữ cái
     */
    public Word binarySearching(String word_target) {
        int low = 0;
        int high = dictionaries.getAllWords().size()-1;
        int mid;

        while (low <= high) {
            mid = (low + high) / 2;

            if (dictionaries.getAllWords().get(mid).getWordTarget().compareTo(word_target) < 0) {
                low = mid + 1;
            } else if (dictionaries.getAllWords().get(mid).getWordTarget().compareTo(word_target) > 0) {
                high = mid - 1;
            } else {
                return dictionaries.getAllWords().get(mid);
            }
        }

        return null;
    }
}
