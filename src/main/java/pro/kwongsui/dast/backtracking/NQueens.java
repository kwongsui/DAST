package pro.kwongsui.dast.backtracking;

import lombok.Getter;

public class NQueens {

  private static int N = 8;

  private final int[] queens;

  @Getter
  private int cnt = 0;

  public NQueens(int n) {
    N = n;
    queens = new int[N];
  }

  public void backtrack(int row) {
    if (row == N) {
      cnt++;
//      print();
      return;
    }
    for (int col = 0; col < N; col++) {
      if (isValid(row, col)) {
        queens[row] = col;
        backtrack(row + 1);
      }
    }
  }

  private boolean isValid(int row, int col) {
    int left = col - 1, right = col + 1;
    for (int i = row - 1; i >= 0; i--) {
      if (queens[i] == col) {
        return false;
      }
      if (left >= 0 && queens[i] == left) {
        return false;
      }
      if (right < N && queens[i] == right) {
        return false;
      }
      left--;
      right++;
    }
    return true;
  }

  public void print() {
    for (int row = 0; row < N; row++) {
      for (int col = 0; col < N; col++) {
        if (queens[row] == col) {
          System.out.print("Q ");
        } else {
          System.out.print("* ");
        }
      }
      System.out.println();
    }
    System.out.println();
  }
}
