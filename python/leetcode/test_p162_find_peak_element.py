from unittest import TestCase

from leetcode.p162_find_peak_element import Solution

param_list = [
    (2, [1,2,3,1]),
    (5, [1,2,1,3,5,6,4]),
    (0, [1]),
    (1, [1,2])
]

class TestSolution(TestCase):
    def test_find_peak_element(self):
        solution = Solution()
        for idx, params in enumerate(param_list):
            expected_answer, inputs = params
            with self.subTest(idx):
                result = solution.findPeakElement(inputs)
                self.assertEqual(expected_answer, result)
