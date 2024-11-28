from typing import List

# Taken from leet code solution discussions
def increasing_triplet_single_pass(nums: List[int]) -> bool:
    min1 = float('inf') # very big
    min2 = float('inf') # very big
    for n in nums:
        if n <= min1:
            min1 = n
        elif n <= min2:
            min2 = n
        else:
            return True
    return False

def increasing_triplet(nums: List[int]) -> bool:
    n = len(nums)
    if n < 3:
        return False

    i, j, k = 0,1,2

    smallest_a_val = None
    largest_c_val = None
    while True:

        # while a > b
        while nums[i] >= nums[j] and j < n - 2:
            j += 1
            k = j + 1

        while nums[j] >= nums[k] and k < n - 1:
            k += 1

        a, b, c = nums[i], nums[j], nums[k]
        if a < b < c:
            return True
        if i == n - 3 and j == n - 2 and k == n - 1:
            break

        if smallest_a_val is None or a < smallest_a_val:
            smallest_a_val = a
        if largest_c_val is None or c > largest_c_val:
            largest_c_val = c

        if (j == n - 2 and k == n -1) or (nums[j] == largest_c_val):
            while nums[i] >= smallest_a_val and i != n - 3:
                i += 1
            j = i + 1
            k = j + 1
        elif k == n - 1:
            j += 1
            k = j + 1
        else:
            k += 1


    return False