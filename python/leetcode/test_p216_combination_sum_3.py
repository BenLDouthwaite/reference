from unittest import TestCase

from leetcode.p216_combination_sum_3 import Solution


class TestSolution(TestCase):
    def test_combination_sum3(self):
        solution = Solution()
        self.assertEqual([[1,2,4]], solution.combinationSum3(k = 3, n = 7))
        self.assertEqual([[1,2,6],[1,3,5],[2,3,4]], solution.combinationSum3(k = 3, n = 9))
        self.assertEqual([], solution.combinationSum3(k = 4, n = 1))
        self.assertEqual([], solution.combinationSum3(k = 9, n = 45))
