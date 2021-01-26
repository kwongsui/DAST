package pro.kwongsui.dast.dp;

public class LevenshteinDistance {

  public int dp(char[] a, char[] b) {
    int[][] states = new int[a.length + 1][b.length + 1];

    // 哨兵优化
    for (int j = 1; j <= b.length; j++) {
      states[0][j] = states[0][j - 1]  + 1;  // 表格第一行
    }
    for (int i = 1; i <= a.length; i++) {
      states[i][0] = states[i - 1][0] + 1; // 表格第一列
    }
    for (int i = 1; i <= a.length; i++) {
      for (int j = 1; j <= b.length; j++) {
        if (a[i - 1] == b[j - 1]) {
          states[i][j] = states[i - 1][j - 1];
        } else {
          states[i][j] =
              min(states[i - 1][j] + 1, states[i][j - 1] + 1, states[i - 1][j - 1] + 1);
        }
      }
    }

    return states[a.length][b.length];
  }

  private int min(int x, int y, int z) {
    return Math.min(x, Math.min(y, z));
  }
}
