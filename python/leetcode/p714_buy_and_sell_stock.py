from typing import List


class Solution:
    def max_profit(self, prices: List[int], fee: int) -> int:

        no_stock_profit = 0
        holding_stock_profit = None

        for p in prices:
            if holding_stock_profit is None:
                holding_stock_profit = no_stock_profit - p
            else:
                sell_now_profit = holding_stock_profit + p - fee
                buy_now_profit = no_stock_profit - p

                if sell_now_profit > no_stock_profit:
                    no_stock_profit = sell_now_profit # If we sell now, make money on prev max

                if buy_now_profit > holding_stock_profit:
                    holding_stock_profit = buy_now_profit # If we buy now, costs less than previous buys

        return no_stock_profit