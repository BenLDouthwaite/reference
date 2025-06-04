from unittest import TestCase

from leetcode.p2462_total_cost_to_hire_k_workers import Solution


class TestSolution(TestCase):
    def test_total_cost(self):
        solution = Solution()
        self.assertEqual(11, solution.totalCost(costs = [17,12,10,2,7,2,11,20,8], k = 3, candidates = 4))
        self.assertEqual(4, solution.totalCost(costs = [1,2,4,1], k = 3, candidates = 3))
        self.assertEqual(526, solution.totalCost(costs = [57,33,26,76,14,67,24,90,72,37,30], k = 11, candidates = 2))
        self.assertEqual(48, solution.totalCost([48], k = 1, candidates = 1))