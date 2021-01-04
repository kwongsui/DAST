package pro.kwongsui.dast.heap;

public class Heap {

  public void heapify(int[] data) {
    int n = data.length - 1;
    int index = (n - 1) / 2;
    while (index >= 0) {
      siftDown(data, n, index--);
    }
  }

  public void delete(int[] data) {
    int n = data.length - 1;
    heapify(data);
    data[0] = data[n--];
    siftDown(data, n, 0);
  }

  public void sort(int[] data) {
    int n = data.length - 1;
    heapify(data);
    while (n > 0) {
      swap(data, n, 0);
      --n;
      siftDown(data, n, 0);
    }
  }

  private void siftDown(int[] data, int n, int i) {
    while (i <= n) {
      int big = i;
      if (2 * i + 1 <= n && data[2 * i + 1] > data[i]) {
        big = 2 * i + 1;
      }
      if (2 * i + 2 <= n && data[2 * i + 2] > data[big]) {
        big = 2 * i + 2;
      }
      if (data[big] > data[i]) {
        swap(data, big, i);
        i = big;
      } else {
        break;
      }
    }
  }

  private void swap(int[] data, int i, int j) {
    int tmp = data[i];
    data[i] = data[j];
    data[j] = tmp;
  }
}
