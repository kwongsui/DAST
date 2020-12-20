package pro.kwongsui.dast.queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class QueueStack {

  private final Deque<Integer> in = new ArrayDeque<>();
  private final Deque<Integer> out = new ArrayDeque<>();
  private int front;

  public void enqueue(int x) {
    if (in.isEmpty()) {
      front = x;
    }
    in.push(x);
  }

  public int dequeue() {
    if (out.isEmpty()) {
      while (!in.isEmpty()) {
        out.push(in.pop());
      }
    }
    return out.pop();
  }

  public int peek() {
    if (in.isEmpty() && out.isEmpty()) {
      return -1;
    } else if (out.isEmpty()) {
      return front;
    } else {
      return out.peek();
    }
  }
}
