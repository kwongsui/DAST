package pro.kwongsui.dast.sort;

public class BinarySearch {
  public int search(int[] arr, int n, int val) {
    int low = 0, high = n - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (arr[mid] == val) return mid;
      else if (arr[mid] < val) low = mid + 1;
      else high = mid - 1;
    }
    return -1;
  }

  public int searchRecursively(int[] arr, int n, int val) {
    return searchInternally(arr, 0, n - 1, val);
  }

  public int searchInternally(int[] arr, int low, int high, int val) {
    if (low > high) return -1;

    int mid = low + (high - low) / 2;
    if (arr[mid] == val) return mid;
    else if (arr[mid] < val) return searchInternally(arr, mid + 1, high, val);
    else return searchInternally(arr, low, mid - 1, val);
  }

  // 查找第一个值等于给定值的元素
  public int search1(int[] arr, int n, int val) {
    int low = 0, high = n - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (arr[mid] < val) low = mid + 1;
      else if (arr[mid] > val) high = mid - 1;
      else {
        if (mid == 0 || arr[mid - 1] != val) return mid;
        else high = mid - 1;
      }
    }
    return -1;
  }

  // 查找最后一个值等于给定值的元素
  public int search2(int[] arr, int n, int val) {
    int low = 0, high = n - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (arr[mid] < val) low = mid + 1;
      else if (arr[mid] > val) high = mid - 1;
      else {
        if (mid == n - 1 || arr[mid + 1] != val) return mid;
        else low = mid + 1;
      }
    }
    return -1;
  }

  // 查找第一个大于等于给定值的元素
  public static int search3(int[] arr, int n, int val) {
    int low = 0, high = n - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (arr[mid] < val) low = mid + 1;
      else {
        if (mid == 0 || arr[mid - 1] < val) return mid;
        else high = mid - 1;
      }
    }
    return -1;
  }

  // 查找最后一个小于等于给定值的元素
  public int search4(int[] arr, int n, int val) {
    int low = 0, high = n - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (arr[mid] > val) high = mid - 1;
      else {
        if (mid == n - 1 || arr[mid + 1] > val) return mid;
        else low = mid + 1;
      }
    }
    return -1;
  }
}
