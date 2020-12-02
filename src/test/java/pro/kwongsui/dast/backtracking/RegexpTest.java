package pro.kwongsui.dast.backtracking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RegexpTest {

  @Test
  void match() {
    assertTrue(new Regexp("*a*b?".toCharArray()).match("angbc".toCharArray()));
    assertTrue(new Regexp("a*b?".toCharArray()).match("angbc".toCharArray()));
    assertTrue(new Regexp("a*b?".toCharArray()).match("angleb".toCharArray()));
    assertFalse(new Regexp("a*b?".toCharArray()).match("b".toCharArray()));
    assertFalse(new Regexp("a*b?".toCharArray()).match("cab".toCharArray()));
    assertFalse(new Regexp("a*b?".toCharArray()).match("abcdefg".toCharArray()));
  }
}