package pro.kwongsui.dast.dp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class KnapsackTest {

  @Test
  void dp() {
    assertEquals(9, new Knapsack().dp(new int[]{2, 2, 4, 6, 3}, 9));
  }

  @Test
  void dp2() {
    assertEquals(9, new Knapsack().dp2(new int[]{2, 2, 1, 3, 5}, 9));
  }

  @Test
  void dp3() {
    assertEquals(15,
        new Knapsack().dp3(new int[]{2, 2, 4, 6, 3}, new int[]{3, 4, 8, 3, 2}, 9));
  }

  @Test
  void dp4() {
    assertEquals(18,
        new Knapsack().dp3(new int[]{2, 2, 1, 3, 5}, new int[]{3, 4, 8, 3, 2}, 9));
  }
}