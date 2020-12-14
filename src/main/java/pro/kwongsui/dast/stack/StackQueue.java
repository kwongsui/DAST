package pro.kwongsui.dast.stack;

import java.util.LinkedList;
import java.util.Queue;

public class StackQueue<E> {

  private final Queue<E> data = new LinkedList<>();

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

}
