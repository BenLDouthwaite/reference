from unittest import TestCase

from leetcode.p547_number_of_provinces import Solution


class TestSolution(TestCase):
    def test_find_circle_num(self):
        solution = Solution()
        self.assertEqual(2, solution.find_circle_num([[1,1,0],[1,1,0],[0,0,1]]))
        self.assertEqual(3, solution.find_circle_num([[1,0,0],[0,1,0],[0,0,1]]))
        self.assertEqual(1, solution.find_circle_num([[1,0,0,1],[0,1,1,0],[0,1,1,1],[1,0,1,1]]))
        self.assertEqual(3, solution.find_circle_num([[1,0,0,0,0,0,0,0,1,0,0,0,0,0,0],[0,1,1,0,0,0,0,0,0,0,0,0,0,1,0],[0,1,1,0,0,0,0,0,0,0,0,1,0,0,1],[0,0,0,1,0,1,0,0,1,0,0,0,0,1,0],[0,0,0,0,1,0,0,0,0,0,0,1,0,0,0],[0,0,0,1,0,1,0,0,0,0,0,1,0,0,0],[0,0,0,0,0,0,1,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,1,0,0,0,0,0,0,0],[1,0,0,1,0,0,0,0,1,1,1,0,0,1,0],[0,0,0,0,0,0,0,0,1,1,0,1,1,0,0],[0,0,0,0,0,0,0,0,1,0,1,1,0,0,0],[0,0,1,0,1,1,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,0,0,1,0,0,1,0,1],[0,1,0,1,0,0,0,0,1,0,0,0,0,1,0],[0,0,1,0,0,0,0,0,0,0,0,0,1,0,1]]))