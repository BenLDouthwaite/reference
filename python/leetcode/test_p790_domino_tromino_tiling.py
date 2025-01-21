from unittest import TestCase

from leetcode.p790_domino_tromino_tiling import Solution


class TestSolution(TestCase):
    def test_num_tilings(self):
        solution = Solution()
        self.assertEqual(5, solution.numTilings(n = 3))
        self.assertEqual(1, solution.numTilings(n = 1))
