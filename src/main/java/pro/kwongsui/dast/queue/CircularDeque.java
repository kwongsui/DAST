package pro.kwongsui.dast.queue;

public class CircularDeque {

    private final int[] data;
    private final int capacity;
    private int head, tail;

    public CircularDeque(int k) {
        capacity = k + 1;
        data = new int[capacity];
    }

    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        head = (head == 0) ? capacity - 1 : head - 1;
        data[head] = value;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        data[tail] = value;
        tail = (tail + 1) % capacity;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        head = (head + 1) % capacity;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        tail = (tail == 0) ? capacity - 1 : tail - 1;
        return true;
    }

    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return data[head];
    }

    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        if (tail == 0) {
            return data[capacity - 1];
        }
        return data[tail - 1];
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public boolean isFull() {
        return (tail + 1) % capacity == head;
    }
}
