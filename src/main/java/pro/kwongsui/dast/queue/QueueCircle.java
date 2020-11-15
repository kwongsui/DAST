package pro.kwongsui.dast.queue;

public class QueueCircle<E> {
  private static final int DEFAULT_CAPACITY = 10;
  private E data[];
  private int head = 0, tail = 0;

  public QueueCircle() {
    this(DEFAULT_CAPACITY);
  }

  public QueueCircle(int capacity) {
    data = (E[]) new Object[capacity];
  }

  public boolean enqueue(Object o) {
    if ((tail + 1) % data.length == head) return false;

    data[tail] = (E) o;

    tail = (tail + 1) % data.length;

    return true;
  }

  public E dequeue() {
    if (head == tail) return null;

    E e = data[head];

    head = (head + 1) % data.length;

    return e;
  }
}
