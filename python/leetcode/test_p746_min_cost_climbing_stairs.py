from unittest import TestCase

from leetcode.p746_min_cost_climbing_stairs import Solution


class TestSolution(TestCase):
    def test_min_cost_climbing_stairs(self):
        solution = Solution()
        self.assertEqual(15, solution.minCostClimbingStairs(cost = [10,15,20]))
        self.assertEqual(6, solution.minCostClimbingStairs(cost = [1,100,1,1,1,100,1,1,100,1]))
        self.assertEqual(0, solution.minCostClimbingStairs(cost = [0,0,1,0]))