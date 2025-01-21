from typing import List


class Solution:
    def rob(self, nums: List[int]) -> int:

        n = len(nums)
        if n <= 2:
            return max(nums)

        i_scores = {-1: 0, 0: nums[0], 1: nums[1]}

        for i in range(n - 3):
            # if i + 2 < n:
            i_scores[i + 2] = nums[i + 2] + max(i_scores[i], i_scores[i - 1])
            # if i + 3 < n:
            i_scores[i + 3] = nums[i + 3] + max(i_scores[i + 1], i_scores[i])

        return max(i_scores[n - 1], i_scores[n - 2])
