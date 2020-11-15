package pro.kwongsui.dast.linkedlist;

public class SinglyLinkedList<E> {
  private int size;
  private SinglyNode<E> beginMarker;
  private SinglyNode<E> endMarker;

  public SinglyLinkedList() {
    doClear();
  }

  private void doClear() {
    beginMarker = new SinglyNode<>(null);
    endMarker = new SinglyNode<>(null);
    beginMarker.next = endMarker;
    size = 0;
  }

  public SinglyNode<E> getBeginMarker() {
    return beginMarker;
  }

  public SinglyNode<E> getEndMarker() {
    return endMarker;
  }

  public void add(E e) {
    add(size(), e);
  }

  public void add(int idx, E e) {
    addBefore(getNode(idx, 0, size()), e);
  }

  private void addBefore(SinglyNode<E> n, E e) {
    SinglyNode<E> newNode = new SinglyNode<>(e);
    SinglyNode<E> last = beginMarker;
    SinglyNode<E> next = beginMarker.next;

    while (next != n) {
      last = next;
      next = next.next;
    }

    newNode.next = next;
    last.next = newNode;

    size++;
  }

  public E remove(int idx) {
    return remove(getNode(idx));
  }

  private E remove(SinglyNode<E> n) {
    SinglyNode<E> last = beginMarker;
    SinglyNode<E> current = beginMarker.next;

    while (current != n) {
      last = current;
      current = current.next;
    }

    last.next = current.next;

    E element = current.element;

    current.element = null;
    current.next = null;

    size--;

    return element;
  }

  public boolean remove(Object o) {
    SinglyNode<E> node = getNode(o);

    if (node != endMarker) {
      remove(node);
      return true;
    } else return false;
  }

  public void clear() {
    doClear();
  }

  public E set(int idx, E e) {
    SinglyNode<E> node = getNode(idx);
    E old = node.element;
    node.element = e;
    return old;
  }

  public E get(int idx) {
    return getNode(idx).element;
  }

  public SinglyNode<E> getNode(int idx) {
    return getNode(idx, 0, size() - 1);
  }

  private SinglyNode<E> getNode(int idx, int lowest, int highest) {
    if (idx < lowest || idx > highest) {
      throw new IndexOutOfBoundsException("Get Node index: " + idx + "; size: " + size());
    }

    SinglyNode<E> node = beginMarker.next;
    int position = 0;
    while (node != endMarker && position != idx) {
      node = node.next;
      ++position;
    }

    return node;
  }

  public SinglyNode<E> getNode(Object o) {
    SinglyNode<E> node = beginMarker;
    while (node != endMarker && !node.element.equals(o)) {
      node = node.next;
    }

    return node;
  }

  public int size() {
    return size;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("[");

    SinglyNode<E> node = beginMarker.next;
    int position = 0;
    while (node != endMarker) {
      builder.append(node.element);

      if (position != size - 1) builder.append(", ");

      node = node.next;
      ++position;
    }

    builder.append("]");

    return builder.toString();
  }
}
