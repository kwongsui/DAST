package pro.kwongsui.dast.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BinarySearchTree {

  private TreeNode tree;

  /**
   * 支持重复数据插入，重复数据当作大于已有数据处理
   */
  public void insert(int val) {
    TreeNode newTreeNode = new TreeNode(val, null, null, null);
    if (tree == null) {
      tree = newTreeNode;
    } else {
      TreeNode p = null, q = tree;
      while (q != null) {
        if (val < q.val) {
          p = q;
          q = q.left;
          if (q == null) {
            p.left = newTreeNode;
          }
        } else {
          p = q;
          q = q.right;
          if (q == null) {
            p.right = newTreeNode;
          }
        }
      }
      newTreeNode.parent = p;
    }
  }

  /**
   * 1. 待删除的节点没有左子树
   * 2. 待删除的节点有左子树，找到左子树中值最大的节点，替换待删除节点，考虑两种情况：
   *    2.1 左子节点没有右子节点
   *    2.2 左子节点有右子节点，找到左子树中的最右节点
   */
  public void delete(int val) {
    TreeNode p = search(val);
    if (p == null) {
      return;
    }
    if (p.left == null) {
      if (p.parent == null) {
        tree = p.right;
      } else {
        if (p.parent.left == p) {
          p.parent.left = p.right;
        } else {
          p.parent.right = p.right;
        }
      }
      // 删除要确保父子节点同步更新
      if (p.right != null) {
        p.right.parent = p.parent;
      }
    } else {
      TreeNode q = p.left;
      if (q.right == null) {
        p.val = q.val;
        p.left = q.left;
      } else {
        while (q.right != null) {
          q = q.right;
        }
        p.val = q.val;
        q.parent.right = q.left;  // q是最右节点，右子节点为空
      }
      // 删除要确保父子节点同步更新
      if (q.left != null) {
        q.left.parent = q.parent;
      }
    }
  }

  public void deleteSame(int val) {
    List<TreeNode> list = searchSame(val);
    for (TreeNode node : list) {
      delete(node.val);
    }
  }

  public TreeNode search(int val) {
    TreeNode p = tree;
    while (p != null) {
      if (val < p.val) {
        p = p.left;
      } else if (val > p.val) {
        p = p.right;
      } else {
        return p;
      }
    }
    return null;
  }

  public List<TreeNode> searchSame(int val) {
    List<TreeNode> list = new ArrayList<>();
    TreeNode p = tree;
    while (p != null) {
      if (val < p.val) {
        p = p.left;
      } else {
        if (val == p.val) {
          list.add(p);
        }
        p = p.right;
      }
    }
    return list;
  }

  public TreeNode searchMax() {
    if (tree == null) {
      return null;
    }
    TreeNode p = tree;
    while (p.right != null) {
      p = p.right;
    }
    return p;
  }

  public TreeNode searchMin() {
    if (tree == null) {
      return null;
    }
    TreeNode p = tree;
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
  public TreeNode searchPrev(int val) {
    TreeNode treeNode = search(val);
    if (treeNode == null) {
      return null;
    }
    TreeNode p;
    if (treeNode.left != null) {
      p = treeNode.left;
      while (p.right != null) {
        p = p.right;
      }
    } else {
      p = treeNode.parent;
      TreeNode q = treeNode;
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
  public TreeNode searchNext(int val) {
    TreeNode treeNode = search(val);
    if (treeNode == null) {
      return null;
    }
    TreeNode p;
    if (treeNode.right != null) {
      p = treeNode.right;
      while (p.left != null) {
        p = p.left;
      }
    } else {
      p = treeNode.parent;
      TreeNode q = treeNode;
      while (p != null && p.right == q) {
        q = p;
        p = p.parent;
      }
    }
    return p;
  }

  public void preOrder(List<Integer> list) {
    preOrder(tree, list);
  }

  private void preOrder(TreeNode treeNode, List<Integer> list) {
    if (treeNode == null) {
      return;
    }
    list.add(treeNode.val);
    preOrder(treeNode.left, list);
    preOrder(treeNode.right, list);
  }

  public void inOrder(List<Integer> list) {
    inOrder(tree, list);
  }

  private void inOrder(TreeNode treeNode, List<Integer> list) {
    if (treeNode == null) {
      return;
    }
    inOrder(treeNode.left, list);
    list.add(treeNode.val);
    inOrder(treeNode.right, list);
  }

  public void postOrder(List<Integer> list) {
    postOrder(tree, list);
  }

  private void postOrder(TreeNode treeNode, List<Integer> list) {
    if (treeNode == null) {
      return;
    }
    postOrder(treeNode.left, list);
    postOrder(treeNode.right, list);
    list.add(treeNode.val);
  }

  public void bfs(List<Integer> list) {
    if (tree == null) {
      return;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(tree);
    while (!queue.isEmpty()) {
      TreeNode treeNode = queue.poll();
      list.add(treeNode.val);
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

  private int height(TreeNode node) {
    // 高度从0开始计算
    if (node == null) {
      return -1;
    }
    return Math.max(height(node.left), height(node.right)) + 1;
  }

  public void updateDepth(TreeNode treeNode, int depth, Map<TreeNode, Integer> map) {
    if (treeNode == null) {
      return;
    }
    map.put(treeNode, depth);
    updateDepth(treeNode.left, depth + 1, map);
    updateDepth(treeNode.right, depth + 1, map);
  }
}
