package pro.kwongsui.dast.dp;

public class MinimumPath {

  private int[][] matrix;
  private int[][] mem;

  public MinimumPath() {}

  public MinimumPath(int[][] m) {
    matrix = m;
    mem = new int[m.length][m[0].length];
  }

  public int dp(int[][] matrix) {
    int[][] states = new int[matrix.length][matrix[0].length];

    int distance = 0;
    for (int col = 0; col < matrix.length; col++) {
      distance += matrix[0][col];
      states[0][col] = distance;
    }
    distance = 0;
    for (int row = 0; row < matrix[0].length; row++) {
      distance += matrix[row][0];
      states[row][0] = distance;
    }

    for (int row = 1; row < matrix.length; row++) {
      for (int col = 1; col < matrix[0].length; col++) {
        states[row][col] = matrix[row][col] + Math.min(states[row - 1][col], states[row][col - 1]);
      }
    }

    return states[matrix.length - 1][matrix[0].length - 1];
  }

  public int dp2(int row, int col) {
    if (row == 0 && col == 0) {
      return matrix[row][col];
    }

    if (mem[row][col] > 0) {
      return mem[row][col];
    }

    int minLeft = Integer.MAX_VALUE;
    if (col - 1 >= 0) {
      minLeft = dp2(row, col - 1);
    }

    int minUp = Integer.MAX_VALUE;
    if (row - 1 >= 0) {
      minUp = dp2(row - 1, col);
    }

    int minDistance = matrix[row][col] + Math.min(minLeft, minUp);
    mem[row][col] = minDistance;

    return minDistance;
  }
}
