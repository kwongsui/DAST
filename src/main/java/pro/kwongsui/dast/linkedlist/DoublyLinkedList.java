package pro.kwongsui.dast.linkedlist;

public class DoublyLinkedList<E> {
  private int size;

  private DoublyNode<E> beginMarker;
  private DoublyNode<E> endMarker;

  public DoublyLinkedList() {
    doClear();
  }

  private void doClear() {
    beginMarker = new DoublyNode<>(null, null, null);
    endMarker = new DoublyNode<>(null, beginMarker, null);
    beginMarker.next = endMarker;
    size = 0;
  }

  public void add(E e) {
    add(size(), e);
  }

  public void add(int idx, E e) {
    addBefore(getNode(idx, 0, size()), e);
  }

  private void addBefore(DoublyNode<E> node, E e) {
    DoublyNode<E> newNode = new DoublyNode<>(e, node.last, node);

    node.last.next = newNode;
    node.last = newNode;

    size++;
  }

  public E set(int idx, E e) {
    DoublyNode<E> node = getNode(idx);
    E oldElement = node.element;
    node.element = e;
    return oldElement;
  }

  public E remove(int idx) {
    return remove(getNode(idx));
  }

  private E remove(DoublyNode<E> n) {
    n.last.next = n.next;
    n.next.last = n.last;

    size--;

    return n.element;
  }

  public boolean remove(Object o) {
    DoublyNode<E> node = getNode(o);

    if (node.next != null) {
      remove(node);
      return true;
    } else return false;
  }

  public void clear() {
    doClear();
  }

  public E get(int idx) {
    return getNode(idx).element;
  }

  private DoublyNode<E> getNode(int idx) {
    return getNode(idx, 0, size() - 1);
  }

  private DoublyNode<E> getNode(int idx, int lowest, int highest) {
    if (idx < lowest || idx > highest)
      throw new IndexOutOfBoundsException("Get node index: " + idx + "; size: " + size());

    DoublyNode<E> node;
    if (idx < size() / 2) {
      node = beginMarker.next;
      for (int i = 0; i < idx; i++) {
        node = node.next;
      }
    } else {
      node = endMarker;
      for (int i = size(); i > idx; i--) {
        node = node.last;
      }
    }

    return node;
  }

  public DoublyNode<E> getNode(Object o) {
    DoublyNode<E> node = beginMarker.next;
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

    DoublyNode<E> node = beginMarker.next;
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
