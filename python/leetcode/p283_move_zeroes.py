from typing import List

# Solution updated from https://www.youtube.com/watch?v=BPb0q5J4u-k&t=242s
def move_zeroes(nums: List[int]) -> None:
    l = 0
    for r in range(len(nums)):
        if nums[r] != 0:
            nums[l], nums[r] = nums[r], nums[l]
            l += 1


move_zeroes([0,1,0,3,12])
move_zeroes([0])
move_zeroes([0, 1])
move_zeroes([0, 0, 1])
move_zeroes([1, 0, 0, 1])


# Each 'pop' operation is expensive for big 0, try to avoid
def move_zeroes_naive(nums: List[int]) -> None:
    ptr = 0
    moved = 0
    n = len(nums)
    while ptr + moved < n:
        if nums[ptr] == 0:
            nums.pop(ptr)
            nums.append(0)
            moved += 1
        else:
            ptr += 1
