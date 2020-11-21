package pro.kwongsui.dast.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {

  BinarySearchTree<Integer> bst = new BinarySearchTree<>();

  @BeforeEach
  void setUp() {
    int[] nums = new int[]{33, 16, 50, 13, 18, 34, 58, 15, 17, 25, 51, 66, 19, 27, 55};
    for (int num : nums) {
      bst.insert(num);
    }
    bst.inOrder();
    Assertions.assertIterableEquals(
        Arrays.asList(13, 15, 16, 17, 18, 19, 25, 27, 33, 34, 50, 51, 55, 58, 66), bst.list);
  }

  @Test
  void insert() {
    bst.insert(14);
    bst.insert(25);
    bst.list.clear();
    bst.inOrder();
    Assertions.assertIterableEquals(
        Arrays.asList(13, 14, 15, 16, 17, 18, 19, 25, 25, 27, 33, 34, 50, 51, 55, 58, 66),
        bst.list);
  }

  @Test
  void delete() {
    bst.delete(13);
    bst.list.clear();
    bst.inOrder();
    Assertions.assertIterableEquals(
        Arrays.asList(15, 16, 17, 18, 19, 25, 27, 33, 34, 50, 51, 55, 58, 66), bst.list);

    bst.insert(13);
    bst.delete(55);
    bst.list.clear();
    bst.inOrder();
    Assertions.assertIterableEquals(
        Arrays.asList(13, 15, 16, 17, 18, 19, 25, 27, 33, 34, 50, 51, 58, 66), bst.list);

    bst.insert(55);
    bst.delete(18);
    bst.list.clear();
    bst.inOrder();
    Assertions.assertIterableEquals(
        Arrays.asList(13, 15, 16, 17, 19, 25, 27, 33, 34, 50, 51, 55, 58, 66), bst.list);
  }

  @Test
  void deleteSame() {
    bst.deleteSame(13);
    bst.list.clear();
    bst.inOrder();
    Assertions.assertIterableEquals(
        Arrays.asList(15, 16, 17, 18, 19, 25, 27, 33, 34, 50, 51, 55, 58, 66), bst.list);

    bst.insert(13);
    bst.deleteSame(55);
    bst.list.clear();
    bst.inOrder();
    Assertions.assertIterableEquals(
        Arrays.asList(13, 15, 16, 17, 18, 19, 25, 27, 33, 34, 50, 51, 58, 66), bst.list);

    bst.insert(55);
    bst.deleteSame(18);
    bst.list.clear();
    bst.inOrder();
    Assertions.assertIterableEquals(
        Arrays.asList(13, 15, 16, 17, 19, 25, 27, 33, 34, 50, 51, 55, 58, 66), bst.list);

    bst.insert(18);
    bst.insert(60);
    bst.insert(60);
    bst.insert(60);
    bst.list.clear();
    bst.inOrder();
    Assertions.assertIterableEquals(
        Arrays.asList(13, 15, 16, 17, 18, 19, 25, 27, 33, 34, 50, 51, 55, 58, 60, 60, 60, 66),
        bst.list);
    bst.deleteSame(60);
    bst.list.clear();
    bst.inOrder();
    Assertions.assertIterableEquals(
        Arrays.asList(13, 15, 16, 17, 18, 19, 25, 27, 33, 34, 50, 51, 55, 58, 66), bst.list);
  }

  @Test
  void search() {
    Assertions.assertNull(bst.search(40));
    Assertions.assertEquals(33, bst.search(33).element);
  }

  @Test
  void searchSame() {
    Assertions.assertNull(bst.search(40));
    Assertions.assertEquals(33, bst.search(33).element);
    bst.insert(25);
    bst.insert(25);
    bst.inOrder();
    List<Integer> list = new ArrayList<>();
    for (TreeNode<Integer> node : bst.searchSame(25)) {
      list.add(node.element);
    }
    Assertions.assertIterableEquals(Arrays.asList(25, 25, 25), list);
  }

  @Test
  void searchMax() {
    Assertions.assertEquals(66, bst.searchMax().element);
  }

  @Test
  void searchMin() {
    Assertions.assertEquals(13, bst.searchMin().element);
  }

  @Test
  void searchPrev() {
    Assertions.assertEquals(19, bst.searchPrev(25).element);
    Assertions.assertEquals(50, bst.searchPrev(51).element);
    Assertions.assertEquals(33, bst.searchPrev(34).element);
    Assertions.assertEquals(58, bst.searchPrev(66).element);
    Assertions.assertEquals(16, bst.searchPrev(17).element);
    Assertions.assertNull(bst.searchPrev(13));
    Assertions.assertNull(bst.searchPrev(23));
  }

  @Test
  void searchNext() {
    Assertions.assertEquals(25, bst.searchNext(19).element);
    Assertions.assertEquals(51, bst.searchNext(50).element);
    Assertions.assertEquals(50, bst.searchNext(34).element);
    Assertions.assertEquals(15, bst.searchNext(13).element);
    Assertions.assertEquals(18, bst.searchNext(17).element);
    Assertions.assertNull(bst.searchNext(66));
    Assertions.assertNull(bst.searchPrev(23));
  }

  @Test
  void preOrder() {
    bst.list.clear();
    bst.preOrder();
    Assertions.assertIterableEquals(
        Arrays.asList(33, 16, 13, 15, 18, 17, 25, 19, 27, 50, 34, 58, 51, 55, 66), bst.list);
  }

  @Test
  void inOrder() {
    bst.list.clear();
    bst.inOrder();
    Assertions.assertIterableEquals(
        Arrays.asList(13, 15, 16, 17, 18, 19, 25, 27, 33, 34, 50, 51, 55, 58, 66), bst.list);
  }

  @Test
  void postOrder() {
    bst.list.clear();
    bst.postOrder();
    Assertions.assertIterableEquals(
        Arrays.asList(15, 13, 17, 19, 27, 25, 18, 16, 34, 55, 51, 66, 58, 50, 33), bst.list);
  }

  @Test
  void bfs() {
    bst.list.clear();
    bst.bfs();
    Assertions.assertIterableEquals(
        Arrays.asList(33, 16, 50, 13, 18, 34, 58, 15, 17, 25, 51, 66, 19, 27, 55), bst.list);
  }

  @Test
  void height() {
    Assertions.assertEquals(4, bst.height());
  }
}