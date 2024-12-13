input = open("./puzzleInputs/aoc_puzzle_input_2024_day6.txt").read()

# Read grid via grid[y][x] as coordinates.
grid = [*map(list, input.splitlines())]

cursor = None
rows = len(grid)
for y in range(rows):
    cols = len(grid[y])
    for x in range(cols):
        if grid[y][x] == '^':
            cursor = (y, x)

initial_cursor = cursor

valid = lambda y, x: 0 <= x < cols and 0 <= y < rows

deltas = { # dY, dX
    '^': (-1, 0),
    '<': (0, -1),
    '>': (0, 1),
    'V': (1, 0)
}
next_pos = {
    '^': '>',
    '<': '^',
    '>': 'V',
    'V': '<'
}

positions_visited = set()
obstructions = set()
while True:
    #move to next position
    y, x = cursor
    arr = grid[y][x]
    dy, dx = deltas[arr]

    positions_visited.add((y, x))
    if valid(y + dy, x + dx):
        if grid[y + dy][x + dx] == '.':
            cursor = (y + dy, x + dx)
            grid[y][x], grid[y + dy][x + dx] = grid[y + dy][x + dx], grid[y][x]
        else:
            obstructions.add((y + dy, x + dx))
            grid[y][x] = next_pos[grid[y][x]]
    else:
        break
print(len(positions_visited))
assert len(positions_visited) == 5153

possible_obstructions = positions_visited.copy()
possible_obstructions.remove(initial_cursor)


valid_obstructions = set()

# Reset the cursor
grid[y][x] = '.'

for pos_obs in possible_obstructions:

    (poy, pox) = pos_obs
    obstructions_hit_from_positions = dict()
    cursor = initial_cursor
    (init_cursor_y, init_cursor_x) = initial_cursor

    grid[init_cursor_y][init_cursor_x] = '^'

    grid[poy][pox] = 'X'

    while True:

        y, x = cursor
        arr = grid[y][x]
        dy, dx = deltas[arr]

        if valid(y + dy, x + dx):
            if grid[y + dy][x + dx] == '.':
                cursor = (y + dy, x + dx)
                grid[y][x], grid[y + dy][x + dx] = grid[y + dy][x + dx], grid[y][x]
            else:
                # TODO Don't need to only detect loops from obstructions. If in a visited square in the same direction, is a loop
                if (y, x) in obstructions_hit_from_positions.get((y + dy, x + dx), []):
                    valid_obstructions.add(pos_obs)
                    break
                else:
                    obstructions_hit_from_positions[(y + dy, x + dx)] = obstructions_hit_from_positions.get((y + dy, x + dx), []) + [(y, x)]
                grid[y][x] = next_pos[grid[y][x]]
        else:
            break

    grid[poy][pox] = '.'
    grid[y][x] = '.'

print(len(valid_obstructions))
assert len(valid_obstructions) == 1711
