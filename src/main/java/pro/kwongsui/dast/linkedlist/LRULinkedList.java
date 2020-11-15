package pro.kwongsui.dast.linkedlist;

public class LRULinkedList<E> {
  private static final int DEFAULT_CAPACITY = 5;

  private int capacity;
  private int count;

  private SinglyNode<E> head;

  public LRULinkedList() {
    this(DEFAULT_CAPACITY);
  }

  public LRULinkedList(int capacity) {
    this.capacity = capacity;
    count = 0;
    head = new SinglyNode<>(null);
  }

  public void cache(E object) {
    if (object == null) throw new IllegalArgumentException("Null not supported");

    if (isCached(object)) {
      if (object.equals(head.element)) return;

      SinglyNode<E> previous = head;
      SinglyNode<E> current = head.next;
      for (int i = 1; i < count; i++) {
        if (object.equals(current.element)) {
          previous.next = current.next;

          SinglyNode<E> newSinglyNode = new SinglyNode<>(object);
          newSinglyNode.next = head;
          head = newSinglyNode;
        }
        previous = previous.next;
        current = current.next;
      }
    } else {
      if (count < capacity) {
        SinglyNode<E> newSinglyNode = new SinglyNode<>(object);
        newSinglyNode.next = head;
        head = newSinglyNode;

        count++;
      } else {
        SinglyNode<E> previous = head;
        for (int i = 2; i < count; i++) {
          previous = previous.next;
        }
        previous.next = null;

        SinglyNode<E> newSinglyNode = new SinglyNode<>(object);
        newSinglyNode.next = head;
        head = newSinglyNode;
      }
    }
  }

  private boolean isCached(E object) {
    SinglyNode<E> current = head;
    for (int i = 0; i < count; i++) {
      if (object.equals(current.element)) return true;
      current = current.next;
    }

    return false;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder(count);
    SinglyNode<E> current = head;
    for (int i = 0; i < count; i++) {
      builder.append(current.element).append(" ");
      current = current.next;
    }
    return builder.toString();
  }

  public static void main(String[] args) {
    LRULinkedList<Integer> lru = new LRULinkedList<>();
    lru.cache(11);
    lru.cache(12);
    lru.cache(13);
    lru.cache(14);
    lru.cache(15);
    System.out.println(lru);
    lru.cache(16);
    lru.cache(17);
    lru.cache(18);
    System.out.println(lru);
    lru.cache(14);
    System.out.println(lru);
    lru.cache(11);
    System.out.println(lru);
    lru.cache(14);
    System.out.println(lru);
    lru.cache(19);
    System.out.println(lru);
  }
}
