from unittest import TestCase

from leetcode.p714_buy_and_sell_stock import Solution


class TestSolution(TestCase):
    def test_max_profit(self):
        solution = Solution()
        self.assertEqual(8, solution.max_profit(prices = [1, 3, 2, 8, 4, 9], fee = 2))
        self.assertEqual(6, solution.max_profit(prices = [1, 3, 7, 5, 10, 3], fee = 3))
        self.assertEqual(10, solution.max_profit(prices = [1, 4, 10, 14], fee = 3))
        self.assertEqual(13, solution.max_profit(prices = [1, 4, 6, 2, 8, 3, 10, 14], fee = 3))
