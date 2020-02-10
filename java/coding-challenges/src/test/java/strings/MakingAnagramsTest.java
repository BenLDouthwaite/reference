package strings;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MakingAnagramsTest {

    @Test
    public void makeAnagram_problemDefinition() {
        String inputA = "cde";
        String inputB = "abc";

        int expectedResult = 4;

        int result = MakingAnagrams.makeAnagram(inputA, inputB);

        assertThat(result, is(expectedResult));
    }

    @Test
    public void makeAnagram_sameInputs() {
        String inputA = "test";
        String inputB = "test";

        int expectedResult = 0;

        int result = MakingAnagrams.makeAnagram(inputA, inputB);

        assertThat(result, is(expectedResult));
    }

    @Test
    public void makeAnagram_sameInputs_differentOrder() {
        String inputA = "test";
        String inputB = "estt";

        int expectedResult = 0;

        int result = MakingAnagrams.makeAnagram(inputA, inputB);

        assertThat(result, is(expectedResult));
    }

    @Test
    public void makeAnagram_extraCharacterInOneInput() {
        String inputA = "a";
        String inputB = "ab";

        int expectedResult = 1;

        int result = MakingAnagrams.makeAnagram(inputA, inputB);

        assertThat(result, is(expectedResult));
    }

    @Test
    public void makeAnagram_extraCharacterInBothInputs() {
        String inputA = "ab";
        String inputB = "ac";

        int expectedResult = 2;

        int result = MakingAnagrams.makeAnagram(inputA, inputB);

        assertThat(result, is(expectedResult));
    }


    @Test
    public void makeAnagram_severalExtraCharactersInBothInputs() {
        String inputA = "abcdefgh";
        String inputB = "aijklmno";

        int expectedResult = 14;

        int result = MakingAnagrams.makeAnagram(inputA, inputB);

        assertThat(result, is(expectedResult));
    }
}