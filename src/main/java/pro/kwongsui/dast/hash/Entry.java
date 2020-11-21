package pro.kwongsui.dast.hash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Entry<K, V> {
  @Getter
  private K key;

  @Getter
  @Setter
  private V value;

  public Entry<K, V> next;
}
