from unittest import TestCase

from leetcode.p724_pivot_index import pivot_index


class Test(TestCase):
    def test_pivot_index(self):
        self.assertEqual(3, pivot_index(nums = [1,7,3,6,5,6]))
        self.assertEqual(-1, pivot_index(nums = [1,2,3]))
        self.assertEqual(0, pivot_index(nums = [2,1,-1]))
