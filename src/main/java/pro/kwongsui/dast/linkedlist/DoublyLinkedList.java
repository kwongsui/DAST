package pro.kwongsui.dast.linkedlist;

public class DoublyLinkedList {

  private final Node head, tail;

  public DoublyLinkedList() {
    head = new Node();
    tail = new Node();
    head.next = tail;
    tail.prev = head;
  }

  public void add(int val) {
    Node newNode = new Node(val);
    newNode.prev = head;
    newNode.next = head.next;
    head.next.prev = newNode;
    head.next = newNode;
  }

  public void remove(int val) {
    Node p = head.next;
    while (p != tail && p.value != val) {
      p = p.next;
    }
    if (p != tail) {
      p.prev.next = p.next;
      p.next.prev = p.prev;
      p.prev = null;
      p.next = null;
    }
  }

  public Node get(int val) {
    Node p = head.next;
    while (p != tail && p.value != val) {
      p = p.next;
    }
    if (p != tail) {
      return p;
    } else {
      return null;
    }
  }
}
