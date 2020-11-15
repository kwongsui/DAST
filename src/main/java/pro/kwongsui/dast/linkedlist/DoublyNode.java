package pro.kwongsui.dast.linkedlist;

public class DoublyNode<E> {
  E element;
  DoublyNode<E> last;
  DoublyNode<E> next;

  DoublyNode(E element, DoublyNode<E> last, DoublyNode<E> next) {
    this.element = element;
    this.last = last;
    this.next = next;
  }
}
