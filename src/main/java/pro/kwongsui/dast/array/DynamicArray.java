package pro.kwongsui.dast.array;

import lombok.Getter;

public class DynamicArray {

  @Getter
  private int[] data;
  private int size = 0;

  public DynamicArray(int capacity) {
    data = new int[capacity];
  }

  public void add(int index, int value) {
    if (index < 0) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    if (index > size) {
      index = size;
    }
    if (size >= data.length) {
      resize();
    }
    if (size - index >= 0) {
      System.arraycopy(data, index, data, index + 1, size - index);
    }
    data[index] = value;
    size++;
  }

  private void resize() {
    int[] newData = new int[size * 2];
    System.arraycopy(data, 0, newData, 0, size);
    data = newData;
  }

  public void remove(int index) {
    checkIndex(index);
    if (size - index - 1 > 0) {
      System.arraycopy(data, index + 1, data, index, size - index - 1);
    }
    size--;
  }

  private void checkIndex(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
  }

  public int set(int index, int value) {
    int old = get(index);
    data[index] = value;
    return old;
  }

  public int get(int index) {
    checkIndex(index);
    return data[index];
  }
}
