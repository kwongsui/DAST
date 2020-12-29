package pro.kwongsui.dast.sort;

public class SortMerge {
  public void merge(int[] arr, int n) {
    merge(arr, 0, n - 1);
  }

  private void merge(int[] arr, int left, int right) {
    if (left == right) return;
    int mid = left + (right - left) / 2;
    merge(arr, left, mid);
    merge(arr, mid + 1, right);
    merge(arr, left, mid, right);
  }

  private void merge(int[] arr, int left, int mid, int right) {
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

  public void mergeNonRecursion(int[] arr) {
    for (int step = 1; step <= arr.length; step *= 2) {
      for (int i = 0; i + step < arr.length; i += step * 2) {
        merge(arr, i, i + step - 1, Math.min(i + step * 2 - 1, arr.length - 1));
      }
    }
  }
}
