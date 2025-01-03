from unittest import TestCase

from leetcode.p2215_difference_of_two_arrays import find_difference


class Test(TestCase):
    def test_find_difference(self):
        self.assertEqual(find_difference([1,2,3],[2,4,6]), [[1,3],[4,6]])
        self.assertEqual(find_difference([1,2,3,3],[1,1,2,2]), [[3],[]])
