package pro.kwongsui.dast.queue;

public class QueueArray {

  private final int[] data;
  private int head = 0, tail = 0;

  public QueueArray(int capacity) {
    data = new int[capacity];
  }

  public boolean enqueue(int val) {
    if (tail == data.length) {
      if (head == 0) {
        return false;
      }
      System.arraycopy(data, head, data, 0, tail - head);
      tail -= head;
      head = 0;
    }
    data[tail++] = val;
    return true;
  }

  public int dequeue() {
    if (head == tail) {
      return -1;
    }
    return data[head++];
  }
}
