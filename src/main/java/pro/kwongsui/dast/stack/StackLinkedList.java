package pro.kwongsui.dast.stack;

public class StackLinkedList<E> {
  private Node<E> top = null;

  public void push(Object o) {
    Node<E> newNode = new Node<>((E) o);

    if (top == null) top = newNode;
    else {
      newNode.next = top;
      top = newNode;
    }
  }

  public E pop() {
    if (isEmpty()) return null;

    E e = top.element;
    top = top.next;

    return e;
  }

  public E peek() {
    if (isEmpty()) return null;
    return top.element;
  }

  public boolean isEmpty() {
    return top == null;
  }

  public void clear() {
    top = null;
  }

  static class Node<E> {
    E element;
    Node<E> next;

    Node(E e) {
      element = e;
    }
  }
}
