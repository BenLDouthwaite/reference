from unittest import TestCase

from leetcode.p198_house_robber import Solution


class TestSolution(TestCase):
    def test_rob(self):
        solution = Solution()
        self.assertEqual(4, solution.rob(nums = [1,2,3,1]))
        self.assertEqual(12, solution.rob(nums = [2,7,9,3,1]))
