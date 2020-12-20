package pro.kwongsui.dast.linkedlist;

public class LinkedListAlgorithms {

  /**
   * 单链表反转
   */
  public Node reverse(Node node) {
    Node p = null, q;
    while (node != null) {
      q = node.next;

      node.next = p;
      p = node;

      node = q;
    }

    return p;
  }

  /**
   * 链表中点
   */
  public Node getMiddleNode(Node node) {
    Node p = node, q = node;

    while (q != null && q.next != null) {
      p = p.next;
      q = q.next.next;
    }

    return p;
  }

  /**
   * 链表环检测
   */
  public boolean checkCircle(Node node) {
    Node p = node, q = node;
    while (q != null && q.next != null) {
      p = p.next;
      q = q.next.next;
      // 如果存在环，快慢指针总会指向同一个节点
      if (p == q) {
        return true;
      }
    }
    return false;
  }

  /**
   * 两个有序链表合并
   */
  public Node merge(Node na, Node nb) {
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

    if (na != null) {
      p.next = na;
    }
    if (nb != null) {
      p.next = nb;
    }

    return sentinel.next;
  }

  /**
   * 删除链表倒数第k个节点
   */
  public Node removeLastKth(Node node, int k) {
    Node r = node;
    int i = 1;
    while (r != null && i < k) { // 让r指针先前进k个节点
      r = r.next;
      ++i;
    }

    // 倒数节点数大于链表节点数
    if (r == null) {
      return node;
    }

    Node p = null, q = node;
    while (r.next != null) { // 当r指针走到链表结尾，q指针刚好走到倒数k个节点
      p = q;
      q = q.next;

      r = r.next;
    }

    if (p == null) {
      node = node.next; // k等于节点数，亦即删除第1个节点
    } else {
      p.next = q.next;
    }
    q.next = null;

    return node;
  }
}
