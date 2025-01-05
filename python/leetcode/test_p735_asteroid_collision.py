from unittest import TestCase

from leetcode.p735_asteroid_collision import asteroid_collision


class Test(TestCase):
    def test_asteroid_collision(self):
        self.assertEqual(asteroid_collision([8,-8]), [])
        self.assertEqual(asteroid_collision([10,2,-5]), [10])
        self.assertEqual(asteroid_collision([-2,2,-1,-2]), [-2])
        self.assertEqual(asteroid_collision([1,-2,-2,1]), [-2,-2,1])
        self.assertEqual(asteroid_collision([1,-1,1,-2]), [-2])
        self.assertEqual(asteroid_collision([1,1,-1,-2]), [-2])
