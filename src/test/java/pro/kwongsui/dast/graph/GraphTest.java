package pro.kwongsui.dast.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GraphTest {

  Graph<Integer> graph = new Graph<>(8);

  @BeforeEach
  void setUp() {
    Integer[][] edges = new Integer[][]{
        {0, 1}, {0, 3}, {1, 2}, {1, 4}, {2, 5}, {3, 4}, {4, 5}, {4, 6}, {5, 7}, {6, 7}};
    for (Integer[] edge : edges) {
      graph.addEdge(edge[0], edge[1]);
    }
  }

  @Test
  void addEdge() {
    assertIterableEquals(Arrays.asList(1, 3), graph.getAdj().get(0));
    assertIterableEquals(Arrays.asList(0, 2, 4), graph.getAdj().get(1));
    assertIterableEquals(Arrays.asList(1, 5), graph.getAdj().get(2));
    assertIterableEquals(Arrays.asList(0, 4), graph.getAdj().get(3));
    assertIterableEquals(Arrays.asList(1, 3, 5, 6), graph.getAdj().get(4));
    assertIterableEquals(Arrays.asList(2, 4, 7), graph.getAdj().get(5));
    assertIterableEquals(Arrays.asList(4, 7), graph.getAdj().get(6));
    assertIterableEquals(Arrays.asList(5, 6), graph.getAdj().get(7));
  }

  @Test
  void bfs() {
    graph.bfs(0, 6);
    assertEquals("0 1 4 6 ", graph.getPath().toString());
  }

  @Test
  void dfs() {
    graph.dfs(0, 6);
    assertEquals("0 1 2 5 4 6 ", graph.getPath().toString());
  }
}