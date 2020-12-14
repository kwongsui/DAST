package pro.kwongsui.dast.linkedlist;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import pro.kwongsui.dast.util.IntArrayConverter;

class DoublyLinkedListTest {

    DoublyLinkedList dll;

    @BeforeEach
    void setUp() {
        dll = new DoublyLinkedList();
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
            dll.add(v);
        }
        assertEquals(output, dll.get(input2).value);
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
            dll.add(v);
        }
        dll.remove(input2);
        assertEquals(output, dll.get(input3).value);
    }

    @ParameterizedTest
    @CsvSource({
        "'0,1,2,3,4', 5",
        "'0,1,2,3,4', -1"
    })
    void get(
        @ConvertWith(IntArrayConverter.class) int[] input, int input2) {
        for (int v : input) {
            dll.add(v);
        }
        assertNull(dll.get(input2));
    }
}