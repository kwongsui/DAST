package pro.kwongsui.dast.string;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StringMatchTest {

    @ParameterizedTest
    @CsvSource({
        "hello, '', 0",
        "hello, ll, 2",
        "aaaaa, bba, -1"
    })
    void bf(String s, String t, int expected) {
        assertEquals(expected, new StringMatch().bf(s, t));
    }

    @ParameterizedTest
    @CsvSource({
        "hello, '', 0",
        "hello, ll, 2",
        "aaaaa, bba, -1"
    })
    void rk(String s, String t, int expected) {
        assertEquals(expected, new StringMatch().rk(s, t));
    }
}