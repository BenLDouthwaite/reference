package strings;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AlternatingCharactersTest {

    @Test
    public void alternatingCharacters_pd1() {
        String input = "AAAA";

        int expectedOutput = 3;

        int result = AlternatingCharacters.alternatingCharacters(input);
        assertThat(result, is(expectedOutput));
    }

    @Test
    public void alternatingCharacters_pd2() {
        String input = "BBBBB";

        int expectedOutput = 4;

        int result = AlternatingCharacters.alternatingCharacters(input);
        assertThat(result, is(expectedOutput));
    }

    @Test
    public void alternatingCharacters_pd3() {
        String input = "ABABABAB";

        int expectedOutput = 0;

        int result = AlternatingCharacters.alternatingCharacters(input);
        assertThat(result, is(expectedOutput));
    }

    @Test
    public void alternatingCharacters_pd4() {
        String input = "BABABA";

        int expectedOutput = 0;

        int result = AlternatingCharacters.alternatingCharacters(input);
        assertThat(result, is(expectedOutput));
    }

    @Test
    public void alternatingCharacters_pd5() {
        String input = "AAABBB";

        int expectedOutput = 4;

        int result = AlternatingCharacters.alternatingCharacters(input);
        assertThat(result, is(expectedOutput));
    }
}