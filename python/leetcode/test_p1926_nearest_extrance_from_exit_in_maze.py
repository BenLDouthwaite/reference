from unittest import TestCase

from leetcode.p1926_nearest_extrance_from_exit_in_maze import Solution


class TestSolution(TestCase):
    def test_nearest_exit(self):
        self.assertEqual(1, solution.nearestExit(
            maze=[
                ["+", "+", ".", "+"],
                [".", ".", ".", "+"],
                ["+", "+", "+", "."]],
            entrance=[1, 2])
                         )
        self.assertEqual(-1, solution.nearestExit(maze=[[".","+"]],entrance=[0,0]))
        self.assertEqual(-1, solution.nearestExit(maze=[["+",".","+","+","+","+","+"],["+",".","+",".",".",".","+"],["+",".","+",".","+",".","+"],["+",".",".",".","+",".","+"],["+","+","+","+","+","+","."]],
                                                  entrance=[0,1]))