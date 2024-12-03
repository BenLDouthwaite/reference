from typing import List


def max_area(height: List[int]) -> int:

    n = len(height)
    ptr_l = 0
    ptr_r = 1

    max_area_found = 0

    while ptr_r < n:
        l = height[ptr_l]
        r = height[ptr_r]

        width = ptr_r - ptr_l
        h = min(l, r)

        area = width * h
        if area > max_area_found:
            max_area_found = area

        if ptr_r >= n - 1:
            ptr_l += 1
            while ptr_l < n and height[ptr_l] <= l:
                ptr_l += 1
            ptr_r = ptr_l + 1
        else:
            ptr_r += 1

        # TODO How to retain calculation?

    return max_area_found