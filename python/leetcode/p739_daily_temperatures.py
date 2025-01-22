from collections import deque
from typing import List

class Solution:

    # My solution, optimised after seeing others
    def dailyTemperatures(self, temperatures: List[int]) -> List[int]:
        monotonic_stack = deque()
        n = len(temperatures)
        res = [0] * n
        for i, temp in enumerate(temperatures):

            # While there are items on the stack that are smaller than the current temperature
            while monotonic_stack and temperatures[monotonic_stack[-1]] < temp:
                # For each lower temp item, update how many temperatures had to be seen before a larger one was found
                index_of_lower_temp = monotonic_stack.pop()
                res[index_of_lower_temp] = i - index_of_lower_temp

            # Add the visited temp to the stack once it's been processed
            monotonic_stack.append(i)

        return res

    # First pass while learning monotonic stacks
    def dailyTemperatures_first_pass(self, temperatures: List[int]) -> List[int]:
        monotonic_stack = deque()
        n = len(temperatures)
        res = [0] * n
        for i in range(n - 1, -1, -1):
            temp = temperatures[i]

            # Need to reverse the stack to start from the smallest element on the top / right of the stack
            for stack_index in reversed(monotonic_stack):
                if temperatures[stack_index] > temp:
                    res[i] = stack_index - i
                    break

            # All values get placed on the stack after their higher value is found (or not)
            # When adding to a monotonically decreasing stack:
            # larger elements at the bottom (smaller index), smaller elements at the top (larger index)
            while monotonic_stack and temperatures[monotonic_stack[-1]] <= temp:
                monotonic_stack.pop()
            monotonic_stack.append(i)

        return res