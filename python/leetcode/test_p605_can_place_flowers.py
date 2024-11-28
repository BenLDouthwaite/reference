from unittest import TestCase

from leetcode.p605_can_place_flowers import can_place_flowers


class Test(TestCase):
    def test_can_place_flowers(self):
        self.assertEqual(can_place_flowers([1,0,0,0,1], 1), True)
        self.assertEqual(can_place_flowers([1,0,0,0,1], 2), False)
        self.assertEqual(can_place_flowers([0,0,1,0,1], 1), True)
        self.assertEqual(can_place_flowers([1,0,1,0,0], 1), True)
        self.assertEqual(can_place_flowers([1,0,0,0], 1), True)
        self.assertEqual(can_place_flowers([1,0,0,0,0], 2), True)
