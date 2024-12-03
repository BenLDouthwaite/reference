from typing import List

def max_area(height: List[int]) -> int:
    n = len(height)
    ptr_l = 0
    ptr_r = n - 1
    max_area_found = 0
    while n > ptr_r > ptr_l:
        if ptr_l is None or ptr_r is None:
            break
        l = height[ptr_l]
        r = height[ptr_r]
        width = ptr_r - ptr_l
        h = min(l, r)
        area = width * h
        if area > max_area_found:
            max_area_found = area
        if l > r:
            ptr_r -= 1
        else:
            ptr_l += 1
    return max_area_found


# Original solution below: Had extra processing that isn't needed

def get_next_valid_index_left(valid_mask, v):
    for i in range(v - 1, -1, -1):
        if valid_mask[i] == 1:
            return i
    return None

def get_next_valid_index_right(valid_mask, v):
    for i in range(v + 1, len(valid_mask)):
        if valid_mask[i] == 1:
            return i
    return None

def flag_if_surrounded(height, v, valid_mask):
    l = get_next_valid_index_left(valid_mask, v)
    r = get_next_valid_index_right(valid_mask, v)
    if l is None or r is None:
        return
    if height[l] >= height[v] <= height[r]:
        valid_mask[v] = 0
        flag_if_surrounded(height, l, valid_mask)
        flag_if_surrounded(height, r, valid_mask)
    return

def max_area_original(height: List[int]) -> int:
    n = len(height)
    ptr_l = 0
    ptr_r = n - 1

    max_area_found = 0

    valid_mask = [1] * n

    for i in range(1, n - 1):
        if height[i] == 0:
            valid_mask[i] = 0
        else:
            flag_if_surrounded(height, i, valid_mask)

    while n > ptr_r > ptr_l:
        if ptr_l is None or ptr_r is None:
            break
        l = height[ptr_l]
        r = height[ptr_r]
        width = ptr_r - ptr_l
        h = min(l, r)
        area = width * h
        if area > max_area_found:
            max_area_found = area

        if l > r:
            ptr_r = get_next_valid_index_left(valid_mask, ptr_r)
        else:
            ptr_l = get_next_valid_index_right(valid_mask, ptr_l)

    return max_area_found