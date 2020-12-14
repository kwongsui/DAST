package pro.kwongsui.dast.stack;

import pro.kwongsui.dast.linkedlist.Node;

public class StackLinkedList {

  private Node top = null;

  public void push(int val) {
    Node newNode = new Node(val);
    if (top != null) {
      newNode.next = top;
    }
    top = newNode;
  }

  public int pop() {
    if (top == null) {
      return -1;
    }
    int val = top.value;
    top = top.next;
    return val;
  }

  public int peek() {
    if (top == null) {
      return -1;
    }
    return top.value;
  }
}
