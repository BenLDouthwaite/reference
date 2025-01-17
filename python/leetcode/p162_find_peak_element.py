from typing import List


class Solution:
    def findPeakElement(self, nums: List[int]) -> int:
        n = len(nums)
        if n == 1:
            return 0
        if n >= 2:
            if nums[0] > nums[1]:
                return 0
            if nums[-1] > nums[-2]:
                return n - 1

        # Need to run in O log n time = Binary search

        lb, mid, ub = 0, n // 2, n
        while True:
            print(f"In range being checked = {nums[lb: ub]}")
            mid = (lb + ub) // 2
            l, m, r = nums[mid - 1], nums[mid], nums[mid + 1]
            print(mid, l, m, r)
            if l < m > r:
                return mid
            elif r > m:
                lb = mid + 1
            elif l > m:
                ub = mid - 1

        return 0