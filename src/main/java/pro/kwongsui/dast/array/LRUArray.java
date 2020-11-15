package pro.kwongsui.dast.array;

public class LRUArray<T> {

  private static final int DEFAULT_CAPACITY = 3;

  private T[] values;
  private int capacity;
  private int count;

  public LRUArray() {
    this(DEFAULT_CAPACITY);
  }

  public LRUArray(int capacity) {
    this.capacity = capacity;
    count = 0;
    values = (T[]) new Object[capacity];
  }

  public void cache(T object) {
    if (object == null) throw new IllegalArgumentException("Null not supported");

    if (isCached(object)) {
      removeCache(object);
      values[count - 1] = object;
    } else {
      if (count < capacity) {
        values[count++] = object;
      } else {
        System.arraycopy(values, 1, values, 0, count - 1);
        values[count - 1] = object;
      }
    }
  }

  private boolean isCached(T object) {
    for (T value : values) {
      if (object.equals(value)) {
        return true;
      }
    }
    return false;
  }

  private void removeCache(T object) {
    for (int i = 0; i < values.length; i++) {
      if (object.equals(values[i])) {
        System.arraycopy(values, i + 1, values, i, count - i - 1);
      }
    }
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder(count);
    for (T value : values) {
      builder.append(value).append(" ");
    }

    return builder.toString();
  }

  public static void main(String[] args) {
    //    array.LRUBasedArray<Integer> lru = new array.LRUBasedArray<>();
    //    lru.cache(1);
    //    lru.cache(2);
    //    lru.cache(3);
    //    System.out.println(lru);
    //    lru.cache(4);
    //    System.out.println(lru);
    //    lru.cache(2);
    //    System.out.println(lru);
    //    lru.cache(4);
    //    System.out.println(lru);

    LRUArray<Integer> lru = new LRUArray<>(8);
    lru.cache(1);
    lru.cache(2);
    lru.cache(3);
    lru.cache(4);
    lru.cache(5);
    lru.cache(6);
    lru.cache(7);
    lru.cache(8);
    System.out.println(lru);
    lru.cache(4);
    System.out.println(lru);
    lru.cache(1);
    System.out.println(lru);
    lru.cache(4);
    System.out.println(lru);
    lru.cache(9);
    System.out.println(lru);
  }
}
