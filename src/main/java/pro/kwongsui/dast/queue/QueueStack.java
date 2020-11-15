package pro.kwongsui.dast.queue;

import java.util.Deque;
import java.util.LinkedList;

public class QueueStack<E> {
  private Deque<E> in = new LinkedList<>();
  private Deque<E> out = new LinkedList<>();
  private E front;

  public void enqueue(E x) {
    if (in.isEmpty()) front = x;
    in.push(x);
  }

  public E dequeue() {
    if (out.isEmpty()) while (!in.isEmpty()) out.push(in.pop());
    return out.pop();
  }

  public E peek() {
    if (out.isEmpty()) return front;
    return out.peek();
  }

  public boolean isEmpty() {
    return in.isEmpty() && out.isEmpty();
  }
}
