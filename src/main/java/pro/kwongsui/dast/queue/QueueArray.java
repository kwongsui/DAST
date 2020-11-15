package pro.kwongsui.dast.queue;

public class QueueArray<E> {
  private static final int DEFAULT_CAPACITY = 10;
  private E data[];
  private int head = 0, tail = 0;

  public QueueArray() {
    this(DEFAULT_CAPACITY);
  }

  public QueueArray(int capacity) {
    data = (E[]) new Object[capacity];
  }

  public boolean enqueue(Object o) {
    if (tail == data.length) {
      if (head == 0) return false;
      System.arraycopy(data, head, data, 0, tail - head);
      tail -= head;
      head = 0;
    }

    data[tail++] = (E) o;

    return true;
  }

  public E dequeue() {
    if (head == tail) return null;
    return data[head++];
  }
}
