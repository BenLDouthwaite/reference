import heapq
from typing import List


class Solution:
    def maxScore(self, nums1: List[int], nums2: List[int], k: int) -> int:

        n = len(nums1)

        pairs = [(nums1[i], nums2[i]) for i in range(n)]
        pairs.sort(key=lambda p: p[1], reverse=True)

        # Use the heap to keep track of the maximum inputs used to build the base
        heap = []

        max_result = 0

        prefix_sum = 0

        for a, b in pairs:

            heapq.heappush(heap, a)

            # Could calculate the sum each time - but more efficient to track only the diff
            prefix_sum += a

            # On each iteration through the pairs, keep only the maximum 'k'
            # But, still calculate the max result on each pass.
            if len(heap) == k:
                result = prefix_sum * b
                if result > max_result:
                    max_result = result
                prefix_sum -= heapq.heappop(heap)

        return max_result
