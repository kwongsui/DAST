package pro.kwongsui.dast.sort;

public class SortMerge {
  public static void merge(int[] arr, int n) {
    merge(arr, 0, n - 1);
  }

  private static void merge(int[] arr, int left, int right) {
    if (left == right) return;
    int mid = left + (right - left) / 2;
    merge(arr, left, mid);
    merge(arr, mid + 1, right);
    merge(arr, left, mid, right);
  }

  private static void merge(int[] arr, int left, int mid, int right) {
    int i = left, j = mid + 1, k = 0;
    int[] temp = new int[right - left + 1];
    while (i <= mid && j <= right) {
      if (arr[i] <= arr[j]) temp[k++] = arr[i++];
      else temp[k++] = arr[j++];
    }

    while (i <= mid) temp[k++] = arr[i++];
    while (j <= right) temp[k++] = arr[j++];

    System.arraycopy(temp, 0, arr, left, right - left + 1);
  }
}
