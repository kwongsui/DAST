package pro.kwongsui.dast.sort;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import pro.kwongsui.dast.util.IntArrayConverter;

class SortMergeTest {

    @ParameterizedTest
    @CsvSource({
        "'6,9,0,3,1,2,5,4,8,7', '0,1,2,3,4,5,6,7,8,9'",
        "'11,8,3,9,7,1,2,5', '1,2,3,5,7,8,9,11'"
    })
    void merge(@ConvertWith(IntArrayConverter.class) int[] input,
        @ConvertWith(IntArrayConverter.class) int[] expected) {
        new SortMerge().merge(input, input.length);
        assertArrayEquals(expected, input);
    }

    @ParameterizedTest
    @CsvSource({
        "'6,9,0,3,1,2,5,4,8,7', '0,1,2,3,4,5,6,7,8,9'",
        "'11,8,3,9,7,1,2,5', '1,2,3,5,7,8,9,11'"
    })
    void mergeNonRecursion(@ConvertWith(IntArrayConverter.class) int[] input,
        @ConvertWith(IntArrayConverter.class) int[] expected) {
        new SortMerge().mergeNonRecursion(input);
        assertArrayEquals(expected, input);
    }
}