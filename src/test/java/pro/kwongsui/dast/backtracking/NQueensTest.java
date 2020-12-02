package pro.kwongsui.dast.backtracking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class NQueensTest {

  @ParameterizedTest
  @CsvSource({
      "5, 10",
      "6, 4",
      "7, 40",
      "8, 92",
      "9, 352",
      "10, 724"
  })
  void queens(int input, int expected) {
    NQueens queens = new NQueens(input);
    queens.backtrack(0);
    assertEquals(expected, queens.getCnt());
  }
}