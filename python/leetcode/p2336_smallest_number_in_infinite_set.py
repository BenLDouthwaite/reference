import heapq


class SmallestInfiniteSet:

    def __init__(self):
        self.min_heap = []
        self.vals_set = set()
        self.min = 1

    def popSmallest(self) -> int:
        if self.min_heap and self.min_heap[0] < self.min:
            ret = heapq.heappop(self.min_heap)
            self.vals_set.remove(ret)
            return ret
        else:
            ret = self.min
            self.min += 1
            return ret

    def addBack(self, num: int) -> None:
        if num < self.min:
            if num not in self.vals_set:
                heapq.heappush(self.min_heap, num)
                self.vals_set.add(num)