from typing import List

# Needed a hint in the discussion of the problem
# Initial algorithm was correct, but actually removing items from the original list slowed it down
# By simply counting and moving on, was able to pass with no other changes.
# Could have used a hashmap, but the problem was explicitly in the 'two pointers' sections, and I need to practice that:22
def max_operations(nums: List[int], k: int) -> int:
    ptr_l, ptr_r = 0, len(nums) - 1
    nums.sort()
    count = 0
    while ptr_l < ptr_r:
        l = nums[ptr_l]
        r = nums[ptr_r]
        val = l + r
        if val == k:
            count += 1
            ptr_l += 1
            ptr_r -= 1
        elif val > k:
            ptr_r -= 1
        elif val < k:
            ptr_l += 1
    return count