package pro.kwongsui.dast.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CircularDequeTest {

    @Test
    void test() {
        CircularDeque circularDeque = new CircularDeque(3);
        assertTrue(circularDeque.insertLast(1));
        assertTrue(circularDeque.insertLast(2));
        assertTrue(circularDeque.insertFront(3));
        assertFalse(circularDeque.insertFront(4));
        assertEquals(2, circularDeque.getRear());
        assertTrue(circularDeque.isFull());
        assertTrue(circularDeque.deleteLast());
        assertTrue(circularDeque.insertFront(4));
        assertEquals(4, circularDeque.getFront());
        assertTrue(circularDeque.deleteFront());
        assertEquals(3, circularDeque.getFront());
    }
}