package pro.kwongsui.dast.backtracking;

import lombok.Getter;

public class Knapsack {

  @Getter
  private int maxW;

  /**
   * 0-1背包问题：物品不可分割，要么装入，要么不装入，求可装入背包物品最大重量
   * @param items 各个物品重量
   * @param k 第k个物品
   * @param cw 已装入背包物品重量
   * @param w 背包可装入物品重量
   */
  public void backtrack(int[] items, int k, int cw, int w) {
    // 装入背包重量达到背包可装入重量或者所有物品都已考察是否装入
    if (cw == w || k == items.length) {
      if (maxW < cw) {
        maxW = cw;
      }
      return;
    }
    if (cw + items[k] <= w) {
      backtrack(items, k + 1, cw + items[k], w);
    }
    backtrack(items, k + 1, cw, w);
  }
}
