package pro.kwongsui.dast.sort;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import pro.kwongsui.dast.util.IntArrayConverter;

class SortLinearTest {

    @ParameterizedTest
    @CsvSource({
        "'230,331,231,343,453,454,34,45,345,59,9',"
            + "3,'9,34,45,59,230,231,331,343,345,453,454'"
    })
    void radix(@ConvertWith(IntArrayConverter.class) int[] input,int n,
        @ConvertWith(IntArrayConverter.class) int[] expected) {
        new SortLinear().radix(input,n);
        assertArrayEquals(expected,input);
    }

    @ParameterizedTest
    @CsvSource({
        "'230,331,231,343,453,454,34,45,345,59,9',"
            + "11,'9,34,45,59,230,231,331,343,345,453,454'"
    })
    void count(@ConvertWith(IntArrayConverter.class) int[] input,int n,
        @ConvertWith(IntArrayConverter.class) int[] expected) {
        new SortLinear().count(input,n);
        assertArrayEquals(expected,input);
    }
}