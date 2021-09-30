package com.gryffindor.frontend.utils;

import java.util.Arrays;

import javafx.collections.ObservableList;

/** Công cụ thao tác trên ListView khi muốn giới hạn số lượng. */
public class BlockingListUtils<T> {
  private final int limit;
  private ObservableList<T> list;

  public BlockingListUtils(int limit, ObservableList<T> list) {
    this.limit = limit;
    this.list = list;
  }

  /**
   * Thêm phần tử vào cuối danh sách nếu danh sách chứa ít hơn số lượng đã đặt.
   * @param element phần tử muốn thêm vào danh sách
   */
  public void add(T element) {
    if (list.size() < limit) {
      list.add(element);
    }
  }

  /**
   * Thêm các phần tử vào cuối danh sách với giới hạn đặt trước.
   * @param elements các phần tử muốn thêm
   */
  @SuppressWarnings("unchecked")
  public void addAll(T... elements) {
    for (T element : elements) {
      add(element);
    }
  }

  /**
   * Thay thế phần tử ở vị trí cho trước.
   * @param index vị trí muốn thay thế
   * @param element giá trị muốn thay thế
   */
  public void set(int index, T element) {
    if (list.size() == 0) {
      list.add(element);
    } else if (index < limit) {
      list.set(index, element);
    }
  }

  /**
   * Xóa hết các phần tử trong danh sách
   * và thay thế bằng các phần tử mới.
   * @param elements các phần tử muốn thay thế
   */
  @SuppressWarnings("unchecked")
  public void setAll(T... elements) {
    if (elements.length > limit) {
      elements = Arrays.copyOf(elements, limit);
    }

    list.setAll(elements);
  }

  public int getLimit() {
    return limit;
  }
}
