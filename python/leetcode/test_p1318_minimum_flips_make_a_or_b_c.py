from unittest import TestCase

from leetcode.p1318_minimum_flips_make_a_or_b_c import Solution


class TestSolution(TestCase):
    def test_min_flips(self):
        solution = Solution()
        self.assertEqual(3, solution.minFlips(a = 2, b = 6, c = 5))
        self.assertEqual(1, solution.minFlips(a = 4, b = 2, c = 7))
        self.assertEqual(0, solution.minFlips(a = 1, b = 2, c = 3))
