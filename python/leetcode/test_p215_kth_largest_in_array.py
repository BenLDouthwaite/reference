from unittest import TestCase

from leetcode.p215_kth_largest_in_array import Solution


class TestSolution(TestCase):
    def test_find_kth_largest(self):
        solution = Solution()
        self.assertEqual(5, solution.findKthLargest(nums = [3,2,1,5,6,4], k = 2))
        self.assertEqual(4, solution.findKthLargest(nums = [3,2,3,1,2,4,5,5,6], k = 4))
