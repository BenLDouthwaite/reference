from unittest import TestCase

from leetcode.p1004_max_consecutive_ones import longest_ones


class Test(TestCase):
    def test_longest_ones(self):
        self.assertEqual(0, longest_ones(nums = [0,0,0,0,0], k = 0))
        self.assertEqual(1, longest_ones(nums = [0,0,0,0,0], k = 1))
        self.assertEqual(2, longest_ones(nums = [0,0,0,0,0], k = 2))
        self.assertEqual(3, longest_ones(nums = [0,0,0,0,0], k = 3))
        self.assertEqual(4, longest_ones(nums = [0,0,0,0,0], k = 4))
        self.assertEqual(5, longest_ones(nums = [0,0,0,0,0], k = 5))
        self.assertEqual(5, longest_ones(nums = [0,0,0,0,0], k = 10))
        self.assertEqual(1, longest_ones(nums = [0,0,1,0,0], k = 0))
        self.assertEqual(1, longest_ones(nums = [1,0,1,0,1], k = 0))
        self.assertEqual(3, longest_ones(nums = [1,1,1,0,1], k = 0))
        self.assertEqual(5, longest_ones(nums = [1,1,1,1,1], k = 0))
        self.assertEqual(4, longest_ones(nums = [1,1,1,0,0,0,1,1,1,1,0], k = 0))
        self.assertEqual(6, longest_ones(nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2))
        self.assertEqual(10, longest_ones(nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3))
