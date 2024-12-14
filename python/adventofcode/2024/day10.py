def debugPrintGrid(grid):

    debug_grid = '\n'.join([''.join([*map(str, l)]) for l in grid])
    print(debug_grid)

def walk(pos):
    y, x = pos

    if pos in position_end_positions:
        return position_end_positions[pos]
    position_value = grid[y][x]

    if position_value == 9:
        position_end_positions[pos] = [pos]
        return [pos]

    deltas = [(y - 1, x), (y + 1, x), (y, x - 1), (y, x + 1)]
    next = [(ny, nx) for (ny, nx) in deltas if in_map(ny, nx) and grid[ny][nx] == position_value + 1]

    trail_end_locations = set()
    for n in next:
        end_positions = walk(n)
        trail_end_locations.update(end_positions)

    position_end_positions[pos] = trail_end_locations
    return trail_end_locations


def walk_p2(pos):
    y, x = pos
    position_value = grid[y][x]
    if position_value == 9: return 1
    deltas = [(y - 1, x), (y + 1, x), (y, x - 1), (y, x + 1)]
    next = [(ny, nx) for (ny, nx) in deltas if in_map(ny, nx) and grid[ny][nx] == position_value + 1]
    return sum([walk_p2(n) for n in next])

input = open("./puzzleInputs/aoc_puzzle_input_2024_day10.txt").read()
grid = [*map(lambda l: [*map(int, l)], input.splitlines())]
rows, cols = len(grid), len(grid[0])

in_map = lambda y, x: 0 <= x < cols and 0 <= y < rows

trailheads = list()
for y in range(rows):
    for x in range(cols):
        if grid[y][x] == 0:
            trailheads.append((y, x))

# p1
# Unnecessary optimisation
position_end_positions = dict()
total_score = 0
for trailhead in trailheads:
    end_locations = walk(trailhead)
    total_score += len(end_locations)
print(total_score)
assert total_score == 644

# p2
p2_total_score = 0
for trailhead in trailheads:
    paths = walk_p2(trailhead)
    p2_total_score += paths
print(p2_total_score)
assert p2_total_score == 1366


### Alternative implementation:
# Taken from user 4HbQ on reddit, local as a reference to debug and understand.
# https://old.reddit.com/r/adventofcode/comments/1hau6hl/2024_day_10_solutions/m1bkqc1/

grid = {i+j*1j: int(c) for i,r in enumerate(open("./puzzleInputs/aoc_puzzle_input_2024_day10.txt"))
        for j,c in enumerate(r.strip())}

def search(pos, seen, height=0):
    if pos in grid and grid[pos] == height:
        if height < 9 or part==1 and pos in seen:
            return sum(search(pos+n, seen, height+1) for n in [1,-1,1j,-1j])
        seen.add(pos)
        return 1
    return 0

for part in 1, 2:
    print(sum(search(pos, set()) for pos in grid if grid[pos]==0))
