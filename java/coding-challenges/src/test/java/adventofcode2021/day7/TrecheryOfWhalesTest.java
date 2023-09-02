package adventofcode2021.day7;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TrecheryOfWhalesTest {

    @Test
    public void crabAlignment_pt2() {
        // Position 5
        String input = "16,1,2,0,4,2,7,1,2,14";
        int result = TrecheryOfWhales.crabAlignment(input, true);
        assertThat(result).isEqualTo(168);
    }

    @Test
    public void crabAlignment() {
        // Position 2
        String input = "16,1,2,0,4,2,7,1,2,14";
        int result = TrecheryOfWhales.crabAlignment(input, false);
        assertThat(result).isEqualTo(37);
    }
}