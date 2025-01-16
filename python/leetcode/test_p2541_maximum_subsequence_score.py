from unittest import TestCase

from leetcode.p2541_maximum_subsequence_score import Solution


class TestSolution(TestCase):
    def test_max_score(self):
        solution = Solution()
        self.assertEqual(12, solution.maxScore(nums1 = [1,3,3,2], nums2 = [2,1,3,4], k = 3))
        self.assertEqual(30, solution.maxScore(nums1 = [4,2,3,1,1], nums2 = [7,5,10,9,6], k = 1))
        self.assertEqual(5, solution.maxScore(nums1 = [1,4], nums2 = [3,1], k = 2))
        self.assertEqual(168, solution.maxScore(nums1 = [2,1,14,12], nums2 = [11,7,13,6], k = 3))
