from typing import List
from unittest import TestCase


class TestSolution(TestCase):
    def test_removeDuplicates(self):
        solution = Solution()
        self.assertEqual(2, solution.removeDuplicates([1,1,2]))
        self.assertEqual(5, solution.removeDuplicates([0,0,1,1,1,2,2,3,3,4]))
        self.assertEqual(1, solution.removeDuplicates([1]))

class Solution:

    def removeDuplicates(self, nums: List[int]) -> int:
        i, j = 0, 0

        while j < len(nums):
            if nums[j] != nums[i]:
                i += 1
                nums[i] = nums[j]
            j += 1

        return i + 1


    # Naive slow sloution
    def removeDuplicates_firstPass(self, nums: List[int]) -> int:
        if len(nums) == 0:
            return 0

        seen = []
        ptr = 0
        while ptr < len(nums):
            if nums[ptr] in seen:
                nums.pop(ptr)
            else:
                seen.append(nums[ptr])
                ptr += 1
        return len(nums)

