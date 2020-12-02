package pro.kwongsui.dast.dp;

import java.util.ArrayList;
import java.util.List;

public class OrderPatchwork {

  /**
   * 选择商品以使得总价格是超过满减价格的最小值
   * @param items 商品，元素是商品价格，索引是第几个商品
   * @param price 满减价格，即满price减多少，上限是price的两倍，超过则不划算
   */
  public List<Integer> dp(int[] items, int price) {
    boolean[][] states = new boolean[items.length][price * 2 + 1];

    states[0][0] = true;
    if (items[0] <= price * 2) {
      states[0][items[0]] = true;
    }

    for (int i = 1; i < items.length; i++) {
      for (int j = 0; j <= price * 2; j++) {
        if (states[i - 1][j]) {
          states[i][j] = true;
        }
      }

      for (int j = 0; j <= price * 2 - items[i]; j++) {
        if (states[i - 1][j]) {
          states[i][j + items[i]] = true;
        }
      }
    }

    List<Integer> list = new ArrayList<>();
    int j;
    // 将j定位至大于等于price的地方，即只查找总价格大于等于price的商品组合
    for (j = price; j < price * 2 + 1; j++) {
      if (states[items.length - 1][j]) {
        break;
      }
    }
    // 总价格超过price的两倍，不划算
    if (j == price * 2 + 1) {
      return list;
    }
    for (int i = items.length - 1; i >= 1; i--) {
      // 前次状态states[i - 1][j - items[i]]可达，即当前状态states[i][j]可达，当前商品items[i]被选购
      if (j - items[i] >= 0 && states[i - 1][j - items[i]]) {
        list.add(items[i]);
        j -= items[i];
      }
    }
    if (j != 0) {
      list.add(items[0]);
    }
    return list;
  }
}
