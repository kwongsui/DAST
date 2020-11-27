package pro.kwongsui.dast.greedy;

import lombok.Getter;
import lombok.Setter;

public class CharNode {

  public char element;
  public CharNode left;
  public CharNode right;
  @Getter
  private final int freq;
  @Setter
  @Getter
  private String code = "";

  public CharNode(char c, int f) {
    element = c;
    freq = f;
  }

  public CharNode(int f, CharNode l, CharNode r) {
    freq = f;
    left = l;
    right = r;
  }

}
