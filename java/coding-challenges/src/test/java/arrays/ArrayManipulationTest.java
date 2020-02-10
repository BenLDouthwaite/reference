package arrays;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ArrayManipulationTest {

    @Test
    public void arrayManipulation() {

        int arraySize = 5;

        int[][] queries = {
                {1,2,100},
                {2,5,100},
                {3,4,100}
        };

        long expectedResult = 200;

        long result = ArrayManipulation.arrayManipulation(arraySize, queries);

        assertThat(result, is(expectedResult));
    }

    @Test
    public void arrayManipulation_tc1() {

        int arraySize = 4;

        int[][] queries = {
                {2,3,603},
                {1,1,286},
                {4,4,882}
        };

        long expectedResult = 882;

        long result = ArrayManipulation.arrayManipulation(arraySize, queries);

        assertThat(result, is(expectedResult));
    }

    @Test
    public void arrayManipulation_singleIncrement() {

        int arraySize = 2;

        int[][] queries = {
                {1,1,1},
        };

        long expectedResult = 1;

        long result = ArrayManipulation.arrayManipulation(arraySize, queries);

        assertThat(result, is(expectedResult));
    }

    @Test
    public void arrayManipulation_twoIncrements() {

        int arraySize = 2;

        int[][] queries = {
                {1,1,1},
                {1,1,1}
        };

        long expectedResult = 2;

        long result = ArrayManipulation.arrayManipulation(arraySize, queries);

        assertThat(result, is(expectedResult));
    }

    @Test
    public void arrayManipulation_twoIncrementsInRanges() {

        int arraySize = 5;

        int[][] queries = {
                {1,3,1},
                {3,5,1}
        };

        long expectedResult = 2;

        long result = ArrayManipulation.arrayManipulation(arraySize, queries);

        assertThat(result, is(expectedResult));
    }
}