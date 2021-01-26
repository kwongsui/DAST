package pro.kwongsui.dast.dp;

public class LongestCommonSubsequence {

  public int dp(char[] a, char[] b) {
    int[][] states = new int[a.length + 1][b.length + 1];

    // 哨兵优化
    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < b.length; j++) {
        if (a[i] == b[j]) {
          states[i + 1][j + 1] = states[i][j] + 1;
        } else {
          states[i + 1][j + 1] = Math.max(states[i][j + 1], states[i + 1][j]);
        }
      }
    }

    return states[a.length][b.length];
  }
}
