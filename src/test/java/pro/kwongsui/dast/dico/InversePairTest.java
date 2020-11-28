package pro.kwongsui.dast.dico;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class InversePairTest {

  @Test
  void inversePair() {
    assertEquals(6, new InversePair().inversePair(new int[]{1, 5, 6, 2, 3, 4}));
    assertEquals(12, new InversePair().inversePair(new int[]{0, 5, 6, 7, 1, 2, 3, 4}));
  }
}