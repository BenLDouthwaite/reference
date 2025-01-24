from unittest import TestCase

from leetcode.p1143_longest_common_subsequence import Solution


class TestSolution(TestCase):
    def test_longest_common_subsequence(self):
        solution = Solution()
        self.assertEqual(3, solution.longest_common_subsequence(text1 = "abcde", text2 = "ace"))
        self.assertEqual(3, solution.longest_common_subsequence(text1 = "abc", text2 = "abc"))
        self.assertEqual(0, solution.longest_common_subsequence(text1 = "abc", text2 = "def"))
        self.assertEqual(1, solution.longest_common_subsequence(text1 = "bsbininm", text2 = "jmjkbkjkv"))
        self.assertEqual(1, solution.longest_common_subsequence(text1 = "bb", text2 = "bo"))
        self.assertEqual(4, solution.longest_common_subsequence(text1 = "ghebrgc", text2 = "hcbgcrcbh"))
        self.assertEqual(2, solution.longest_common_subsequence(text1 = "drc", text2 = "crc")) # rc goal
        self.assertEqual(5, solution.longest_common_subsequence(text1 = "abcba", text2 = "abcbcba"))