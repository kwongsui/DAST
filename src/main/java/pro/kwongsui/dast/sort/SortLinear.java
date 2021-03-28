package pro.kwongsui.dast.sort;

import java.util.ArrayList;
import java.util.List;

public class SortLinear {

  @SuppressWarnings("unchecked")
  public void radix(int[] arr, int n) { // n是数组中元素位数
    List<Integer>[] buckets = new ArrayList[10];
    for (int i = 0; i < buckets.length; i++) buckets[i] = new ArrayList<>();
    // Arrays.fill(buckets, new ArrayList<Integer>()); // 填充的是同一个ArrayList

    for (int digit = 0; digit < n; digit++) {
      for (List<Integer> bucket : buckets) bucket.clear();

      for (int val : arr) {
        int key = getKey(val, digit);
        buckets[key].add(val);
      }

      int k = 0;
      for (List<Integer> bucket : buckets) for (Object val : bucket) arr[k++] = (int) val;
    }
  }

  private int getKey(int val, int digit) {
    int result = 1;
    for (int i = 0; i < digit; i++) result *= 10;
    return (val / result) % 10;
  }

  public void count(int[] arr, int n) {
    // 原始数组：元素范围
    int max = arr[0];
    for (int i = 1; i < n; i++) if (arr[i] > max) max = arr[i];

    // 计数数组：元素为原数组元素出现次数，索引即原数组元素
    int[] count = new int[max + 1];
    for (int i = 0; i < n; i++) count[arr[i]]++;
    // 计数数组：累加，元素变成原数组中小于等于计数数组当前索引（即原数组元素）的元素个数
    for (int i = 1; i <= max; i++) count[i] = count[i] + count[i - 1];

    // 结果数组：原数组从尾部扫描，从计数数组中得到与原数组元素相等的索引位置元素-1，即原数组元素排序后的位置
    int[] ret = new int[n]; // 注：尾部扫描，保证稳定排序，同时也是出栈思维，即后进先出
    for (int i = n - 1; i >= 0; i--) ret[count[arr[i]]-- - 1] = arr[i];

    System.arraycopy(ret, 0, arr, 0, n);
  }
}
