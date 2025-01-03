from unittest import TestCase

from leetcode.p2352_equal_row_and_column_pair import equal_pairs


class Test(TestCase):
    def test_equal_pairs(self):
        self.assertEqual(equal_pairs([[3,2,1],[1,7,6],[2,7,7]]), 1)
        self.assertEqual(equal_pairs([[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]), 3)

