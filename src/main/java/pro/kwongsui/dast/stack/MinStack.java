package pro.kwongsui.dast.stack;

import java.util.Deque;
import java.util.LinkedList;

public class MinStack {
  Deque<Integer> data;
  Deque<Integer> min;

  public MinStack() {
    data = new LinkedList<>();
    min = new LinkedList<>();
  }

  public void push(int x) {
    data.push(x);
    if (min.isEmpty() || x <= min.peek()) {
      min.push(x);
    }
  }

  public void pop() {
    if (data.isEmpty()) {
      throw new RuntimeException("No data");
    }
    if (data.pop().equals(min.peek())) {
      min.pop();
    }
  }

  public int top() {
    if (data.isEmpty()) {
      throw new RuntimeException("No data");
    }
    return data.peek();
  }

  public int getMin() {
    if (min.isEmpty()) {
      throw new RuntimeException("No data");
    }
    return min.peek();
  }
}
