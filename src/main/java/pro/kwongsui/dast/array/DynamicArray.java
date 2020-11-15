package pro.kwongsui.dast.array;

public class DynamicArray<E> {
  private static final int DEFAULT_CAPACITY = 10;

  private E[] data;

  private int size = 0;

  public DynamicArray() {
    this(DEFAULT_CAPACITY);
  }

  public DynamicArray(int capacity) {
    data = (E[]) new Object[capacity];
  }

  public void add(E e) {
    add(size(), e);
  }

  public void add(int index, E e) {
    if (index < 0 || index > size)
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

    if (size >= data.length) resize();

    if (size - index >= 0) System.arraycopy(data, index, data, index + 1, size - index);

    data[index] = e;

    size++;
  }

  public void addAll(E[] objects) {
    for (E e : objects) add(e);
  }

  /** new Object[2 * size + 1] ensures that the new size is not 0. */
  private void resize() {
    E[] newData = (E[]) new Object[size * 2 + 1];

    System.arraycopy(data, 0, newData, 0, size);

    data = newData;
  }

  public E remove(int index) {
    checkIndex(index);

    E e = data[index];

    if (size - index - 1 >= 0) System.arraycopy(data, index + 1, data, index, size - index - 1);

    data[size - 1] = null;

    size--;

    return e;
  }

  private void checkIndex(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
  }

  public boolean remove(E e) {
    int index = indexOf(e);
    if (index != -1) {
      remove(index);
      return true;
    }

    return false;
  }

  public void clear() {
    data = (E[]) new Object[DEFAULT_CAPACITY];
    size = 0;
  }

  public E set(int index, E e) {
    checkIndex(index);

    E old = get(index);

    data[index] = e;

    return old;
  }

  public void trimToSize() {
    if (size != data.length) {
      E[] newData = (E[]) new Object[size];
      System.arraycopy(data, 0, newData, 0, size);
      data = newData;
    }
  }

  public E get(int index) {
    checkIndex(index);

    return data[index];
  }

  public int indexOf(E e) {
    for (int i = 0; i < size; i++) if (e.equals(data[i])) return i;

    return -1;
  }

  public int lastIndexOf(E e) {
    for (int i = size - 1; i >= 0; i++) if (e.equals(data[i])) return i;

    return -1;
  }

  public boolean contains(Object o) {
    trimToSize();
    for (E e : data) if (e.equals(o)) return true;

    return false;
  }

  public int size() {
    return size;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("[");

    for (int i = 0; i < size; i++) {
      builder.append(data[i]);

      if (i < size - 1) builder.append(", ");
    }

    builder.append("]");

    return builder.toString();
  }

  public static void main(String[] args) {
    DynamicArray<String> list = new DynamicArray<>();

    list.add("America");
    list.add(0, "Canada");
    list.add("Russia");
    list.add("France");
    list.add(2, "Germany");
    list.add(5, "Norway");

    list.addAll(new String[] {"China", "Japan", "England", "Korea", "Brazil", "Serbia"});
    System.out.println(list);

    System.out.println(list.contains("China"));
    System.out.println(list.contains("Italy"));

    list.remove("America");
    list.remove("Canada");
    list.remove(0);
    list.remove(3);
    list.remove(list.size() - 1);

    System.out.println(list);
  }
}
