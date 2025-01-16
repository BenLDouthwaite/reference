from unittest import TestCase

from leetcode.p994_rotting_oranges import Solution


class TestSolution(TestCase):
    def test_oranges_rotting(self):
        solution = Solution()
        self.assertEqual(4, solution.orangesRotting(grid = [[2,1,1],[1,1,0],[0,1,1]]))
        self.assertEqual(-1, solution.orangesRotting(grid = [[2,1,1],[0,1,1],[1,0,1]]))