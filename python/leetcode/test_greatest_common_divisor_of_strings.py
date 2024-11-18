from unittest import TestCase

from leetcode.greatest_common_divisor_of_strings import greatest_common_divisor_of_strings, gcd_recursive, gcd


class Test(TestCase):
    def test_greatest_common_divisor_of_strings(self):
        self.assertEqual(greatest_common_divisor_of_strings("ABCABC", "ABC"), "ABC")
        self.assertEqual(greatest_common_divisor_of_strings("ABABAB", "ABAB"), "AB")
        self.assertEqual(greatest_common_divisor_of_strings("LEET", "CODE"), "")

    def test_gcd_recursive(self):
        self.assertEqual(3, gcd_recursive(9, 6))
        self.assertEqual(5, gcd_recursive(10, 45))
        self.assertEqual(3, gcd_recursive(1701, 3768))

    def test_gcd(self):
        self.assertEqual(3, gcd(9, 6))
        self.assertEqual(5, gcd(10, 45))
        self.assertEqual(3, gcd(1701, 3768))
