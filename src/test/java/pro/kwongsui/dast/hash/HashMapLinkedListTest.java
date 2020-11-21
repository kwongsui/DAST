package pro.kwongsui.dast.hash;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HashMapLinkedListTest {

  static final int N = 3100;
  HashMapLinkedList<Character, Integer> map;

  @BeforeEach
  void setUp() {
    map = new HashMapLinkedList<>();

    for (int i = 0; i < N; i++) {
      map.put((char) ('A' + i), i + 1);
    }
  }

  @Test
  @Order(1)
  void put() {
    for (int i = 0; i < N; i++) {
      Assertions.assertEquals(i + 1, map.get((char) ('A' + i)));
    }

    for (int i = 0; i < N; i++) {
      map.put((char) ('A' + i), i + 2);
    }

    for (int i = 0; i < N; i++) {
      Assertions.assertEquals(i + 2, map.get((char) ('A' + i)));
    }
  }

  @Test
  @Order(3)
  void remove() {
    for (int i = 0; i < N; i++) {
      char key = (char) ('A' + i);
      Assertions.assertEquals(i + 1, map.get(key));

      map.remove(key);

      Assertions.assertNull(map.get(key));
    }
  }

  @Test
  @Order(2)
  void get() {
    for (int i = 0; i < N; i++) {
      Assertions.assertEquals(i + 1, map.get((char) ('A' + i)));
    }
  }
}