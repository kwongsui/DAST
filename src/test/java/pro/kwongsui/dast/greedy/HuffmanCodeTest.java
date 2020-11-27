package pro.kwongsui.dast.greedy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

class HuffmanCodeTest {

  @Test
  void encode() {
    char[] chars = new char[1000];
    for (int i = 0; i < chars.length; i++) {
      chars[i] = (char) new Random().nextInt(256);
    }
    String raw = new String(chars);
    HuffmanCode huffmanCode = new HuffmanCode();
    assertEquals(raw, huffmanCode.decode(huffmanCode.encode(raw)));
  }

  @Test
  void decode() {
  }
}