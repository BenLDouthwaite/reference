from unittest import TestCase

from leetcode.p392_is_subsequence import is_subsequence


class Test(TestCase):
    def test_is_subsequence(self):
        self.assertEqual(True, is_subsequence("abc", "ahbgdc"))
        self.assertEqual(False, is_subsequence("axc", "ahbgdc"))
