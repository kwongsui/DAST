package pro.kwongsui.dast.backtracking;

public class AllPermutation {

  public void allPermutation(int[] data) {
    permutationHead(data, 0);
    permutationTail(data, data.length);
  }

  private void permutationHead(int[] data, int k) {
    if (k == data.length) {
      for (int num : data) {
        System.out.print(num + " ");
      }
      System.out.println();
    }
    /*
     * 从头到尾依次固定索引k位置的元素寻找排列，k是子数组头部
     * 1. 第一个交换是为了固定索引k位置的元素
     * 2. 第二个交换是把元素交换回来以进行下一个索引k位置元素的固定
     */
    for (int i = k; i < data.length; i++) {
      swap(data, i, k);
      permutationHead(data, k + 1);
      swap(data, i, k);
    }
  }

  private void permutationTail(int[] data, int k) {
    if (k == 1) {
      for (int num : data) {
        System.out.print(num + " ");
      }
      System.out.println();
    }
    /*
     * 从尾到头依次固定索引k-1位置的元素寻找排列，k-1是子数组尾部
     * 1. 第一个交换是为了固定索引k-1位置的元素
     * 2. 第二个交换是把元素交换回来以进行下一个索引k-1位置元素的固定
     */
    for (int i = 0; i < k; i++) {
      swap(data, i, k - 1);
      permutationTail(data, k - 1);
      swap(data, i, k - 1);
    }
  }

  private void swap(int[] data, int i, int j) {
    int temp = data[i];
    data[i] = data[j];
    data[j] = temp;
  }
}
