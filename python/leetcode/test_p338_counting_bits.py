from unittest import TestCase

from leetcode.p338_counting_bits import Solution


class TestSolution(TestCase):
    def test_count_bits(self):
        solution = Solution()
        self.assertEqual([0,1,1], solution.countBits(2))
        self.assertEqual([0,1,1,2,1,2], solution.countBits(5))
        self.assertEqual([0,1,1,2,1,2,2,3,1,2,2,3,2,3,3,4,1,2,2,3,2], solution.countBits(20))
