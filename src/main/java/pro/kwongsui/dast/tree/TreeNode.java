package pro.kwongsui.dast.tree;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TreeNode<E> {
  public E element;
  public TreeNode<E> left;
  public TreeNode<E> right;
  public TreeNode<E> parent;
}
