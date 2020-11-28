package pro.kwongsui.dast.dico;

public class InversePair {

  private int count;

  public int inversePair(int[] arr) {
    merge(arr, 0, arr.length - 1);
    return count;
  }

  private void merge(int[] arr, int low, int high) {
    if (low == high) {
      return;
    }
    int mid = low + (high - low) / 2;
    merge(arr, low, mid);
    merge(arr, mid + 1, high);
    merge(arr, low, mid, high);
  }

  private void merge(int[] arr, int low, int mid, int high) {
    int i = low, j = mid + 1, k = 0;
    int[] temp = new int[high - low + 1];
    while (i <= mid && j <= high) {
      if (arr[i] <= arr[j]) {
        temp[k++] = arr[i++];
      } else {
        count += mid - i + 1;
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
