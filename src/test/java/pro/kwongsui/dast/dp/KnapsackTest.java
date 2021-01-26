package pro.kwongsui.dast.dp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import pro.kwongsui.dast.util.IntArrayConverter;

class KnapsackTest {

  @ParameterizedTest
  @CsvSource({
      "'2,2,4,6,3', 9, 9"
  })
  void dp(@ConvertWith(IntArrayConverter.class) int[] input, int w, int expected) {
    assertEquals(expected, new Knapsack().dp(input, w));
  }

  @ParameterizedTest
  @CsvSource({
      "'2,2,1,3,5', 9, 9"
  })
  void dp2(@ConvertWith(IntArrayConverter.class) int[] input, int w, int expected) {
    assertEquals(expected, new Knapsack().dp2(input, w));
  }

  @ParameterizedTest
  @CsvSource({
      "'2,2,4,6,3', '3,4,8,3,2', 9, 15"
  })
  void dp3(@ConvertWith(IntArrayConverter.class) int[] weight,
      @ConvertWith(IntArrayConverter.class) int[] values,
      int w, int expected) {
    assertEquals(expected, new Knapsack().dp3(weight, values, w));
  }

  @ParameterizedTest
  @CsvSource({
      "'2,2,1,3,5', '3,4,8,3,2', 9, 18"
  })
  void dp4(@ConvertWith(IntArrayConverter.class) int[] weight,
      @ConvertWith(IntArrayConverter.class) int[] values,
      int w, int expected) {
    assertEquals(expected, new Knapsack().dp4(weight, values, w));
  }
}