package pro.kwongsui.dast.sort;

public class SortQuick {
  public static void quick(int[] arr, int n) {
    quick(arr, 0, n - 1);
  }

  private static void quick(int[] arr, int left, int right) {
    if (left >= right) return;

    int pivot = partition(arr, left, right);
    quick(arr, left, pivot - 1);
    quick(arr, pivot + 1, right);
  }

  private static int partition(int[] arr, int left, int right) {
    int pivot = arr[right];
    // i把arr分成两部分，arr[left…i-1]元素小于pivot，是"已处理区间"，A[i…right-1]是"未处理区间"
    int i = left;
    // 从未处理的区间arr[i…right-1]中取一个元素arr[j]，如果小于pivot，则加入到已处理区间尾部，即arr[i]的位置
    for (int j = i; j < right; j++)
      if (arr[j] < pivot && i++ != j) { // i++说明已处理区间又多一个数据，i == j说明数据在已处理区间尾部
        int temp = arr[i - 1]; // i != j 则将arr[j]加入到已处理区间尾部arr[i-1]
        arr[i - 1] = arr[j];
        arr[j] = temp;
      }

    int temp = arr[i];
    arr[i] = arr[right];
    arr[right] = temp;

    return i;
  }
}
