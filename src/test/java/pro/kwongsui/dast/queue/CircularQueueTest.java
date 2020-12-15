package pro.kwongsui.dast.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CircularQueueTest {

    @Test
    void test() {
        CircularQueue circularQueue = new CircularQueue(3);
        assertTrue(circularQueue.enQueue(1));
        assertTrue(circularQueue.enQueue(2));
        assertTrue(circularQueue.enQueue(3));
        assertFalse(circularQueue.enQueue(4));
        assertEquals(1, circularQueue.Front());
        assertEquals(3, circularQueue.Rear());
        assertTrue(circularQueue.isFull());
        assertTrue(circularQueue.deQueue());
        assertTrue(circularQueue.enQueue(4));
        assertEquals(2, circularQueue.Front());
        assertEquals(4, circularQueue.Rear());
    }
}