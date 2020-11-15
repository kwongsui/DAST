package pro.kwongsui.dast.stack;

import java.util.Deque;
import java.util.LinkedList;

public class StackQueue<E> {
  private Deque<E> data = new LinkedList<>();

  public StackQueue() {}

  public void push(E e) {
    int n = data.size();

    data.offer(e);

    for (int i = 0; i < n; i++) {
      data.offer(data.poll());
    }
  }

  public E pop() {
    return data.poll();
  }

  public E peek() {
    return data.peek();
  }

  public boolean isEmpty() {
    return data.isEmpty();
  }
}
