package pro.kwongsui.dast.dc;

public class InversePair {

  public int inversePair(int[] arr) {
    int[] count = new int[]{0};
    merge(arr, 0, arr.length - 1, count);
    return count[0];
  }

  private void merge(int[] arr, int low, int high, int[] count) {
    if (low == high) {
      return;
    }
    int mid = low + (high - low) / 2;
    merge(arr, low, mid, count);
    merge(arr, mid + 1, high, count);
    merge(arr, low, mid, high, count);
  }

  private void merge(int[] arr, int low, int mid, int high, int[] count) {
    int i = low, j = mid + 1, k = 0;
    int[] temp = new int[high - low + 1];
    while (i <= mid && j <= high) {
      if (arr[i] <= arr[j]) {
        temp[k++] = arr[i++];
      } else {
        count[0] += mid - i + 1;
        temp[k++] = arr[j++];
      }
    }
    while (i <= mid) {
      temp[k++] = arr[i++];
    }
    while (j <= high) {
      temp[k++] = arr[j++];
    }

    System.arraycopy(temp, 0, arr, low, high - low + 1);
  }
}
