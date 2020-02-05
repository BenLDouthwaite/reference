package warmup;

import org.junit.Test;
import warmup.JumpingOnTheClouds;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class JumpingOnTheCloudsTest {

    @Test
    public void jumpingOnClouds_problemDefinition_0() {

        int[] input = {0,0,1,0,0,1,0};
        int expectedResult = 4;

        int actualResult = JumpingOnTheClouds.jumpingOnClouds(input);
        assertThat(actualResult, is(expectedResult));
    }

    @Test
    public void jumpingOnClouds_problemDefinition_1() {

        int[] input = {0,0,0,0,1,0};
        int expectedResult = 3;

        int actualResult = JumpingOnTheClouds.jumpingOnClouds(input);
        assertThat(actualResult, is(expectedResult));
    }

    @Test
    public void jumpingOnClouds_oneStepOneJump() {
        int[] input = {0,0};
        int expectedResult = 1;

        int actualResult = JumpingOnTheClouds.jumpingOnClouds(input);
        assertThat(actualResult, is(expectedResult));
    }

    @Test
    public void jumpingOnClouds_twoStepsOneJump() {
        int[] input = {0,0,0};
        int expectedResult = 1;

        int actualResult = JumpingOnTheClouds.jumpingOnClouds(input);
        assertThat(actualResult, is(expectedResult));
    }

    @Test
    public void jumpingOnClouds_threeStepsTwoJumps() {
        int[] input = {0,0,0,0};
        int expectedResult = 2;

        int actualResult = JumpingOnTheClouds.jumpingOnClouds(input);
        assertThat(actualResult, is(expectedResult));
    }

    @Test
    public void jumpingOnClouds_CTC_1J() {
        int[] input = {0,1,0};
        int expectedResult = 1;

        int actualResult = JumpingOnTheClouds.jumpingOnClouds(input);
        assertThat(actualResult, is(expectedResult));
    }

    @Test
    public void jumpingOnClouds_CTCTC_2J() {
        int[] input = {0,1,0,1,0};
        int expectedResult = 2;

        int actualResult = JumpingOnTheClouds.jumpingOnClouds(input);
        assertThat(actualResult, is(expectedResult));
    }
}