from collections import namedtuple
from unittest import TestCase

from leetcode.p875_koko_eating_bananas import Solution

Inputs = namedtuple('Point', ['piles', 'h'])
param_list = [
    (4, Inputs(piles = [3,6,7,11], h = 8)),
    (30, Inputs(piles = [30,11,23,4,20], h = 5)),
    (23, Inputs(piles = [30,11,23,4,20], h = 6))
]

class TestSolution(TestCase):
    def test_min_eating_speed(self):
        solution = Solution()
        for idx, params in enumerate(param_list):
            expected_answer, inputs = params
            piles, h = inputs
            with self.subTest(idx):
                result = solution.min_eating_speed(piles, h)
                self.assertEqual(expected_answer, result)
