from typing import List

def pivot_index(nums: List[int]) -> int:

    # TODO Can simplify this down to avoid 2 passes.

    running_total = 0
    sum_arr = list()
    for i in range(len(nums)):
        n = nums[i]
        running_total += n
        sum_arr.append(running_total)

    total = running_total

    l_sum = 0
    for i in range(len(nums)):
        n = nums[i]
        r_sum = total - l_sum - n
        if l_sum == r_sum:
            return i
        l_sum = sum_arr[i]
    return -1