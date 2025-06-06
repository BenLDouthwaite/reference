from typing import List
from unittest import TestCase


class TestSolution(TestCase):
    def test_unique_paths(self):
        solution = Solution()
        self.assertEqual(2, solution.removeElement([3,2,2,3], 3))
        self.assertEqual(5, solution.removeElement([0,1,2,2,3,0,4,2], 2))

class Solution:
    def removeElement(self, nums: List[int], val: int) -> int:
        i = 0
        while i < len(nums):
            if nums[i] == val:
                nums.pop(i)
            else:
                i += 1
        return len(nums)