package pro.kwongsui.dast.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import pro.kwongsui.dast.util.IntArrayConverter;

class SinglyLinkedListTest {

    SinglyLinkedList sll;

    @BeforeEach
    void setUp() {
        sll = new SinglyLinkedList();
    }

    @ParameterizedTest
    @CsvSource({
        "'0,1,2,3,4', 0, 0",
        "'0,1,2,3,4', 1, 1",
        "'0,1,2,3,4', 4, 4"
    })
    void add(
        @ConvertWith(IntArrayConverter.class) int[] input, int input2, int output) {
        for (int v : input) {
            sll.add(v);
        }
        assertEquals(output, sll.get(input2).value);
    }

    @ParameterizedTest
    @CsvSource({
        "'0,1,2,3,4', 0, 3, 3",
        "'0,1,2,3,4', 4, 1, 1",
        "'0,1,2,3,4', 2, 4, 4",
        "'0,1,2,3,4', 5, 4, 4"
    })
    void remove(
        @ConvertWith(IntArrayConverter.class) int[] input, int input2, int input3, int output) {
        for (int v : input) {
            sll.add(v);
        }
        sll.remove(input2);
        assertEquals(output, sll.get(input3).value);
    }

    @ParameterizedTest
    @CsvSource({
        "'0,1,2,3,4', 5",
        "'0,1,2,3,4', -1"
    })
    void get(
        @ConvertWith(IntArrayConverter.class) int[] input, int input2) {
        for (int v : input) {
            sll.add(v);
        }
        assertNull(sll.get(input2));
    }
}