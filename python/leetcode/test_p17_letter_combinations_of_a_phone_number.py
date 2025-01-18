from collections import namedtuple
from unittest import TestCase

from leetcode.p17_letter_combinations_of_a_phone_number import Solution

Inputs = namedtuple('Input', ['digits'])
param_list = [
    (["ad","ae","af","bd","be","bf","cd","ce","cf"], Inputs(digits = "23")),
    ([], Inputs(digits = "")),
    (["a","b","c"], Inputs(digits = "2"))
]

class TestSolution(TestCase):
    def test_letter_combinations(self):
        solution = Solution()
        for idx, params in enumerate(param_list):
            expected_answer, inputs = params
            with self.subTest(idx):
                result = solution.letter_combinations(inputs.digits)
                self.assertEqual(expected_answer, result)