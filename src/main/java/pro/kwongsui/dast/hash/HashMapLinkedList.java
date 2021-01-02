package pro.kwongsui.dast.hash;

public class HashMapLinkedList<K, V> {

  private static final float DEFAULT_FACTOR = .75f;
  private static final int DEFAULT_CAPACITY = 10;
  private final float factor; // 散列表装载因子
  private int newCapacity; // 新散列表容量
  private int oldCapacity; // 旧散列表容量
  private int newSize;  // 新散列表已有数据量
  private int oldSize;  // 旧散列表已有数据量
  private int prev; // 旧散列表上次迁移数据所在槽

  private Entry<K, V>[] newSlots;
  private Entry<K, V>[] oldSlots;

  public HashMapLinkedList() {
    this(DEFAULT_CAPACITY, DEFAULT_FACTOR);
  }

  public HashMapLinkedList(int capacity, float factor) {
    this.newCapacity = capacity;
    this.factor = factor;

    newSlots = (Entry<K, V>[]) new Entry[newCapacity];
  }

  public V put(K key, V value) {
    V val;
    // 首先在 newSlots 中查找并修改
    val = putIf(key, value, newSlots, newCapacity);
    if (val != null) {
      return val;
    } else if (oldSlots != null) { // 没有找到再到 oldSlots 中查找并修改
      val = putIf(key, value, oldSlots, oldCapacity);
      if (val != null) {
        return val;
      }
    }

    val = putVal(key, value, newSlots, newCapacity);
    newSize++;

    if (oldSlots == null && newSize >= newCapacity * factor) {
      resize(); // 扩容
    }

    if (oldSlots != null) { // 迁移旧散列表数据到新散列表
      Entry<K, V> p, q;
      while (prev < oldSlots.length) { // 待迁移数据所在槽与上一个已迁移数据所在槽之间可能有空的槽
        p = oldSlots[prev];
        if (p != null && p.next != null) {
          q = p.next;
          putVal(q.getKey(), q.getValue(), newSlots, newCapacity);
          newSize++;
          oldSize--;
          p.next = q.next;
          break;
        }
        prev++;
      }
      if (oldSize == 0) {  // 旧散列表数据迁移完成
        oldSlots = null;
        prev = 0;
      }
    }

    return val;
  }

  private V putIf(K key, V value, Entry<K, V>[] entries, int capacity) {
    int index;
    Entry<K, V> sentinel, e;
    index = hash(key) & (capacity - 1);
    sentinel = entries[index];
    if (sentinel != null) {
      e = sentinel.next;
      while (e != null) {
        if (e.getKey().equals(key)) {
          V val = e.getValue();
          e.setValue(value);
          return val;
        }
        e = e.next;
      }
    }
    return null;
  }

  private V putVal(K key, V value, Entry<K, V>[] entries, int capacity) {
    int index = hash(key) & (capacity - 1);
    if (newSlots[index] == null) {
      newSlots[index] = new Entry<>(null, null, null); // 哨兵节点
    }

    Entry<K, V> sentinel = newSlots[index];
    if (sentinel.next == null) {
      sentinel.next = new Entry<>(key, value, null); // 插入数据
    } else {
      sentinel.next = new Entry<>(key, value, sentinel.next); // 新数据插入链表头部
    }

    return value;
  }

  private int hash(Object key) {
    int h;
    return ((key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16));
  }

  private void resize() {
    oldSlots = newSlots;
    oldCapacity = newCapacity;
    oldSize = newSize;
    newCapacity = newCapacity << 1;
    newSlots = (Entry<K, V>[]) new Entry[newCapacity];
    newSize = 0;
  }

  public void remove(K key) {
    // 先从 newSlots 查找删除
    V val = removeVal(key, newSlots, newCapacity);
    // 没有再从 oldSlots 删除
    if (val == null && oldSlots != null) {
      removeVal(key, oldSlots, oldCapacity);
    }
  }

  private V removeVal(K key, Entry<K, V>[] entries, int capacity) {
    int index = hash(key) & (capacity - 1);
    Entry<K, V> sentinel = entries[index];
    Entry<K, V> p, q;
    if (sentinel != null) {
      p = sentinel;
      q = sentinel.next;
      while (q != null) {
        if (q.getKey().equals(key)) {
          V val = q.getValue();
          p.next = q.next;
          return val;
        }
        p = q;
        q = q.next;
      }
    }
    return null;
  }

  public V get(K key) {
    // 先从 newSlots 查找
    V val = getVal(key, newSlots, newCapacity);
    // 没找到再从 oldSlots 查找
    if (val == null && oldSlots != null) {
      val = getVal(key, oldSlots, oldCapacity);
    }

    return val;
  }

  private V getVal(K key, Entry<K, V>[] entries, int capacity) {
    int index = hash(key) & (capacity - 1);
    Entry<K, V> sentinel = entries[index];
    Entry<K, V> e;
    if (sentinel != null) {
      e = sentinel.next;
      while (e != null) {
        if (e.getKey().equals(key)) {
          return e.getValue();
        }
        e = e.next;
      }
    }
    return null;
  }
}
