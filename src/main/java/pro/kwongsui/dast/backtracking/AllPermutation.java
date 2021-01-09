package pro.kwongsui.dast.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllPermutation {

  public List<List<Integer>> permute(int[] data) {
    List<List<Integer>> list = new ArrayList<>();
    if (data == null || data.length == 0) {
      return list;
    }
    backtrack(data, 0, list);
//    backtrack2(data, data.length, list);
    return list;
  }

  private void backtrack(int[] data, int k, List<List<Integer>> list) {
    if (k == data.length) {
      List<Integer> l = new ArrayList<>();
      for (int num : data) {
        l.add(num);
      }
      list.add(l);
    }
    /*
     * 从头到尾依次固定索引k位置的元素寻找排列，k是子数组头部
     * 1. 第一个交换是为了固定索引k位置的元素
     * 2. 第二个交换是把元素交换回来以进行下一个索引k位置元素的固定
     * 3. 哈希集去重，例如[1,2,3,1]=[1]+[2,3,1],[2]+[1,3,1],[3]+[1,2,1]，[1]+[1,2,3]重复
     */
    Set<Integer> visited = new HashSet<>();
    for (int i = k; i < data.length; i++) {
      if (visited.add(data[i])) {
        swap(data, i, k);
        backtrack(data, k + 1, list);
        swap(data, i, k);
      }
    }
  }

  private void backtrack2(int[] data, int k, List<List<Integer>> list) {
    if (k == 1) {
      List<Integer> l = new ArrayList<>();
      for (int num : data) {
        l.add(num);
      }
      list.add(l);
    }
    /*
     * 从尾到头依次固定索引k-1位置的元素寻找排列，k-1是子数组尾部
     * 1. 第一个交换是为了固定索引k-1位置的元素
     * 2. 第二个交换是把元素交换回来以进行下一个索引k-1位置元素的固定
     */
    for (int i = 0; i < k; i++) {
      swap(data, i, k - 1);
      backtrack2(data, k - 1, list);
      swap(data, i, k - 1);
    }
  }

  private void swap(int[] data, int i, int j) {
    int temp = data[i];
    data[i] = data[j];
    data[j] = temp;
  }
}
