package pro.kwongsui.dast.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class HuffmanCode {

  private CharNode huffmanTree;

  public String encode(String raw) {
    huffmanTree = huffmanTree(frequency(raw));
    String[] code = encode(huffmanTree);
    StringBuilder builder = new StringBuilder();
    for (char ch : raw.toCharArray()) {
      builder.append(code[ch]);
    }
    return builder.toString();
  }

  public String decode(String code) {
    CharNode cn = huffmanTree;
    StringBuilder builder = new StringBuilder();
    for (char ch : code.toCharArray()) {
      if (cn.left != null) {
        if (ch == '0') {
          cn = cn.left;
        } else {
          cn = cn.right;
        }
      }
      if (cn.left == null) {
        builder.append(cn.element);
        cn = huffmanTree;
      }
    }
    return builder.toString();
  }

  private String[] encode(CharNode tree) {
    String[] code = new String[256];
    encode(tree, code);
    return code;
  }

  private void encode(CharNode cn, String[] code) {
    if (cn == null) {
      return;
    }
    if (cn.left != null) {
      cn.left.setCode(cn.getCode() + "0");
      encode(cn.left, code);
      cn.right.setCode(cn.getCode() + "1");
      encode(cn.right, code);
    } else {
      code[cn.element] = cn.getCode();
    }
  }

  private CharNode huffmanTree(int[] freq) {
    Queue<CharNode> queue = new PriorityQueue<>(Comparator.comparingInt(CharNode::getFreq));
    for (int i = 0; i < freq.length; i++) {
      if (freq[i] > 0) {
        queue.offer(new CharNode((char) i, freq[i]));
      }
    }

    while (queue.size() > 1) {
      CharNode cn1 = queue.poll();
      CharNode cn2 = queue.poll();
      queue.offer(new CharNode(cn1.getFreq() + cn2.getFreq(), cn1, cn2));
    }

    return queue.poll();
  }

  private int[] frequency(String s) {
    int[] count = new int[256];
    for (char ch : s.toCharArray()) {
      count[ch]++;
    }
    return count;
  }
}
