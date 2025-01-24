from unittest import TestCase

from leetcode.p62_unique_paths import Solution


class TestSolution(TestCase):
    def test_unique_paths(self):
        solution = Solution()
        self.assertEqual(28, solution.unique_paths(3, 7))
        self.assertEqual(3, solution.unique_paths(3, 2))
