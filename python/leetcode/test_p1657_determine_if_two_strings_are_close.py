from unittest import TestCase

from leetcode.p1657_determine_if_two_strings_are_close import close_strings


class Test(TestCase):
    def test_close_strings(self):
        self.assertEqual(close_strings("abc", "bca"), True)
        self.assertEqual(close_strings("aab", "abc"), False)
        self.assertEqual(close_strings("a", "aa"), False)
        self.assertEqual(close_strings("cabbba", "aabbss"), False)
        self.assertEqual(close_strings("abbzzca", "babzzcz"), False)
        self.assertEqual(close_strings("aaabbbbccddeeeeefffff", "aaaaabbcccdddeeeeffff"), False)
