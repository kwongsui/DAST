package pro.kwongsui.dast.stack;

public class StackArray {

  private int[] data;
  private final int capacity;
  private int size;


  public StackArray(int capacity) {
    data = new int[capacity];
    this.capacity = capacity;
    size = 0;
  }

  public void push(int val) {
    if (size == capacity) {
      resize();
    }
    data[size++] = val;
  }

  private void resize() {
    int[] newData = new int[size * 2];
    System.arraycopy(data, 0, newData, 0, size);
    data = newData;
  }

  public int pop() {
    if (size == 0) {
      return -1;
    }
    return data[size-- - 1];
  }

  public int peek() {
    if (size == 0) {
      return -1;
    }
    return data[size - 1];
  }
}
