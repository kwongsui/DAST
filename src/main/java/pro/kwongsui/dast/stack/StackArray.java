package pro.kwongsui.dast.stack;

public class StackArray<E> {
  private static final int DEFAULT_CAPACITY = 10;
  private E[] data;
  private int capacity;
  private int size;

  public StackArray() {
    this(DEFAULT_CAPACITY);
  }

  public StackArray(int capacity) {
    data = (E[]) new Object[capacity];
    this.capacity = capacity;
    size = 0;
  }

  public void push(Object o) {
    if (size == capacity) resize();
    data[size++] = (E) o;
  }

  private void resize() {
    E[] newData = (E[]) new Object[capacity * 2];
    System.arraycopy(data, 0, newData, 0, size);
    data = newData;
  }

  public E pop() {
    if (isEmpty()) return null;
    return data[size-- - 1];
  }

  public E peek() {
    if (isEmpty()) return null;
    return data[size - 1];
  }

  public boolean isEmpty() {
    return size == 0;
  }
}
