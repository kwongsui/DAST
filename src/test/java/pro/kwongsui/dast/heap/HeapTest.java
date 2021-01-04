package pro.kwongsui.dast.heap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import pro.kwongsui.dast.util.IntArrayConverter;

class HeapTest {

  @ParameterizedTest
  @CsvSource({
      "'7,5,19,8,4,1,20,13,16', '20,16,19,13,4,1,7,5,8'"
  })
  void build(@ConvertWith(IntArrayConverter.class) int[] input,
      @ConvertWith(IntArrayConverter.class) int[] expected) {
    new Heap().heapify(input);
    Assertions.assertArrayEquals(expected, input);
  }

  @ParameterizedTest
  @CsvSource({
      "'33,27,21,16,13,15,19,5,6,7,8,1,2,12', '27,16,21,12,13,15,19,5,6,7,8,1,2,12'"
  })
  void delete(@ConvertWith(IntArrayConverter.class) int[] input,
      @ConvertWith(IntArrayConverter.class) int[] expected) {
    new Heap().delete(input);
    Assertions.assertArrayEquals(expected, input);
  }

  @ParameterizedTest
  @CsvSource({
      "'9,6,3,1,5', '1,3,5,6,9'"
  })
  void sort(@ConvertWith(IntArrayConverter.class) int[] input,
      @ConvertWith(IntArrayConverter.class) int[] expected) {
    new Heap().sort(input);
    Assertions.assertArrayEquals(expected, input);
  }
}