from unittest import TestCase

from leetcode.p1137_tribonacci_number import Solution


class TestSolution(TestCase):
    def test_tribonacci(self):
        solution = Solution()
        for expected_answer, n in [
            (4, 4),
            (1389537, 25)
        ]:
            with self.subTest(n):
                result = solution.tribonacci(n)
                self.assertEqual(expected_answer, result)
