import math
from typing import List


class Solution:
    def min_eating_speed(self, piles: List[int], h: int) -> int:
        # piles.sort()

        max_fail = 0
        min_pass = max(piles)

        while max_fail + 1 != min_pass:
            mid = (min_pass + max_fail) // 2

            total_hours = sum([math.ceil(pile / mid) for pile in piles])

            if total_hours <= h:
                min_pass = mid
            else:
                max_fail = mid

        return min_pass
