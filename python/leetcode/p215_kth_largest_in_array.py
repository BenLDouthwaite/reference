import heapq
from typing import List

class Solution:

    def findKthLargest(self, nums: List[int], k: int) -> int:
        min_heap = []
        for num in nums:
            heapq.heappush(min_heap, num)
            if len(min_heap) > k:
                heapq.heappop(min_heap)

        return min_heap[0]

    def findKthLargest_mapCounter(self, nums: List[int], k: int) -> int:

        count = dict()
        largest = None
        for n in nums:
            count[n] = count.get(n, 0) + 1
            if largest is None or n > largest:
                largest = n

        # TODO Possibly can loop forever?
        while True:
            top_count = count.get(largest, 0)
            k = k - top_count
            if k <= 0:
                return largest
            largest -= 1
