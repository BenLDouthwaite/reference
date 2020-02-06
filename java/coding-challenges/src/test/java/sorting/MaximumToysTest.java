package sorting;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MaximumToysTest {

    @Test
    public void maximumToys_problemDefinition() {

        int[] prices = {1,12,5,111,200,1000,10};
        int k = 50;

        int expectedResult = 4;

        int result = MaximumToys.maximumToys(prices, k);
        assertThat(result, is(expectedResult));
    }

    @Test
    public void maximumToys_sortedSimpleList() {

        int[] prices = {1,1,1,50};
        int k = 10;

        int expectedResult = 3;

        int result = MaximumToys.maximumToys(prices, k);
        assertThat(result, is(expectedResult));
    }
}