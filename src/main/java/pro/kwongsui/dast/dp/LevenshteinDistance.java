package pro.kwongsui.dast.dp;

public class LevenshteinDistance {

  public int[][] dp(char[] a, char[] b) {
    int[][] states = new int[a.length][b.length];

    for (int j = 0; j < b.length; j++) {
      if (a[0] == b[j]) {
        states[0][j] = j;
      } else if (j != 0) {
        states[0][j] = states[0][j - 1] + 1;
      } else {
        // 首个字符不相同，即a[i] != b[j] && i = j = 0
        states[0][j] = 1;
      }
    }

    for (int i = 0; i < a.length; i++) {
      if (b[0] == a[i]) {
        states[i][0] = i;
      } else if (i != 0) {
        states[i][0] = states[i - 1][0] + 1;
      } else {
        states[i][0] = 1;
      }
    }

    for (int i = 1; i < a.length; i++) {
      for (int j = 1; j < b.length; j++) {
        if (a[i] == b[j]) {
          states[i][j] =
              min(states[i - 1][j] + 1, states[i][j - 1] + 1, states[i - 1][j - 1]);
        } else {
          states[i][j] =
              min(states[i - 1][j] + 1, states[i][j - 1] + 1, states[i - 1][j - 1] + 1);
        }
      }
    }

    return states;
  }

  private int min(int x, int y, int z) {
    return Math.min(x, Math.min(y, z));
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
