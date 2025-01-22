from unittest import TestCase

from leetcode.p739_daily_temperatures import Solution


class TestSolution(TestCase):
    def test_daily_temperatures(self):
        solution = Solution()
        self.assertEqual([1,1,4,2,1,1,0,0] , solution.dailyTemperatures(temperatures = [73,74,75,71,69,72,76,73]))
        self.assertEqual([1,1,1,0] , solution.dailyTemperatures(temperatures = [30,40,50,60]))
        self.assertEqual([1,1,0] , solution.dailyTemperatures(temperatures = [30,60,90]))
