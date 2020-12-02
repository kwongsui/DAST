package pro.kwongsui.dast.dp;

public class LongestCommonSubsequence {

  public int[][] dp(char[] a, char[] b) {
    int[][] states = new int[a.length][b.length];

    for (int j = 0; j < b.length; j++) {
      if (a[0] == b[j]) {
        states[0][j] = 1;
      } else if (j != 0) {
        states[0][j] = states[0][j - 1];
      } else {
        states[0][j] = 0;
      }
    }

    for (int i = 0; i < a.length; i++) {
      if (b[0] == a[i]) {
        states[i][0] = 1;
      } else if (i != 0) {
        states[i][0] = states[i - 1][0];
      } else {
        states[i][0] = 0;
      }
    }

    for (int i = 1; i < a.length; i++) {
      for (int j = 1; j < b.length; j++) {
        if (a[i] == b[j]) {
          states[i][j] = max(states[i - 1][j], states[i][j - 1], states[i - 1][j - 1] + 1);
        } else {
          states[i][j] = max(states[i - 1][j], states[i][j - 1], states[i - 1][j - 1]);
        }
      }
    }

    return states;
  }

  private int max(int x, int y, int z) {
    return Math.max(x, Math.max(y, z));
  }

  public void print(int[][] states) {
    for (int[] state : states) {
      for (int i : state) {
        System.out.print(i + " ");
      }
      System.out.println();
    }
  }
}
