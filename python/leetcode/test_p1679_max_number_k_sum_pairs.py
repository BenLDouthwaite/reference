from unittest import TestCase

from leetcode.p1679_max_number_k_sum_pairs import max_operations


class Test(TestCase):
    def test_max_operations(self):
        self.assertEqual(2, max_operations(nums = [1,2,3,4], k = 5))
        self.assertEqual(4, max_operations(nums = [1,4,1,4,1,4,1,4], k = 5))
        self.assertEqual(0, max_operations(nums = [1,4,1,4,1,4,1,4], k = 6))
        self.assertEqual(1, max_operations(nums = [3,1,3,4,3], k = 6))
        self.assertEqual(1, max_operations(nums = [1,1,1,1,4,5,6,6,6,7,8], k = 6))
        self.assertEqual(1, max_operations(nums = [1,1,1,1,1,1,1,1,1,1,1,1,1,4,5,6,6,6,7,8], k = 6))