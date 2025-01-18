from collections import deque


class Solution:
    def tribonacci(self, n: int) -> int:
        vals = deque([0, 1, 1])
        if n < 3:
            return vals[n]
        for i in range(3, n):
            i_val = sum(vals)
            vals.append(i_val)
            vals.popleft()
        return sum(vals)