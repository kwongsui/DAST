package pro.kwongsui.dast.sort;

import java.util.Arrays;

public class Sorts {

  public static void main(String[] args) {
    int[] list = {6, 11, 3, 9, 8};
    //    bubble(list, list.length);
    //    insert(list, list.length);
    //    select(list, list.length);
    //    merge(list, list.length);
    //    quick(list, list.length);
    System.out.println(Arrays.toString(list));
  }

  private static void bubble(int[] arr, int n) {
    boolean needNextPass = true;
    for (int i = 1; i < n && needNextPass; i++) {
      needNextPass = false;
      for (int j = 0; j < n - i; j++) {
        if (arr[j] > arr[j + 1]) {
          int temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;

          needNextPass = true;
        }
      }
    }
  }

  private static void insert(int[] arr, int n) {
    for (int i = 1; i < n; i++) {
      int val = arr[i];
      int j;
      for (j = i - 1; j >= 0 && arr[j] > val; j--) {
        arr[j + 1] = arr[j];
      }

      arr[j + 1] = val;
    }
  }

  private static void select(int[] arr, int n) {
    int min, minIdx;
    for (int i = 0; i < n; i++) {
      min = arr[i];
      minIdx = i;
      for (int j = i + 1; j < n; j++) {
        if (arr[j] < min) {
          min = arr[j];
          minIdx = j;
        }
      }

      if (i != minIdx) {
        arr[minIdx] = arr[i];
        arr[i] = min;
      }
    }
  }
}
