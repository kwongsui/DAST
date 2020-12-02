package pro.kwongsui.dast.backtracking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import pro.kwongsui.dast.util.IntArrayConverter;

class KnapsackTest {

  @ParameterizedTest
  @CsvSource({
      "'7,5,2,3,5,8', 10, 10",
      "'7,5,2', 6, 5",
      "'10,6,22,25,30,43,55', 60, 59"
  })
  void backtrack(@ConvertWith(IntArrayConverter.class) int[] items, int w, int maxW) {
    Knapsack knapsack = new Knapsack();
    knapsack.backtrack(items, 0, 0, w);
    assertEquals(maxW, knapsack.getMaxW());
  }
}