package pro.kwongsui.dast.linkedlist;

public class SinglyLinkedList {

  private final Node sentinel;

  public SinglyLinkedList() {
    sentinel = new Node();
  }

  public void add(int val) {
    Node newNode = new Node(val);
    newNode.next = sentinel.next;
    sentinel.next = newNode;
  }

  public void remove(int val) {
    Node p = sentinel, q = sentinel.next;
    while (q != null && q.value != val) {
      p = q;
      q = q.next;
    }
    if (q != null) {
      p.next = q.next;
      q.next = null;
    }
  }

  public Node get(int val) {
    Node p = sentinel.next;
    while (p != null && p.value != val) {
      p = p.next;
    }
    return p;
  }
}
