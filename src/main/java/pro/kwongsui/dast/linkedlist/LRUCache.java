package pro.kwongsui.dast.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private final int capacity;
    private final Node head, tail;
    private final Map<Integer, Node> map = new HashMap<>();
    private int count;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        count = 0;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        } else {
            remove(node);
            add(node);
            return node.value;
        }
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node == null) {
            if (count == capacity) {
                map.remove(tail.prev.key);
                remove(tail.prev);
                count--;
            }
            Node newNode = new Node(key, value);
            add(newNode);
            map.put(key, newNode);
            count++;
        } else {
            remove(node);
            add(node);
            node.value = value;
        }
    }

    private void add(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
