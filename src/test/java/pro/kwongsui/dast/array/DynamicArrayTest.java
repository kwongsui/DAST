package pro.kwongsui.dast.array;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import pro.kwongsui.dast.util.IntArrayConverter;

class DynamicArrayTest {

  DynamicArray arr;

  @BeforeEach
  void setUp() {
    arr = new DynamicArray(5);
  }

  @ParameterizedTest
  @CsvSource({
      "'0,1,2,3', 4, '0,1,2,3,4'",
      "'0,1,2,3,4', 6, '0,1,2,3,4,6,0,0,0,0'"
  })
  void add(
      @ConvertWith(IntArrayConverter.class) int[] input1,
      int input2,
      @ConvertWith(IntArrayConverter.class) int[] expected) {
    for (int v : input1) {
      arr.add(v, v);
    }
    arr.add(input2, input2);
    assertArrayEquals(expected, arr.getData());
  }

  @ParameterizedTest
  @CsvSource({
      "'0,1,2,3,4', 4, '0,1,2,3,4'",
      "'0,1,2,3,4,5', 5, '0,1,2,3,4,5,0,0,0,0'"
  })
  void remove(
      @ConvertWith(IntArrayConverter.class) int[] input1,
      int input2,
      @ConvertWith(IntArrayConverter.class) int[] expected) {
    for (int v : input1) {
      arr.add(v, v);
    }
    arr.remove(input2);
    assertArrayEquals(expected, arr.getData());
  }

  @ParameterizedTest
  @CsvSource({
      "'0,1,2,3,4', 4, 5, '0,1,2,3,5'",
      "'0,1,2,3,4,5', 4, 5, '0,1,2,3,5,5,0,0,0,0'"
  })
  void set(
      @ConvertWith(IntArrayConverter.class) int[] input1,
      int input2,
      int input3,
      @ConvertWith(IntArrayConverter.class) int[] expected) {
    for (int v : input1) {
      arr.add(v, v);
    }
    arr.set(input2, input3);
    assertArrayEquals(expected, arr.getData());
  }

  @ParameterizedTest
  @CsvSource({
      "'0,1,2,3,4', 4, 4",
      "'0,1,2,3,4,5', 5, 5"
  })
  void get(
      @ConvertWith(IntArrayConverter.class) int[] input1,
      int input2,
      int expected) {
    for (int v : input1) {
      arr.add(v, v);
    }
    assertEquals(expected, arr.get(input2));
  }
}