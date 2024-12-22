# Alternative reference implementation taked from 4HbQ on reddit
# https://old.reddit.com/r/adventofcode/comments/1hfboft/2024_day_16_solutions/m2bcfmq/

from collections import defaultdict
from heapq import heappop, heappush


# text_io_wrapper = open("./puzzleInputs/aoc_day16_debug.txt")
# text_io_wrapper = open("./puzzleInputs/aoc_day16_debug2.txt")
text_io_wrapper = open("./puzzleInputs/aoc_day16_example1.txt")
# text_io_wrapper = open("./puzzleInputs/aoc_day16_example2.txt")
# text_io_wrapper = open("./puzzleInputs/aoc_puzzle_input_2024_day16.txt")
grid_lines = text_io_wrapper.read().splitlines()

grid = {i+j*1j: c for i,r in enumerate(grid_lines)
                  for j,c in enumerate(r) if c != '#'}

start, = (p for p in grid if grid[p] == 'S')

seen = []
best = 1e9
dist = defaultdict(lambda: 1e9)
todo = [(0, t:=0, start, 1j, [start])]

while todo:
    val, _, pos, dir, path = heappop(todo)

    if val > dist[pos, dir]: continue
    else: dist[pos, dir] = val

    if grid[pos] == 'E' and val <= best:
        seen += path
        best = val

    for r, v in (1, 1), (+1j, 1001), (-1j, 1001):
        v, t, p, d = val+v, t+1, pos + dir*r, dir*r
        if p not in grid: continue
        heappush(todo, (v, t, p, d, path + [p]))


print(best, len(set(seen)))