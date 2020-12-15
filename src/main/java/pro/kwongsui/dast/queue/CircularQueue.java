package pro.kwongsui.dast.queue;

public class CircularQueue {

    private final int[] data;
    private final int capacity;
    private int head, tail;

    public CircularQueue(int k) {
        capacity = k + 1;
        data = new int[capacity];
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        data[tail] = value;
        tail = (tail + 1) % capacity;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        head = (head + 1) % capacity;
        return true;
    }

    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return data[head];
    }

    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return tail == 0 ? data[capacity - 1] : data[tail - 1];
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public boolean isFull() {
        return (tail + 1) % capacity == head;
    }
}
