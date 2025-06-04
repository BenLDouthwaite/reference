from unittest import TestCase

from leetcode.p790_domino_tromino_tiling import Solution


class TestSolution(TestCase):
    def test_num_tilings(self):
        solution = Solution()
        # self.assertEqual(1, solution.numTilings(n=1))
        # self.assertEqual(2, solution.numTilings(n=2))
        # self.assertEqual(5, solution.numTilings(n=3))
        # self.assertEqual(11, solution.numTilings(n=4))
        # self.assertEqual(24, solution.numTilings(n=5))
        # self.assertEqual(53, solution.numTilings(n=6))
        # self.assertEqual(117, solution.numTilings(n=7))
        self.assertEqual(-1, solution.numTilings(n=30))

