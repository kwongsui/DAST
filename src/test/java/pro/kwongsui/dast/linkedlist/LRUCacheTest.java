package pro.kwongsui.dast.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LRUCacheTest {

    LRUCache cache;

    @BeforeEach
    void setUp() {
        cache = new LRUCache(2);
    }

    @Test
    void test() {
        cache.put(1, 1);
        cache.put(2, 2);
        assertEquals(1, cache.get(1));
        cache.put(3, 3);
        assertEquals(-1, cache.get(2));
        cache.put(4, 4);
        assertEquals(-1, cache.get(1));
        assertEquals(3, cache.get(3));
        assertEquals(4, cache.get(4));
        cache.put(3, -3);
        cache.put(-1, 2);
        assertEquals(2, cache.get(-1));
    }
}