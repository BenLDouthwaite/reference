from typing import List

def equal_pairs(grid: List[List[int]]) -> int:
    row_count = {}
    for row in grid:
        row_string = str(row)
        row_count[row_string] = row_count.get(row_string, 0) + 1

    col_count = {}
    for i in range(len(grid)):
        col_string = str([row[i] for row in grid])
        col_count[col_string] = col_count.get(col_string, 0) + 1

    return sum([v * col_count.get(k, 0) for k, v in row_count.items()])

