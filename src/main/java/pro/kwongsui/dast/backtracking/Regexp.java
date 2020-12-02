package pro.kwongsui.dast.backtracking;

/**
 * 背景假设：
 * 1. 正则表达式中只包含“*”和“?”这两种通配符，
 * 2. “*”匹配任意多个（大于等于 0 个）任意字符
 * 3. “?”匹配零个或者一个任意字符
 */
public class Regexp {

  private boolean matched = false;
  private final char[] pattern;

  public Regexp(char[] p) {
    pattern = p;
  }

  public boolean match(char[] text) {
    matched = false;
    match(text, 0, 0);
    return matched;
  }

  private void match(char[] text, int i, int j) {
    if (matched) {
      return;
    }

    if (j == pattern.length) {
      if (i == text.length) {
        matched = true;
      }
      return;
    }

    if (pattern[j] == '*') {
      for (int k = 0; k <= text.length - i; k++) {
        match(text, i + k, j + 1);
      }
    } else if (pattern[j] == '?') {
      match(text, i, j + 1);
      match(text, i + 1, j + 1);
    } else if (i < text.length && pattern[j] == text[i]) {
      match(text, i + 1, j + 1);
    }
  }
}
