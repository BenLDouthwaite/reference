package warmup;

import org.junit.Test;
import warmup.RepeatedString;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RepeatedStringTest {

    @Test
    public void repeatedString_problemDefinition_0() {

        String repeatingString = "aba";
        long characterLimit = 10L;

        long expectedCharacterCount  = 7L;
        long characterCount = RepeatedString.repeatedString(repeatingString, characterLimit);

        assertThat(characterCount, is(expectedCharacterCount));
    }

    @Test
    public void repeatedString_problemDefinition_1() {

        String repeatingString = "a";
        long characterLimit = 1000000000000L;

        long expectedCharacterCount  = 1000000000000L;
        long characterCount = RepeatedString.repeatedString(repeatingString, characterLimit);

        assertThat(characterCount, is(expectedCharacterCount));
    }

    @Test
    public void repeatedString_problemDefinition_7() {

        String repeatingString = "kmretasscityylpdhuwjirnqimlkcgxubxmsxpypgzxtenweirknjtasxtvxemtwxuarabssvqdnktqadhyktagjxoanknhgilnm";
        long characterLimit = 736778906400L;

        long expectedCharacterCount  = 51574523448L;
        long characterCount = RepeatedString.repeatedString(repeatingString, characterLimit);

        assertThat(characterCount, is(expectedCharacterCount));
    }

    @Test
    public void repeatedString_oneCharacter_Matching() {

        String repeatingString = "a";
        long characterLimit = 1;

        long expectedCharacterCount  = 1;
        long characterCount = RepeatedString.repeatedString(repeatingString, characterLimit);

        assertThat(characterCount, is(expectedCharacterCount));
    }

    @Test
    public void repeatedString_oneCharacter_NotMatching() {

        String repeatingString = "b";
        long characterLimit = 1;

        long expectedCharacterCount  = 0;
        long characterCount = RepeatedString.repeatedString(repeatingString, characterLimit);

        assertThat(characterCount, is(expectedCharacterCount));
    }

    @Test
    public void repeatedString_manyCharacter_Matching_NoRepeating() {

        String repeatingString = "aaabaaabaaa";
        long characterLimit = 11;

        long expectedCharacterCount  = 9;
        long characterCount = RepeatedString.repeatedString(repeatingString, characterLimit);

        assertThat(characterCount, is(expectedCharacterCount));
    }

    @Test
    public void repeatedString_manyCharacter_Matching_RepeatingOnce() {

        String repeatingString = "aaabaaabaaa";
        long characterLimit = 22;

        long expectedCharacterCount = 18;
        long characterCount = RepeatedString.repeatedString(repeatingString, characterLimit);

        assertThat(characterCount, is(expectedCharacterCount));
    }

}