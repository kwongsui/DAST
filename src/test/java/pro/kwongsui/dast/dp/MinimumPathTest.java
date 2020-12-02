package pro.kwongsui.dast.dp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MinimumPathTest {

  @Test
  void dp() {
    assertEquals(19,
        new MinimumPath().dp(new int[][]{
            {1, 3, 5, 9},
            {2, 1, 3, 4},
            {5, 2, 6, 7},
            {6, 8, 4, 3}
        }));

    assertEquals(19,
        new MinimumPath(
            new int[][]{
                {1, 3, 5, 9},
                {2, 1, 3, 4},
                {5, 2, 6, 7},
                {6, 8, 4, 3}
            }).dp2(3, 3));
  }
}