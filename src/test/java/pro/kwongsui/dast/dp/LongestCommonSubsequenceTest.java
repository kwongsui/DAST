package pro.kwongsui.dast.dp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LongestCommonSubsequenceTest {

  @ParameterizedTest
  @CsvSource({
      "'mitcmu', 'mtacnu', 4",
      "'nitcmu', 'mtacnu', 3",
      "'mitt', 'mt', 2"
  })
  void dp(String input1, String input2, int expected) {
    LongestCommonSubsequence lcs = new LongestCommonSubsequence();
    int[][] states = lcs.dp(input1.toCharArray(), input2.toCharArray());
    assertEquals(expected, states[input1.length() - 1][input2.length() - 1]);
    lcs.print(states);
  }
}