from unittest import TestCase

from leetcode.p399_evaluate_division import Solution


class TestSolution(TestCase):
    def test_calc_equation(self):
        solution = Solution()
        self.assertEqual([6.00000,0.50000,-1.00000,1.00000,-1.00000], solution.calc_equation(
            equations = [["a","b"],["b","c"]],
            values = [2.0,3.0],
            queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
        ))
        self.assertEqual([3.75000,0.40000,5.00000,0.20000], solution.calc_equation(
            equations = [["a","b"],["b","c"],["bc","cd"]],
            values = [1.5,2.5,5.0],
            queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
        ))
        self.assertEqual([0.50000,2.00000,-1.00000,-1.00000], solution.calc_equation(
            equations = [["a","b"]],
            values = [0.5],
            queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
        ))

        ## Unknown output wanted
        # self.assertEqual([0.50000, 2.00000, -1.00000, -1.00000], solution.calc_equation(
        #     equations=[["x1","x2"],["x2","x3"],["x3","x4"],["x4","x5"]],
        #     values=[3.0,4.0,5.0,6.0],
        #     queries=[["x1","x5"],["x5","x2"],["x2","x4"],["x2","x2"],["x2","x9"],["x9","x9"]]
        # ))
        # self.assertEqual([0.50000, 2.00000, -1.00000, -1.00000], solution.calc_equation(
        #     equations=[["a","b"],["c","d"]],
        #     values=[1.0,1.0],
        #     queries=[["a","c"],["b","d"],["b","a"],["d","c"]]
        # ))
