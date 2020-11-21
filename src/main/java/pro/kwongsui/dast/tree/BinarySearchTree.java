package pro.kwongsui.dast.tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTree<E> {
  private final Comparator<E> comparator;

  public List<E> list = new ArrayList<>(); // Only for unit test
  private TreeNode<E> tree;

  public BinarySearchTree() {
    comparator = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
  }

  public BinarySearchTree(Comparator<E> c) {
    comparator = c;
  }

  /**
   * 支持重复数据插入，重复数据当作大于已有数据处理
   */
  public void insert(E e) {
    TreeNode<E> newTreeNode = new TreeNode<>(e, null, null, null);
    if (tree == null) {
      tree = newTreeNode;
    } else {
      TreeNode<E> p = null, q = tree;
      while (q != null) {
        if (comparator.compare(e, q.element) < 0) {
          p = q;
          q = q.left;
        } else {
          p = q;
          q = q.right;
        }
      }
      if (comparator.compare(e, p.element) < 0) {
        p.left = newTreeNode;
      } else {
        p.right = newTreeNode;
      }
      newTreeNode.parent = p;
    }
  }

  /**
   * 1. 待删除的节点没有左子树
   * 2. 待删除的节点有左子树，考虑两种情况：
   *    2.1 左子节点没有右子节点
   *    2.2 左子节点有右子节点，找到左子树中的最右节点
   */
  public void delete(E e) {
    TreeNode<E> p = search(e);
    if (p == null) {
      return;
    }
    if (p.left == null) {
      if (p.parent.left == p) {
        p.parent.left = p.right;
      } else {
        p.parent.right = p.right;
      }
      // 删除要确保父子节点同步更新
      if (p.right != null) {
        p.right.parent = p.parent;
      }
    } else {
      TreeNode<E> q = p.left;
      if (q.right == null) {
        p.element = q.element;
        p.left = q.left;
      } else {
        while (q.right != null) {
          q = q.right;
        }
        p.element = q.element;
        q.parent.right = q.left;
      }
      // 删除要确保父子节点同步更新
      if (q.left != null) {
        q.left.parent = q.parent;
      }
    }
  }

  public void deleteSame(E e) {
    List<TreeNode<E>> list = searchSame(e);
    for (TreeNode<E> node : list) {
      delete(node.element);
    }
  }

  public TreeNode<E> search(E e) {
    TreeNode<E> p = tree;
    while (p != null) {
      if (comparator.compare(e, p.element) < 0) {
        p = p.left;
      } else if (comparator.compare(e, p.element) > 0) {
        p = p.right;
      } else {
        return p;
      }
    }
    return null;
  }

  public List<TreeNode<E>> searchSame(E e) {
    List<TreeNode<E>> list = new ArrayList<>();
    TreeNode<E> p = tree;
    while (p != null) {
      if (comparator.compare(e, p.element) < 0) {
        p = p.left;
      } else {
        if (comparator.compare(e, p.element) == 0) {
          list.add(p);
        }
        p = p.right;
      }
    }
    return list;
  }

  public TreeNode<E> searchMax() {
    if (tree == null) {
      return null;
    }
    TreeNode<E> p = tree;
    while (p.right != null) {
      p = p.right;
    }
    return p;
  }

  public TreeNode<E> searchMin() {
    if (tree == null) {
      return null;
    }
    TreeNode<E> p = tree;
    while (p.left != null) {
      p = p.left;
    }
    return p;
  }

  /**
   * 中序遍历中的前驱节点
   * 1. 若结点treeNode的左子树不为空，则treeNode的前驱是它的左子树中最右节点
   * 2. 若结点treeNode的左子树为空，从结点treeNode开始向上查找，直到遇到一个结点，其左子节点不再是结点
   *    treeNode的祖先
   */
  public TreeNode<E> searchPrev(E e) {
    TreeNode<E> treeNode = search(e);
    if (treeNode == null) {
      return null;
    }
    TreeNode<E> p;
    if (treeNode.left != null) {
      p = treeNode.left;
      while (p.right != null) {
        p = p.right;
      }
    } else {
      p = treeNode.parent;
      TreeNode<E> q = treeNode;
      while (p != null && p.left == q) {
        q = p;
        p = p.parent;
      }
    }
    return p;
  }

  /**
   * 中序遍历中的后继节点
   * 1. 若结点treeNode的右子树不为空，则treeNode的前驱是它的右子树中最左节点
   * 2. 若结点treeNode的右子树为空，从结点treeNode开始向上查找，直到遇到一个结点，其右子节点不再是结点
   *    treeNode的祖先
   */
  public TreeNode<E> searchNext(E e) {
    TreeNode<E> treeNode = search(e);
    if (treeNode == null) {
      return null;
    }
    TreeNode<E> p;
    if (treeNode.right != null) {
      p = treeNode.right;
      while (p.left != null) {
        p = p.left;
      }
    } else {
      p = treeNode.parent;
      TreeNode<E> q = treeNode;
      while (p != null && p.right == q) {
        q = p;
        p = p.parent;
      }
    }
    return p;
  }

  public void preOrder() {
    preOrder(tree);
  }

  private void preOrder(TreeNode<E> treeNode) {
    if (treeNode == null) {
      return;
    }
    list.add(treeNode.element);
    preOrder(treeNode.left);
    preOrder(treeNode.right);
  }

  public void inOrder() {
    inOrder(tree);
  }

  private void inOrder(TreeNode<E> treeNode) {
    if (treeNode == null) {
      return;
    }
    inOrder(treeNode.left);
    list.add(treeNode.element);
    inOrder(treeNode.right);
  }

  public void postOrder() {
    postOrder(tree);
  }

  public void postOrder(TreeNode<E> treeNode) {
    if (treeNode == null) {
      return;
    }
    postOrder(treeNode.left);
    postOrder(treeNode.right);
    list.add(treeNode.element);
  }

  public void bfs() {
    if (tree == null) {
      return;
    }
    Queue<TreeNode<E>> queue = new LinkedList<>();
    queue.offer(tree);
    while (!queue.isEmpty()) {
      TreeNode<E> treeNode = queue.poll();
      list.add(treeNode.element);
      if (treeNode.left != null) {
        queue.offer(treeNode.left);
      }
      if (treeNode.right != null) {
        queue.offer(treeNode.right);
      }
    }
  }

  public int height() {
    return height(tree);
  }

  private int height(TreeNode<E> treeNode) {
    // 高度从0开始计算
    if (treeNode == null) {
      return -1;
    }
    return Math.max(height(treeNode.left), height(treeNode.right)) + 1;
  }
}
