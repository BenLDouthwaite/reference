package ctci;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class BitManipulationTest {

    @Test
    public void insertion_textbookExample_lowerBitsCleared() {
        // given
        int a = 0b10000000000;
        int b = 0b10011;
        int i = 2;
        int j = 6;

        // when
        int result = BitManipulation.insertion(a, b, i, j);

        // then
        assertThat(result).isEqualTo(0b10001001100);
    }

    @Test
    public void insertion_allBitsSet() {
        // given
        int a = 0b11111111111;
        int b = 0b10011;
        int i = 2;
        int j = 6;

        // when
        int result = BitManipulation.insertion(a, b, i, j);

        // then
        String binaryResult = Integer.toBinaryString(result);
        int expected = 0b11111001111;
        String expectedBinary = Integer.toBinaryString(expected);
        assertThat(binaryResult).isEqualTo(expectedBinary);
    }

    @Test
    public void insertion_insertAllZeroes() {
        // given
        int a = 0b11111111111;
        int b = 0b00000;
        int i = 2;
        int j = 6;

        // when
        int result = BitManipulation.insertion(a, b, i, j);

        // then
        String binaryResult = Integer.toBinaryString(result);
        int expected = 0b11110000011;
        String expectedBinary = Integer.toBinaryString(expected);
        assertThat(binaryResult).isEqualTo(expectedBinary);
    }
}