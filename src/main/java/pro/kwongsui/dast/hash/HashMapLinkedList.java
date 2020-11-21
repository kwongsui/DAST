package pro.kwongsui.dast.hash;

public class HashMapLinkedList<K, V> {

  private static final float DEFAULT_FACTOR = .75f;
  private static final int DEFAULT_CAPACITY = 10;
  private final float factor; // 散列表装载因子
  private int capacity; // 散列表已用容量
  private int size;  // 散列表已有数据量
  private int count = 0; // 旧散列表已迁移数据量
  private int lastSlot = 0; // 上次迁移槽

  private Entry<K, V>[] slots;
  private Entry<K, V>[] helper;

  public HashMapLinkedList() {
    this(DEFAULT_CAPACITY, DEFAULT_FACTOR);
  }

  public HashMapLinkedList(int capacity, float factor) {
    this.capacity = capacity;
    this.factor = factor;

    slots = (Entry<K, V>[]) new Entry[capacity];
  }

  public V put(K key, V value) {
    V val;
    // 首先在slots中查找并修改
    val = putIf(key, value, slots);
    if (val != null) {
      return val;
    } else if (helper != null) { // 没有找到再到helper中查找并修改
      val = putIf(key, value, helper);
      if (val != null) {
        return val;
      }
    }

    val = putVal(hash(key) & (capacity - 1), key, value);
    size++;

    if (size >= capacity * factor) {
      resize(); // 扩容
    }

    if (helper != null) { // 迁移旧数据
      Entry<K, V> p, q;
      for (int i = lastSlot; i < helper.length; i++) {
        p = helper[i];
        if (p != null && p.next != null) {
          q = p.next;
          putVal(hash(q.getKey()) & (capacity - 1), q.getKey(), q.getValue());
          p.next = q.next;
          lastSlot = i;
          count++;
          break;
        }
      }
      if (count >= helper.length * factor) { // 所有数据迁移完成
        helper = null;
        lastSlot = 0;
        count = 0;
      }
    }

    return val;
  }

  private V putIf(K key, V value, Entry<K, V>[] entries) {
    int index;
    Entry<K, V> sentinel, e;
    index = hash(key) & (entries.length - 1);
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

  private V putVal(int index, K key, V value) {
    if (slots[index] == null) {
      slots[index] = new Entry<>(null, null, null); // 哨兵节点
    }

    Entry<K, V> sentinel = slots[index];
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
    helper = slots;
    capacity = (capacity << 1) + 1;
    slots = (Entry<K, V>[]) new Entry[capacity];
  }

  public V remove(K key) {
    // 先从slots删除
    V val = removeVal(key, slots);
    // 没有再从helper删除
    if (val == null && helper != null) {
      val = removeVal(key, helper);
    }

    return val;
  }

  private V removeVal(K key, Entry<K, V>[] entries) {
    int index = hash(key) & (entries.length - 1);
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
    // 先从slots查找
    V val = getVal(key, slots);
    // 没找到再从helper查找
    if (val == null && helper != null) {
      val = getVal(key, helper);
    }

    return val;
  }

  private V getVal(K key, Entry<K, V>[] entries) {
    int index = hash(key) & (entries.length - 1);
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
