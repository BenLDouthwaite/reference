from unittest import TestCase

from leetcode.p1493_longest_subarray_of_1s_after_delete import longest_subarray


class Test(TestCase):
    def test_longest_subarray(self):
        self.assertEqual(3, longest_subarray(nums = [1,1,0,1]))
        self.assertEqual(5, longest_subarray(nums = [0,1,1,1,0,1,1,0,1]))
        self.assertEqual(2, longest_subarray(nums = [1,1,1]))
