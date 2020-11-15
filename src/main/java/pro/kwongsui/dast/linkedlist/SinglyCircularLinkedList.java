package pro.kwongsui.dast.linkedlist;

public class SinglyCircularLinkedList<E> {
  private int size;
  private SinglyNode<E> sentinel;

  public SinglyCircularLinkedList() {
    doClear();
  }

  private void doClear() {
    sentinel = new SinglyNode<>(null);
    sentinel.next = sentinel;
    size = 0;
  }

  public int size() {
    return size;
  }

  public void add(E e) {
    add(size(), e);
  }

  public void add(int idx, E e) {
    addBefore(getNode(idx, 0, size()), e);
  }

  private void addBefore(SinglyNode<E> n, E e) {
    SinglyNode<E> newNode = new SinglyNode<>(e);

    SinglyNode<E> last = sentinel;
    SinglyNode<E> current = sentinel.next;
    while (current != n) {
      last = current;
      current = current.next;
    }

    newNode.next = current;
    last.next = newNode;

    size++;
  }

  public E remove(int idx) {
    return remove(getNode(idx));
  }

  private E remove(SinglyNode<E> n) {
    SinglyNode<E> last = sentinel;
    SinglyNode<E> current = sentinel.next;

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

    if (node != sentinel) {
      remove(node);
      return true;
    } else return false;
  }

  public void clear() {
    doClear();
  }

  public E set(int idx, E e) {
    SinglyNode<E> node = getNode(idx);
    E oldElement = node.element;
    node.element = e;
    return oldElement;
  }

  public E get(int idx) {
    return getNode(idx).element;
  }

  private SinglyNode<E> getNode(int idx) {
    return getNode(idx, 0, size() - 1);
  }

  private SinglyNode<E> getNode(int idx, int lowest, int highest) {
    if (idx < lowest || idx > highest)
      throw new IndexOutOfBoundsException("Get node index: " + idx + "; size: " + size());

    SinglyNode<E> node = sentinel.next;
    int position = 0;
    while (node != sentinel && position != idx) {
      node = node.next;
      ++position;
    }

    return node;
  }

  public SinglyNode<E> getNode(Object o) {
    SinglyNode<E> node = sentinel.next;
    while (node != sentinel && !node.element.equals(o)) {
      node = node.next;
    }

    return node;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("[");

    SinglyNode<E> node = sentinel.next;
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
