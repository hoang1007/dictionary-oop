package com.gryffindor.backend.libraries;

import com.gryffindor.backend.entities.Dictionary;

public class BinarySearch {

  private final Dictionary dictionaries;

  public BinarySearch() {
    dictionaries = new Dictionary();
  }

  /**
   * Hàm tìm kiếm nhị phân compareTo(): hàm so sánh xâu theo thứ tự bảng chữ cái
   * 
   * @return chi so cua tu tim thay
   */
  public int binarySearching(String wordTarget, int low, int high) {
    if (low < 0) {
      low = 0;
    }

    if (high > dictionaries.getAllWords().size() - 1) {
      high = dictionaries.getAllWords().size() - 1;
    }

    int mid = low;

    while (low <= high) {
      mid = (low + high) / 2;

      if (dictionaries.getAllWords().get(mid).getWordTarget().compareTo(wordTarget) < 0) {
        low = mid + 1;
      } else if (dictionaries.getAllWords().get(mid).getWordTarget().compareTo(wordTarget) > 0) {
        high = mid - 1;
      } else {
        return mid;
      }
    }

    return mid;
  }
}
