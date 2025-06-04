import heapq
from collections import deque
from typing import List


class Solution:
    def totalCost(self, costs: List[int], k: int, candidates: int) -> int:

        costs_d = deque(costs)

        l_heap = []
        r_heap = []

        for i in range(candidates):
            if costs_d:
                heapq.heappush(l_heap, costs_d.popleft())
            if costs_d:
                heapq.heappush(r_heap, costs_d.pop())

        cost = 0
        for session in range(k):
            l_val = l_heap[0] if len(l_heap) > 0 else float('inf') # Hack to avoid index exception
            r_val = r_heap[0] if len(r_heap) > 0 else float('inf')

            if r_val < l_val or not l_heap:
                cost += heapq.heappop(r_heap)
                if costs_d:
                    heapq.heappush(r_heap, costs_d.pop())

            else: # l smaller or equal
                cost += heapq.heappop(l_heap)
                if costs_d:
                    heapq.heappush(l_heap, costs_d.popleft())
        return cost