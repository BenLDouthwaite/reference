from unittest import TestCase

from leetcode.p1732_highest_altitude import largest_altitude


class Test(TestCase):
    def test_largest_altitude(self):
        self.assertEqual(1, largest_altitude(gain = [-5,1,5,0,-7]))
        self.assertEqual(0, largest_altitude(gain = [-4,-3,-2,-1,4,3,2]))
