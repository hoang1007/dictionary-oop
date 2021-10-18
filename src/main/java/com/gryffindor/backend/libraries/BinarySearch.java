package com.gryffindor.backend.libraries;

import com.gryffindor.backend.entities.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinarySearch {
  private static int COUNT_WORD = 5;
  private static List<Word> wordList;
  private static Stack<Result> history = new Stack<>();

  /**
   * Hàm tìm kiếm nhị phân.
   *
   * @param wordTarget từ muốn tìm trong danh sách
   * @param low biên dưới của tìm kiếm bị phân
   * @param high biên trên của tìm kiếm nhị phân
   * @return chỉ số của từ tìm kiếm hoặc gần nhất với từ tìm kiếm trong danh sách đã cho được đặt
   *     bởi hàm {@link BinarySearch#setWordList(List)}
   */
  public static int search(String wordTarget, int low, int high) {
    if (low < 0) {
      low = 0;
    }

    if (high > wordList.size() - 1) {
      high = wordList.size() - 1;
    }

    int mid = low;

    while (low <= high) {
      mid = (low + high) / 2;
      String temp = wordList.get(mid).getWordTarget();

      if (temp.length() > wordTarget.length()) {
        temp = temp.substring(0, wordTarget.length());
        System.out.println("temp: " + temp + " " + wordTarget);
      }

      if (temp.compareTo(wordTarget) < 0) {
        low = mid + 1;
      } else if (temp.compareTo(wordTarget) > 0) {
        high = mid - 1;
      } else {
        break;
      }
    }

    while (mid >= 0 && wordList.get(mid).getWordTarget().startsWith(wordTarget)) {
      mid--;
    }

    return mid + 1;
  }

  /** Đặt số từ trả về của một tìm kiếm. */
  public static void setCountWord(int count) {
    COUNT_WORD = count;
  }

  /** Đặt danh sách muốn tìm kiếm. */
  public static void setWordList(List<Word> list) {
    wordList = list;
    history.clear();
  }

  /**
   * Hàm tìm kiếm từ liên tục dựa trên các vùng tìm kiếm trước đó.
   *
   * @param wordTarget từ muốn tìm kiếm
   * @param dir hướng tìm kiếm dir = 1: thêm một kí tự dir = -1: bớt một kí tự
   * @return danh cách các từ bắt đầu bằng {@link wordTarget}
   */
  public static List<Word> searchAdvanced(String wordTarget, int dir) {
    if (history.isEmpty()) {
      Result res = new Result(0, Integer.MAX_VALUE);
      res.index = search(wordTarget, res.low, res.high);
      history.push(res);
    } else if (dir < 0) {
      System.out.println("roll back...");
      history.pop();
    } else if (dir > 0) {
      System.out.println("dir > 0");

      String oldRes = wordList.get(history.peek().index).getWordTarget();
      // so sanh tu truoc do voi tu muon tim
      // neu tu muon tim lon hon thi tim trong doan ben phai cua tu truoc do
      // neu khong tim tim ben trai
      if (oldRes.length() > wordTarget.length()) {
        oldRes = oldRes.substring(0, wordTarget.length());
      }

      int compare = wordTarget.compareTo(oldRes);

      if (compare == 0) {
        history.push(history.peek());
      } else if (compare < 0) { // wordTarget < oldRes
        // tim ben trai
        Result res = new Result(history.peek().low, history.peek().index);

        res.index = search(wordTarget, res.low, res.high);
        history.push(res);
      } else {
        // tim ben phai
        Result res = new Result(history.peek().index, history.peek().high);
        res.index = search(wordTarget, res.low, res.high);

        history.push(res);
      }
    }

    System.out.println("his: " + history.size());

    return getNeightbors(history.peek().index, COUNT_WORD);
  }

  /**
   * Trả về danh sách từ từ vị trí index.
   *
   * @param index
   * @param count
   * @return
   */
  private static List<Word> getNeightbors(int index, int count) {
    List<Word> neighbors = new ArrayList<>();
    int left = index;
    int right = index + 1;

    for (; left > 0 && right < wordList.size() && count > 0; count -= 2) {
      neighbors.add(wordList.get(left--));
      neighbors.add(wordList.get(right++));
    }

    for (; left > 0 && count > 0; count--) {
      neighbors.add(wordList.get(left--));
    }

    for (; right < wordList.size() && count > 0; count--) {
      neighbors.add(wordList.get(right++));
    }

    return neighbors;
  }

  // Lưu biên và index của từ tìm được
  // để dùng cho truy vết từ
  private static class Result {
    int low;
    int high;
    int index;

    public Result(int low, int high) {
      this.low = low;
      this.high = high;
    }
  }
}
