from itertools import combinations

input = """
............
........0...
.....0......
.......0....
....0.......
......A.....
............
............
........A...
.........A..
............
............
""".strip()

input = open("./puzzleInputs/aoc_puzzle_input_2024_day8.txt").read()

frequency_dict = dict()
grid = [*map(lambda l: list(l), input.splitlines())]
rows, cols = len(grid), len(grid[0])

in_map = lambda y, x: 0 <= x < cols and 0 <= y < rows

for y in range(rows):
    for x in range(cols):
        if grid[y][x] != ".":
            frequency_dict[grid[y][x]] = frequency_dict.get(grid[y][x], []) + [(y, x)]

antinodes = set()
p2_antinodes = set()
for k, v in frequency_dict.items():
    for (ay, ax), (by, bx) in list(combinations(v, 2)):
        (diff_y, diff_x) = by - ay, bx - ax  # Delta
        p2_antinodes.add((ay, ax))
        p2_antinodes.add((by, bx))

        (cy, cx) = ((ay - diff_y), (ax - diff_x))
        if in_map(cy, cx):
            antinodes.add((cy, cx))
        while in_map(cy, cx):
            p2_antinodes.add((cy, cx))
            (cy, cx) = ((cy - diff_y), (cx - diff_x))

        (dy, dx) = ((by + diff_y), (bx + diff_x))
        if in_map(dy, dx):
            antinodes.add((dy, dx))
        while in_map(dy, dx):
            p2_antinodes.add((dy, dx))
            (dy, dx) = ((dy + diff_y), (dx + diff_x))

# Useful hack for viewing a multi-line string input that has been split to a list of lists of chars
# debug_grid = '\n'.join([''.join(l) for l in grid])

print(len(antinodes))
print(len(p2_antinodes))




