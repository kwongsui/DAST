package pro.kwongsui.dast.sort;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import pro.kwongsui.dast.util.IntArrayConverter;

class SortQuickTest {

    @ParameterizedTest
    @CsvSource({
        "'6,9,0,3,1,2,5,4,8,7', '0,1,2,3,4,5,6,7,8,9'"
    })
    void quick(@ConvertWith(IntArrayConverter.class) int[] input,
        @ConvertWith(IntArrayConverter.class) int[] expected) {
        new SortQuick().quick(input, input.length);
        assertArrayEquals(expected, input);
    }

    @ParameterizedTest
    @CsvSource({
        "'6,9,0,3,1,2,5,4,8,7', '0,1,2,3,4,5,6,7,8,9'"
    })
    void quickNonRecursion(@ConvertWith(IntArrayConverter.class) int[] input,
        @ConvertWith(IntArrayConverter.class) int[] expected) {
        new SortQuick().quickNonRecursion(input);
        assertArrayEquals(expected, input);
    }
}
