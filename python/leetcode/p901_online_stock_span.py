from collections import deque


class StockSpanner:

    def __init__(self):
        self.stack = deque()

    def next(self, price: int) -> int:
        return 0

# Your StockSpanner object will be instantiated and called as such:
# obj = StockSpanner()
# param_1 = obj.next(price)