package pro.kwongsui.dast.linkedlist;

public class LinkedListAlgorithms<E> {
  static class Node {
    int value;
    Node next;

    Node(int v) {
      value = v;
    }
  }

  /** 单链表反转 */
  public static Node reverse(Node node) {
    Node p = null, q;
    while (node != null) {
      q = node.next;

      node.next = p;
      p = node;

      node = q;
    }

    return p;
  }

  /** 链表中点 */
  public static Node getMiddleNode(Node node) {
    Node p = node, q = node;

    while (q != null && q.next != null) {
      p = p.next;
      q = q.next.next;
    }

    return p;
  }

  /** 链表环检测 */
  public static boolean checkCircle(Node node) {
    Node p = node, q = node;
    while (q != null && q.next != null) {
      p = p.next;
      q = q.next.next;

      if (p == q) return true; // 如果存在环，快慢指针总会指向同一个节点
    }
    return false;
  }

  /** 两个有序链表合并 */
  public static Node merge(Node na, Node nb) {
    Node sentinel = new Node(-1);

    Node p = sentinel;

    while (na != null && nb != null) {
      if (na.value <= nb.value) {
        p.next = na;
        na = na.next;
      } else {
        p.next = nb;
        nb = nb.next;
      }
      p = p.next;
    }

    if (na != null) p.next = na;
    if (nb != null) p.next = nb;

    return sentinel.next;
  }

  /** 删除链表倒数第k个节点 */
  public static Node removeLastKth(Node node, int k) {
    Node r = node;
    int i = 1;
    while (r != null && i < k) { // 让r指针先前进k个节点
      r = r.next;
      ++i;
    }

    // 倒数节点数大于链表节点数
    if (r == null) return node;

    Node p = null, q = node;
    while (r.next != null) { // 当r指针走到链表结尾，q指针刚好走到倒数k个节点
      p = q;
      q = q.next;

      r = r.next;
    }

    if (p == null) node = node.next; // k等于节点数，亦即删除第1个节点
    else p.next = q.next;

    return node;
  }

  public static void print(Node node) {
    while (node != null) {
      System.out.print(node.value + " ");
      node = node.next;
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Node node0 = new Node(1);
    Node node1 = new Node(2);
    Node node2 = new Node(4);
    Node node3 = new Node(6);
    Node node4 = new Node(8);
    node0.next = node1;
    node1.next = node2;
    node2.next = node3;
    node3.next = node4;
    //    node4.next = node0;
    //    print(node0);
    //    print(reverse(node0));

    //    System.out.println(getMiddleNode(node0).value);
    //    System.out.println(checkCircle(node0));
    Node node5 = new Node(0);
    Node node6 = new Node(3);
    Node node7 = new Node(5);
    node5.next = node6;
    node6.next = node7;
    //    print(node5);

    print(merge(node0, node5));
    //    print(removeLastKth(node0, 6));
  }
}
