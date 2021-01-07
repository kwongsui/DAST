package pro.kwongsui.dast.dc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import pro.kwongsui.dast.util.IntArrayConverter;

class InversePairTest {

  @ParameterizedTest
  @CsvSource({
      "'1,5,6,2,3,4',6",
      "'0,5,6,7,1,2,3,4',12"
  })
  void inversePair(@ConvertWith(IntArrayConverter.class) int[] input, int expected) {
    assertEquals(expected, new InversePair().inversePair(input));
  }
}