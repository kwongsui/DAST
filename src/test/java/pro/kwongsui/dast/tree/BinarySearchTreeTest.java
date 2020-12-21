package pro.kwongsui.dast.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import pro.kwongsui.dast.util.IntegerArrayConverter;

class BinarySearchTreeTest {

    BinarySearchTree bst = new BinarySearchTree();

    @BeforeEach
    void setUp() {
        int[] input = new int[]{33, 16, 50, 13, 18, 34, 58, 15, 17, 25, 51, 66, 19, 27, 55};
        for (int num : input) {
            bst.insert(num);
        }
    }

    @ParameterizedTest
    @CsvSource({
        "14,25,'13,14,15,16,17,18,19,25,25,27,33,34,50,51,55,58,66'"
    })
    void insert(int val1, int val2, @ConvertWith(IntegerArrayConverter.class) Integer[] expected) {
        bst.insert(val1);
        bst.insert(val2);
        List<Integer> list = new ArrayList<>();
        bst.inorder(list);
        Assertions.assertIterableEquals(Arrays.asList(expected), list);
    }

    @ParameterizedTest
    @CsvSource({
        "13,'15,16,17,18,19,25,27,33,34,50,51,55,58,66'",
        "55,'13,15,16,17,18,19,25,27,33,34,50,51,58,66'",
        "18,'13,15,16,17,19,25,27,33,34,50,51,55,58,66'"
    })
    void delete(int val, @ConvertWith(IntegerArrayConverter.class) Integer[] expected) {
        bst.delete(val);
        List<Integer> list = new ArrayList<>();
        bst.inorder(list);
        Assertions.assertIterableEquals(Arrays.asList(expected), list);
    }

    @ParameterizedTest
    @CsvSource({
        "60, "
            + "'13,15,16,17,18,19,25,27,33,34,50,51,55,58,60,60,60,66', "
            + "'13,15,16,17,18,19,25,27,33,34,50,51,55,58,66'"
    })
    void deleteSame(int val,
        @ConvertWith(IntegerArrayConverter.class) Integer[] expected1,
        @ConvertWith(IntegerArrayConverter.class) Integer[] expected2) {
        bst.insert(val);
        bst.insert(val);
        bst.insert(val);

        List<Integer> list = new ArrayList<>();
        bst.inorder(list);
        Assertions.assertIterableEquals(Arrays.asList(expected1), list);

        bst.deleteSame(val);
        list.clear();
        bst.inorder(list);
        Assertions.assertIterableEquals(Arrays.asList(expected2), list);
    }

    @ParameterizedTest
    @CsvSource({
        "40,'null'",
        "33,33"
    })
    void search(int val, String expected) {
        if (expected.equals("null")) {
            Assertions.assertNull(bst.search(val));
        } else {
            Assertions.assertEquals(Integer.parseInt(expected), bst.search(val).val);
        }
    }

    @ParameterizedTest
    @CsvSource({
        "25,'25,25,25'"
    })
    void searchSame(int val, @ConvertWith(IntegerArrayConverter.class) Integer[] expected) {
        bst.insert(val);
        bst.insert(val);
        List<Integer> list = new ArrayList<>();
        for (TreeNode node : bst.searchSame(25)) {
            list.add(node.val);
        }
        Assertions.assertIterableEquals(Arrays.asList(expected), list);
    }

    @Test
    void searchMax() {
        Assertions.assertEquals(66, bst.searchMax().val);
    }

    @Test
    void searchMin() {
        Assertions.assertEquals(13, bst.searchMin().val);
    }

    @ParameterizedTest
    @CsvSource({
        "25,19",
        "51,50",
        "34,33",
        "66,58",
        "17,16",
        "13,'null'",
        "23,'null'"
    })
    void searchPrev(int val, String expected) {
        if (expected.equals("null")) {
            Assertions.assertNull(bst.searchPrev(val));
        } else {
            Assertions.assertEquals(Integer.parseInt(expected), bst.searchPrev(val).val);
        }
    }

    @ParameterizedTest
    @CsvSource({
        "19,25",
        "50,51",
        "34,50",
        "13,15",
        "17,18",
        "66,'null'",
        "23,'null'"
    })
    void searchNext(int val, String expected) {
        if (expected.equals("null")) {
            Assertions.assertNull(bst.searchNext(val));
        } else {
            Assertions.assertEquals(Integer.parseInt(expected), bst.searchNext(val).val);
        }
    }

    @ParameterizedTest
    @CsvSource({
        "'33,16,13,15,18,17,25,19,27,50,34,58,51,55,66'"
    })
    void preOrder(@ConvertWith(IntegerArrayConverter.class) Integer[] expected) {
        List<Integer> list = new ArrayList<>();
        bst.preorder(list);
        Assertions.assertIterableEquals(Arrays.asList(expected), list);
    }

    @ParameterizedTest
    @CsvSource({
        "'13,15,16,17,18,19,25,27,33,34,50,51,55,58,66'"
    })
    void inOrder(@ConvertWith(IntegerArrayConverter.class) Integer[] expected) {
        List<Integer> list = new ArrayList<>();
        bst.inorder(list);
        Assertions.assertIterableEquals(Arrays.asList(expected), list);
    }

    @ParameterizedTest
    @CsvSource({
        "'15,13,17,19,27,25,18,16,34,55,51,66,58,50,33'"
    })
    void postOrder(@ConvertWith(IntegerArrayConverter.class) Integer[] expected) {
        List<Integer> list = new ArrayList<>();
        bst.postorder(list);
        Assertions.assertIterableEquals(Arrays.asList(expected), list);
    }

    @ParameterizedTest
    @CsvSource({
        "'33,16,50,13,18,34,58,15,17,25,51,66,19,27,55'"
    })
    void bfs(@ConvertWith(IntegerArrayConverter.class) Integer[] expected) {
        List<Integer> list = new ArrayList<>();
        bst.bfs(list);
        Assertions.assertIterableEquals(Arrays.asList(expected), list);
    }
}