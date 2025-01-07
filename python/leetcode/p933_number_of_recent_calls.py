from collections import deque


class RecentCounter:
    WINDOW = 3000

    def __init__(self):
        self.counter = deque()

    def ping(self, t: int) -> int:
        self.counter.append(t)
        while self.counter[0] < t - self.WINDOW:
            self.counter.popleft()
        return len(self.counter)