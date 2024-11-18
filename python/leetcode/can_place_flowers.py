# https://leetcode.com/problems/can-place-flowers
from typing import List

def can_place_flowers(flowerbed: List[int], n: int) -> bool:
    available_plots = 0

    # Starting at 1 is a hack to avoid checking the left boundary on every iteration.
    consecutive_empty = 1
    for i in range(0, len(flowerbed)):
        if flowerbed[i] != 0:
            if consecutive_empty >= 3:
                available_plots += (consecutive_empty - 1) // 2

                # Optimisation, break early if already successful
                if available_plots >= n:
                    return True
            consecutive_empty = 0
        else:
            consecutive_empty += 1
    if consecutive_empty >= 2:
        available_plots += consecutive_empty // 2
    return available_plots >= n