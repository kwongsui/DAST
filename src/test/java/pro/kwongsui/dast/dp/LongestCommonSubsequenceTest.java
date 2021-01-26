package pro.kwongsui.dast.dp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LongestCommonSubsequenceTest {

  @ParameterizedTest
  @CsvSource({
      "'mitcmu', 'mtacnu', 4",
      "'nitcmu', 'mtacnu', 3",
      "'mitt', 'mt', 2",
      "abcde, ace, 3",
      "bl, yby, 1",
      "bsbininm, jmjkbkjkv, 1",
      "aba, a, 1"
  })
  void dp(String input1, String input2, int expected) {
    assertEquals(expected,
        new LongestCommonSubsequence().dp(input1.toCharArray(), input2.toCharArray()));
  }
}