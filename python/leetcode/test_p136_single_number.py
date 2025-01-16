from unittest import TestCase

from leetcode.p136_single_number import Solution


class TestSolution(TestCase):
    def test_single_number(self):
        solution = Solution()
        self.assertEqual(1, solution.singleNumber(nums = [2,2,1]))
        self.assertEqual(4, solution.singleNumber(nums = [4,1,2,1,2]))
        self.assertEqual(1, solution.singleNumber(nums = [1]))
