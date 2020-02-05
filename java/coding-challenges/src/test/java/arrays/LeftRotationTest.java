package arrays;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LeftRotationTest {

    @Test
    public void rotLeft_problemDefinition() {
        int numberOfRotations = 4;
        int[] array = {1,2,3,4,5};

        int[] expectedResult = {5,1,2,3,4};

        int[] result = LeftRotation.rotLeft(array, numberOfRotations);
        assertThat(result, is(expectedResult));
    }

    @Test
    public void rotLeft_singleRotation_twoItems() {
        int numberOfRotations = 1;
        int[] array = {1,2};

        int[] expectedResult = {2,1};

        int[] result = LeftRotation.rotLeft(array, numberOfRotations);
        assertThat(result, is(expectedResult));
    }

    @Test
    public void rotLeft_singleRotation_manyItems() {
        int numberOfRotations = 1;
        int[] array = {1,2,3,4,5,6,7,8};

        int[] expectedResult = {2,3,4,5,6,7,8,1};

        int[] result = LeftRotation.rotLeft(array, numberOfRotations);
        assertThat(result, is(expectedResult));
    }

    @Test
    public void rotLeft_manyRotations_manyItems() {
        int numberOfRotations = 4;
        int[] array = {1,2,3,4,5,6,7,8};

        int[] expectedResult = {5,6,7,8,1,2,3,4};

        int[] result = LeftRotation.rotLeft(array, numberOfRotations);
        assertThat(result, is(expectedResult));
    }

    @Test
    public void rotLeft_sameRotationsAsLength() {
        int numberOfRotations = 8;
        int[] array = {1,2,3,4,5,6,7,8};

        int[] expectedResult = {1,2,3,4,5,6,7,8};

        int[] result = LeftRotation.rotLeft(array, numberOfRotations);
        assertThat(result, is(expectedResult));
    }
}