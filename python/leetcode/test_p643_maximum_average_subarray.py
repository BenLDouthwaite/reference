from unittest import TestCase

from leetcode.p643_maximum_average_subarray import find_max_average


class Test(TestCase):
    def test_find_max_average(self):
        self.assertEqual(5.00, find_max_average(nums = [5], k = 1))
        self.assertEqual(-1, find_max_average(nums = [-1], k = 1))
        self.assertEqual(12.75000, find_max_average(nums = [1,12,-5,-6,50,3], k = 4))
