class Solution:
    def minFlips(self, a: int, b: int, c: int) -> int:
        print(a, b, c)
        print("{:04b}".format(a), "{:04b}".format(b), "{:04b}".format(c))

        max_bits = max(a,b,c).bit_length()

        bits_to_flip = 0
        for i in range(max_bits):
            a_bit = (a >> i) & 1
            b_bit = (b >> i) & 1
            c_bit = (c >> i) & 1

            if c_bit:
                if not a_bit and not b_bit:
                    bits_to_flip += 1
            else:
                bits_to_flip += a_bit + b_bit

        return bits_to_flip