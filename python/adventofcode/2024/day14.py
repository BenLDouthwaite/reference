import math

text_io_wrapper = open("./puzzleInputs/aoc_puzzle_input_2024_day14.txt")

input = text_io_wrapper.read()

def print_grid(grid):
    for row in grid:
        for col in row:
            print(col, end='')
        print()

row_count = 103
col_count = 101

lines = [n.split() for n in input.splitlines()]
start_deltas = [[a[2:].split(','), b[2:].split(',')] for (a, b) in lines]
start_deltas = [((int(s_x), int(s_y)), (int(d_x), int(d_y))) for ((s_x, s_y), (d_x, d_y)) in start_deltas]

steps = 100

robot_positions = [((s_x + d_x * steps) % col_count, (s_y + d_y * steps) % row_count) for ((s_x, s_y), (d_x, d_y)) in start_deltas]

vertical_centre = col_count // 2
horizontal_centre = row_count // 2

is_top = lambda y: 0 <= y < horizontal_centre
is_bottom = lambda y: horizontal_centre < y < row_count
is_left = lambda x: 0 <= x < vertical_centre
is_right = lambda x : vertical_centre < x < col_count

quadrant_count = {'tl':0, 'tr': 0, 'bl':0, 'br':0}

for (x, y) in robot_positions:
    if is_top(y) and is_left(x): quadrant_count['tl'] += 1
    if is_top(y) and is_right(x): quadrant_count['tr'] += 1
    if is_bottom(y) and is_left(x): quadrant_count['bl'] += 1
    if is_bottom(y) and is_right(x): quadrant_count['br'] += 1

p1_score = math.prod(quadrant_count.values())
print(p1_score)

printed = 0
for i in range(10000):

    robot_positions = [((s_x + d_x * i) % col_count, (s_y + d_y * i) % row_count) for ((s_x, s_y), (d_x, d_y)) in start_deltas]

    # Horizontal line of decent length, worked well enough when trawling output
    offsets = [
        (0,0),
        (1,0),
        (-1,0),
        (2,0),
        (-2,0),
        (-3,0),
        (3,0),
        (-4,0),
        (4,0),
    ]

    if i % 1000 == 0:
        print(f"Testing reached {i}")

    for (x, y) in robot_positions:
        if all([(x + d_x, y + d_y) in robot_positions for (d_x, d_y) in offsets]):
            print(f"Maybe tree? Steps = {i}")
            printed += 1
            grid = []
            for _ in range(row_count):
                grid.append(['.'] * col_count)
            for (x, y) in robot_positions:
                grid[y][x] = 'R'
            print_grid(grid)