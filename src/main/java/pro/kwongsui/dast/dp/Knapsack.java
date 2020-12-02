package pro.kwongsui.dast.dp;

import java.util.Arrays;

public class Knapsack {

  public int dp(int[] weight, int w) {
    boolean[][] states = new boolean[weight.length][w + 1];

    states[0][0] = true;
    if (weight[0] <= w) {
      states[0][weight[0]] = true;
    }

    for (int i = 1; i < weight.length; i++) {
      for (int j = 0; j <= w; j++) {
        if (states[i - 1][j]) {
          states[i][j] = states[i - 1][j];
        }
      }

      for (int j = 0; j <= w - weight[i]; j++) {
        if (states[i - 1][j]) {
          states[i][j + weight[i]] = true;
        }
      }
    }

    for (int i = w; i >= 0; i--) {
      if (states[weight.length - 1][i]) {
        return i;
      }
    }

    return 0;
  }

  public int dp2(int[] weight, int w) {
    boolean[] states = new boolean[w + 1];

    states[0] = true;
    if (weight[0] <= w) {
      states[weight[0]] = true;
    }

    for (int i = 1; i < weight.length; i++) {
      for (int j = w - weight[i]; j >= 0; j--) {
        if (states[j]) {
          states[j + weight[i]] = true;
        }
      }
      // 存在重复计算问题
//      for(int j = 0; j <= w - weight[i]; j++) {
//        if(states[j])
//          states[j + weight[i]] = true;
//      }
    }

    for (int i = w; i >= 0; i--) {
      if (states[i]) {
        return i;
      }
    }

    return 0;
  }

  public int dp3(int[] weight, int[] values, int w) {
    int[][] states = new int[weight.length][w + 1];
    for (int[] state : states) {
      Arrays.fill(state, -1);
    }

    states[0][0] = 0;
    if (weight[0] <= w) {
      states[0][weight[0]] = values[0];
    }

    for (int i = 1; i < weight.length; i++) {
      for (int j = 0; j <= w; j++) {
        if (states[i - 1][j] >= 0) {
          states[i][j] = states[i - 1][j];
        }
      }

      for (int j = 0; j <= w - weight[i]; j++) {
        if (states[i - 1][j] >= 0) {
          int val = states[i - 1][j] + values[i];
          // 相同重量的物品，已装入的可能比未装入的价值要大，
          // 如已装入索引1和2的物品，重量6，价值12，考虑是否装入索引3的物品时，重量6，价值3，则不装入
          if (val > states[i][j + weight[i]]) {
            states[i][j + weight[i]] = val;
          }
        }
      }
    }

    int maxVal = -1;
    for (int i = 0; i <= w; i++) {
      if (states[weight.length - 1][i] > maxVal) {
        maxVal = states[weight.length - 1][i];
      }
    }

    return maxVal;
  }

  public int dp4(int[] weight, int[] values, int w) {
    int[] states = new int[w + 1];
    Arrays.fill(states, -1);

    states[0] = 0;
    if (weight[0] <= w) {
      states[weight[0]] = values[0];
    }

    for (int i = 0; i < weight.length; i++) {
      for (int j = w - weight[i]; j >= 0; j--) {
        if (states[j] >= 0) {
          int val = states[j] + values[i];
          if (val > states[j + weight[i]]) {
            states[j + weight[i]] = val;
          }
        }
      }
    }

    int maxVal = -1;
    for (int i = 0; i <= w; i++) {
      if (states[i] > maxVal) {
        maxVal = states[i];
      }
    }

    return maxVal;
  }
}
