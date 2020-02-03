import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SockMerchantTest {

    @Test
    public void testSockMerchant_onePair_notMatching() {
        int[] socks = {1,2};
        testSocksMatching(socks, 0);
    }

    @Test
    public void testSockMerchant_twoPairs_matching() {
        int[] socks = {1,2,1,2};
        testSocksMatching(socks, 2);
    }

    @Test
    public void testSockMerchant_problemDefExample() {
        int[] socks = {1, 2, 1, 2, 1, 3, 2};
        testSocksMatching(socks, 2);
    }

    @Test
    public void testSockMerchant_noSocks() {
        int[] socks = {};
        testSocksMatching(socks, 0);
    }

    @Test
    public void testSockMerchant_manySocksNoPairs() {
        int[] socks = {1,2,3,4,5,6,7,8,9,10};
        testSocksMatching(socks, 0);
    }

    @Test
    public void testSockMerchant_manySocksOfOneColour() {
        int[] socks = {1,1,1,1,1,1,1,1,1,1,1,1};
        testSocksMatching(socks, 6);
    }

    @Test
    public void testSockMerchant_manySocksAllPairs() {
        int[] socks = {1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10};
        testSocksMatching(socks, 10);
    }

    @Test
    public void testSockMerchant_manySocksManyPairsOneRemainderForEachColour() {
        int[] socks = {1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10};
        testSocksMatching(socks, 10);
    }

    private void testSocksMatching(int[] socks, int numberOfPairsExpected) {
        int numberOfPairs = SockMerchant.sockMerchant(socks.length, socks);
        assertThat(numberOfPairs, is(numberOfPairsExpected));
    }
}
