from typing import List
from unittest import TestCase


class TestSolution(TestCase):
    def test_removeDuplicates(self):
        solution = Solution()
        self.assertEqual(5, solution.removeDuplicates([1,1,1,2,2,3]))
        self.assertEqual(7, solution.removeDuplicates([0,0,1,1,1,1,2,3,3]))

        self.assertEqual(2, solution.removeDuplicates([1,1]))
        self.assertEqual(2, solution.removeDuplicates([1,1,1]))
        self.assertEqual(2, solution.removeDuplicates([1,1,1,1]))

        self.assertEqual(5, solution.removeDuplicates([1,1,1,1,2,2,3]))

class Solution:

    def removeDuplicates(self, nums: List[int]) -> int:

        if len(nums) <= 2:
            return len(nums)

        i = 0
        count = 1

        for j in range(1, len(nums)):
            if nums[j] == nums[i]:
                count += 1
                if count <= 2:
                    i += 1
                    nums[i] = nums[j]
            else:
                i += 1
                nums[i] = nums[j]
                count = 1

        return i + 1

    def removeDuplicatesOriginalConvoluted(self, nums: List[int]) -> int:
        i, j = 0, 0

        if len(nums) == 1:
            return 1

        count = 1

        for j in range(1, len(nums)):

            if nums[i] == nums[j]:
                count += 1
                continue

            if count >= 2:
                nums[i + 1] = nums[i]
                i += 2
                nums[i] = nums[j]
                count = 1

            else:
                i += 1
                nums[i] = nums[j]
                count = 1

        if count > 0:
            if nums[i] == nums[j - 1] and count > 1:
                i += 1
                nums[i] = nums[j]

        return i + 1