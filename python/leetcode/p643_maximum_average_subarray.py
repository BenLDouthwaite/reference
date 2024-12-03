from typing import List

def find_max_average(nums: List[int], k: int) -> float:
    total = sum(nums[:k])
    max_sum = total
    for i in range(1, len(nums) - k + 1):
        total = total - nums[i - 1] + nums[i + k - 1]
        # anecdotally slightly slower than an explicit `if x > y then y = x` check, but more concise
        # max_sum = max(max_sum, total)
        if total > max_sum:
            max_sum = total
    return max_sum / float(k)