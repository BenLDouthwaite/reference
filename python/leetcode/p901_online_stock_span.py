from collections import deque


class StockSpanner:

    def __init__(self):
        self.stack = deque()
        self.day = 0

    def next(self, price: int) -> int:
        self.day += 1

        while self.stack and self.stack[-1][0] <= price:
            self.stack.pop()

        if not self.stack:
            self.stack.append((price, self.day))
            return self.day

        last_day_higher_than_today = self.stack[-1][1]
        self.stack.append((price, self.day))
        return self.day - last_day_higher_than_today

# Your StockSpanner object will be instantiated and called as such:
# obj = StockSpanner()
# res = []
# for v in [100, 80, 60, 70, 60, 75, 85]:
#     val = obj.next(v)
#     res.append(val)
# print(res)


# obj = StockSpanner()
# res = []
# for v in [31,41,48,59,79]:
#     val = obj.next(v)
#     res.append(val)
# print(res)