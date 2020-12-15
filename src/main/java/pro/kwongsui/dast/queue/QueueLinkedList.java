package pro.kwongsui.dast.queue;

import pro.kwongsui.dast.linkedlist.Node;

public class QueueLinkedList {

  private Node head, tail;

  public QueueLinkedList() {}

  public void enqueue(int val) {
    Node newNode = new Node(val);
    if (tail == null) {
      head = tail = newNode;
    } else {
      tail.next = newNode;
      tail = newNode;
    }
  }

  public int dequeue() {
    if (head == null) {
      return -1;
    }
    int val = head.value;
    head = head.next;
    if (head == null) {
      tail = null;
    }
    return val;
  }
}
