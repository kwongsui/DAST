package pro.kwongsui.dast.dp;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class OrderPatchworkTest {

  @Test
  void dp() {
    assertIterableEquals(Arrays.asList(41, 32, 64, 68),
        new OrderPatchwork().dp(new int[]{68, 64, 32, 41, 88}, 200));
  }
}