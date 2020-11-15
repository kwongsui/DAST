package pro.kwongsui.dast.linkedlist;

public class DoublyCircularLinkedList<E> {
  private int size;
  DoublyNode<E> sentinel;

  public DoublyCircularLinkedList() {
    doClear();
  }

  private void doClear() {
    sentinel = new DoublyNode<>(null, null, null);
    sentinel.last = sentinel.next = sentinel;
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

  public E remove(int idx) {
    return remove(getNode(idx));
  }

  private E remove(DoublyNode<E> node) {
    node.last.next = node.next;
    node.next.last = node.last;
    size--;
    return node.element;
  }

  public boolean remove(Object o) {
    DoublyNode<E> node = getNode(o);
    if (node != sentinel) {
      remove(node);
      return true;
    } else return false;
  }

  public void clear() {
    doClear();
  }

  public E set(int idx, E e) {
    DoublyNode<E> node = getNode(idx);
    E oldElement = node.element;
    node.element = e;
    return oldElement;
  }

  public E get(int idx) {
    return getNode(idx).element;
  }

  private DoublyNode<E> getNode(int idx) {
    return getNode(idx, 0, size() - 1);
  }

  private DoublyNode<E> getNode(int idx, int lowest, int highest) {
    if (idx < lowest || idx > highest) {
      throw new IndexOutOfBoundsException("Get node index: " + idx + "; size: " + size());
    }

    DoublyNode<E> node;
    if (idx < size() / 2) {
      node = sentinel.next;
      for (int i = 0; i < idx; i++) {
        node = node.next;
      }
    } else {
      node = sentinel;
      for (int i = size(); i > idx; i--) {
        node = sentinel.last;
      }
    }
    return node;
  }

  public DoublyNode<E> getNode(Object o) {
    DoublyNode<E> node = sentinel.next;
    while (node != sentinel && !node.element.equals(o)) {
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

    DoublyNode<E> node = sentinel.next;
    int position = 0;
    while (node != sentinel) {
      builder.append(node.element);

      if (position != size - 1) builder.append(", ");

      node = node.next;
      ++position;
    }

    builder.append("]");

    return builder.toString();
  }
}
