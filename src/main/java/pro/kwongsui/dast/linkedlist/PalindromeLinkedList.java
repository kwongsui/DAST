package pro.kwongsui.dast.linkedlist;

public class PalindromeLinkedList {
  private Node head;

  /**
   * 1. 快慢指针找链表中点，慢指针前进一步，快指针前进两步，同时反转链表前半部分
   * 2. 找到中点时，若快指针为null，链表节点数为偶数，否则为奇数
   * 3. 偶数节点数时，直接从中间开始向左右两边比较，奇数节点数时，慢指针正指向中点，指向下一节点再开始比较
   * 4. 比较的同时恢复被反转的前半部分链表
   */
  public boolean isPalindromic() {
    Node slow = head, fast = head, prev = null, next;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;

      next = slow.next;

      /* 反转链表前半部分 */
      slow.next = prev;
      prev = slow;

      slow = next;
    }

    Node mid = slow;
    // 节点数为奇数，slow为中点
    if (fast != null) {
      slow = slow.next;
    }

    boolean flag = true;
    Node left = prev, right = slow;
    while (left != null && right != null) { // 从链表中间向左右两边比较
      if (left.data != right.data) {
        flag = false;
      }

      right = right.next;

      next = left.next;

      /* 恢复被反转的前半部分 */
      left.next = mid;
      mid = left;

      left = next;
    }

    head = mid;

    return flag;
  }

  private static class Node {
    char data;
    Node next;

    Node(char c) {
      data = c;
    }
  }
}