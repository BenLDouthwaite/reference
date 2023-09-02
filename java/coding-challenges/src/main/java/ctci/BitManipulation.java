package ctci;

public class BitManipulation {

    static int clearBit(int num, int i) {
        int mask = ~(1 << i);
        return num & mask;
    }

    // Range is inclusive
    static int clearBitRange(int num, int start, int end) {

        // 0 indexed, so if 'end' = 3, will be all 1s followed by 4 zeroes.
        int higherBits = (-1 << end + 1);
        System.out.println("Clear bit range higher: " + Integer.toBinaryString(higherBits));

        // The bits we want to clear on the right, so if start = 3, will be all zeroes with 2 ones
        int lowerBits = (1 << start) - 1;
        System.out.println("Clear bit range lower: " + Integer.toBinaryString(lowerBits));

        int mask = higherBits | lowerBits;
        System.out.println("Clear bit range mask: " + Integer.toBinaryString(mask));

        return num & mask;
    }

    // 5.1
    public static int insertion(int a, int b, int i, int j) {

        System.out.println("Starting val:" + Integer.toBinaryString(a));

        int res = clearBitRange(a, i, j);

        System.out.println("After clearing j through i val:" + Integer.toBinaryString(res));

        int mask = b << i;

        return res | mask;
    }
}
