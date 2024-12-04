from typing import List


def longest_ones(nums: List[int], k) -> int:
    n = len(nums)
    ptr_l = 0
    ptr_r = 0
    max_length = 0
    tokens = k
    token_positions = set()
    while ptr_r < n:
        #print(f"PRE:  Checking from {ptr_l} to {ptr_r} inclusive, length = {ptr_r - ptr_l}. slice: {nums[ptr_l: ptr_r + 1]}")
        if nums[ptr_r]:
            ptr_r += 1
        else:
            if tokens > 0:
                nums[ptr_r] = 1
                token_positions.add(ptr_r)
                ptr_r += 1
                tokens -= 1
            else:
                if ptr_l in token_positions:
                    nums[ptr_l] = 0
                    token_positions.remove(ptr_l)
                    ptr_l += 1

                    nums[ptr_r] = 1
                    token_positions.add(ptr_r)
                    ptr_r += 1
                else:
                    ptr_l += 1
                    if ptr_l > ptr_r:
                        ptr_r = ptr_l
        #print(f"POST: Checking from {ptr_l} to {ptr_r} inclusive, length = {ptr_r - ptr_l}. slice: {nums[ptr_l: ptr_r + 1]}")
        length = ptr_r - ptr_l
        if length > max_length:
            max_length = length
    return max_length