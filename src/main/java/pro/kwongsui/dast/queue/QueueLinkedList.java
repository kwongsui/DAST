package pro.kwongsui.dast.queue;

public class QueueLinkedList<E> {
  private Node<E> head, tail;

  public QueueLinkedList() {}

  public void enqueue(Object o) {
    Node<E> newNode = new Node<>((E) o);

    if (tail == null) {
      head = tail = newNode;
    } else {
      tail.next = newNode;
      tail = newNode;
    }
  }

  public E dequeue() {
    if (head == null) return null;

    E e = head.element;
    head = head.next;

    if (head == null) tail = null;

    return e;
  }

  static class Node<E> {
    E element;
    Node<E> next;

    Node(E e) {
      element = e;
    }
  }
}
