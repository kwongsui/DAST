package pro.kwongsui.dast.heap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HeapTest {

  @Test
  void build() {
    Integer[] input = new Integer[]{7, 5, 19, 8, 4, 1, 20, 13, 16};
    Integer[] expected = new Integer[]{20, 16, 19, 13, 4, 1, 7, 5, 8};
    new Heap<Integer>().heapify(input);
    Assertions.assertArrayEquals(expected, input);
  }

  @Test
  void delete() {
    Integer[] input = new Integer[]{33, 27, 21, 16, 13, 15, 19, 5, 6, 7, 8, 1, 2, 12};
    Integer[] expected = new Integer[]{27, 16, 21, 12, 13, 15, 19, 5, 6, 7, 8, 1, 2, 12};
    new Heap<Integer>().delete(input);
    Assertions.assertArrayEquals(expected, input);
  }

  @Test
  void sort() {
    Integer[] input = new Integer[]{9, 6, 3, 1, 5};
    Integer[] expected = new Integer[]{1, 3, 5, 6, 9};
    new Heap<Integer>().sort(input);
    Assertions.assertArrayEquals(expected, input);
  }
}