package pro.kwongsui.dast.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import lombok.Getter;

public class Graph<V> {

  private final int vertices;
  @Getter
  private final Map<V, LinkedList<V>> adj;
  private boolean found = false;
  @Getter
  private final StringBuilder path = new StringBuilder(); // Only for unit test

  public Graph(int v) {
    vertices = v;
    adj = new HashMap<>(vertices);
  }

  public void addEdge(V u, V v) {
    adj.put(u, adj.getOrDefault(u, new LinkedList<>()));
    adj.get(u).add(v);
    adj.put(v, adj.getOrDefault(v, new LinkedList<>()));
    adj.get(v).add(u);
  }

  public void bfs(V u, V v) {
    if (u == v) {
      return;
    }

    Map<V, V> prev = new HashMap<>(vertices);

    Map<V, Boolean> isVisited = new HashMap<>(vertices);
    isVisited.put(u, true);

    Queue<V> queue = new LinkedList<>();
    queue.offer(u);
    while (!queue.isEmpty()) {
      V w = queue.poll();
      for (V x : adj.get(w)) {
        if (!isVisited.getOrDefault(x, false)) {
          prev.put(x, w);
          if (x == v) {
            print(prev, u, v);
            return;
          }
          isVisited.put(x, true);
          queue.add(x);
        }
      }
    }
  }

  public void dfs(V u, V v) {
    if (u == v) {
      return;
    }

    found = false;

    Map<V, V> prev = new HashMap<>(vertices);
    Map<V, Boolean> isVisited = new HashMap<>(vertices);

    dfs(u, v, prev, isVisited);

    print(prev, u, v);
  }

  private void dfs(V u, V v, Map<V, V> prev, Map<V, Boolean> isVisited) {
    if (found) {
      return;
    }

    isVisited.put(u, true);
    if (u == v) {
      found = true;
      return;
    }

    for (V w : adj.get(u)) {
      if (!isVisited.getOrDefault(w, false)) {
        prev.put(w, u);
        dfs(w, v, prev, isVisited);
      }
    }
  }

  public void print(Map<V, V> prev, V u, V v) {
    if (prev.get(v) != null && u != v) {
      print(prev, u, prev.get(v));
    }
    path.append(v).append(" ");
  }
}
