from typing import List


def longest_subarray(nums: List[int]) -> int:
    ptr_l = 0
    n = len(nums)
    k = 1
    for r in range(n):
        if nums[r] == 0:
            k -= 1
        if k < 0:
            if nums[ptr_l] == 0:
                k += 1
            ptr_l += 1
    return r - ptr_l
