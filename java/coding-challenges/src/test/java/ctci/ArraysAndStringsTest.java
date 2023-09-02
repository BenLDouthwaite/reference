package ctci;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArraysAndStringsTest {

    @Test
    public void charsUnique() {
        assertThat(ArraysAndStrings.charsUnique("a")).isTrue();
        assertThat(ArraysAndStrings.charsUnique("abcdef")).isTrue();
        assertThat(ArraysAndStrings.charsUnique("aa")).isFalse();
        assertThat(ArraysAndStrings.charsUnique("abcdefa")).isFalse();
        assertThat(ArraysAndStrings.charsUnique("abcdeff")).isFalse();
    }
}