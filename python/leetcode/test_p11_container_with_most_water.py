from unittest import TestCase

from leetcode.p11_container_with_most_water import max_area


class Test(TestCase):
    def test_max_area(self):
        # self.assertEqual(1, max_area([1,1]))
        # self.assertEqual(49, max_area([1,8,6,2,5,4,8,3,7]))
        self.assertEqual(49, max_area([1,2,3,4,5,6,7,8,9,8,7,6,5,4,3,2,1]))
