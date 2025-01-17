from collections import namedtuple
from unittest import TestCase

from leetcode.p2300_successful_pairs_of_spells_and_potions import Solution

Inputs = namedtuple('Point', ['spells', 'potions', 'success'])
param_list = [
    ([4,0,3], Inputs(spells = [5,1,3], potions = [1,2,3,4,5], success = 7)),
    ([2,0,2], Inputs(spells = [3,1,2], potions = [8,5,8], success = 16)),
    # ([1], Inputs(spells = [7], potions = [38,25], success = 223)),
    ([2,2,1,2,2,2,2,2,2], Inputs(spells = [16,13,7,36,16,25,22,18,29], potions = [38,25], success = 223))
    # ([], Inputs(spells = [27], potions = [30,11,5,20,19,36,39,24,20,37,33,22,32,28,36,24,40,27,36,37,38,23,39,11,40,19,37,32,25,29,28,37,31,36,32,40,38,22,17,38,20,33,29,17,36,33,35,25,28,18,17,19,40,27,40,28,40,40,40,39,17,34,36,11,22,29,22,35,35,22,18,34], success = 135))
]

class TestSolution(TestCase):
    def test_successful_pairs(self):
        solution = Solution()
        for idx, params in enumerate(param_list):
            expected_answer, inputs = params
            spells, potions, success = inputs
            with self.subTest(idx):
                result = solution.successful_pairs(spells, potions, success)
                self.assertEqual(expected_answer, result)