package pro.kwongsui.dast.dp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LevenshteinDistanceTest {

  @ParameterizedTest
  @CsvSource({
      "'mitcmu', 'mtacnu', 3",
      "'nitcmu', 'mtacnu', 4",
      "'mitt', 'mt', 2"
  })
  void dp(String input1, String input2, int expected) {
    LevenshteinDistance ld = new LevenshteinDistance();
    int[][] states = ld.dp(input1.toCharArray(), input2.toCharArray());
    assertEquals(expected, states[input1.length() - 1][input2.length() - 1]);
    ld.print(states);
  }
}