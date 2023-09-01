package maps;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SherlockAnagramsTest  {

    @Test
    public void sherlockAndAnagrams_sampleInput0_1() {
        // given
        String s = "abba";

        // when
        int countOfAnagrammaticPairs = SherlockAnagrams.sherlockAndAnagrams(s);

        // then
        assertThat(countOfAnagrammaticPairs).isEqualTo(4);
    }

    @Test
    public void sherlockAndAnagrams_sampleInput0_2() {
        // given
        String s = "abcd";

        // when
        int countOfAnagrammaticPairs = SherlockAnagrams.sherlockAndAnagrams(s);

        // then
        assertThat(countOfAnagrammaticPairs).isEqualTo(0);
    }

    @Test
    public void sherlockAndAnagrams_sampleInput1_1() {
        // given
        String s = "ifailuhkqq";

        // when
        int countOfAnagrammaticPairs = SherlockAnagrams.sherlockAndAnagrams(s);

        // then
        assertThat(countOfAnagrammaticPairs).isEqualTo(3);
    }

    @Test
    public void sherlockAndAnagrams_sampleInput1_2() {
        // given
        String s = "kkkk";

        // when
        int countOfAnagrammaticPairs = SherlockAnagrams.sherlockAndAnagrams(s);

        // then
        assertThat(countOfAnagrammaticPairs).isEqualTo(10);
    }

    @Test
    public void sherlockAndAnagrams_sampleInput2_1() {
        // given
        String s = "cdcd";

        // when
        int countOfAnagrammaticPairs = SherlockAnagrams.sherlockAndAnagrams(s);

        // then
        assertThat(countOfAnagrammaticPairs).isEqualTo(5);
    }
}