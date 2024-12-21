
# text_io_wrapper = open("./puzzleInputs/aoc_day15_example3.txt")
# text_io_wrapper = open("./puzzleInputs/aoc_day15_example2.txt")
# text_io_wrapper = open("./puzzleInputs/aoc_day15_debug.txt")
text_io_wrapper = open("./puzzleInputs/aoc_puzzle_input_2024_day15.txt")
grid_text, commands = text_io_wrapper.read().split('\n\n')

grid_lines = grid_text.splitlines()
row_count = len(grid_lines)
col_count = len(grid_lines[0])

commands = commands.replace('\n', '')

# Indexed via a + bj. Where 'a' is the row, and 'b' is the column
grid = {row_index + column_index * 1j: character for row_index, line in enumerate(grid_lines)
        for column_index, character in enumerate(line.strip())}

rob_pos = [position for position, char in grid.items() if char == '@'][0]
def print_grid(grid_dict, row_count, col_count):
    for y in range(row_count):
        for x in range(col_count): print(grid_dict.get(y + x * 1j, '.'), end='')
        print()

print_grid(grid, row_count, col_count)
print(f"Robot starts at {rob_pos}")

deltas = {'<':-1j, '^':-1, '>':1j, 'v':1}

for command in commands:
    pos_diff = deltas[command]
    next_space = rob_pos + pos_diff
    if grid[next_space] == '.':
        grid[rob_pos], grid[next_space] = grid[next_space], grid[rob_pos]
        rob_pos = next_space
    elif grid[next_space] == 'O':
        while grid[next_space] == 'O':
            next_space = next_space + pos_diff
        if grid[next_space] == '.':
            grid[rob_pos], grid[rob_pos + pos_diff], grid[next_space] = '.', '@', 'O'
            rob_pos = rob_pos + pos_diff

print_grid(grid, row_count, col_count)
coordinates_sum = sum([int(100 * position.real + position.imag) for (position, value) in grid.items() if value == 'O'])
print(coordinates_sum)
assert coordinates_sum == 1552879


# Part 2
p2_grid_lines = [l.replace('#', '##') .replace('O', '[]').replace('.', '..').replace('@', '@.') for l in grid_lines]
grid = {row_index + column_index * 1j: character for row_index, line in enumerate(p2_grid_lines)
        for column_index, character in enumerate(line.strip())}

row_count = len(p2_grid_lines)
col_count = len(p2_grid_lines[0])
print_grid(grid, row_count, col_count)

box_offset = {'[': 1j, ']': -1j}

rob_pos = [position for position, char in grid.items() if char == '@'][0]
for command in commands:
    pos_diff = deltas[command]
    next_space = rob_pos + pos_diff
    next_space_char = grid[next_space]
    if grid[next_space] == '.':
        grid[rob_pos], grid[next_space] = grid[next_space], grid[rob_pos]
        rob_pos = next_space
    elif grid[next_space] in ['[', ']']:
        if command in ['>','<']:
            while grid[next_space] == next_space_char:
                next_space = next_space + pos_diff * 2
            if grid[next_space] == '.':
                row = rob_pos.real
                col_start = int(next_space.imag)
                col_end = int(rob_pos.imag)
                if command == '<':
                    for y in range(col_start, col_end):
                        position_to_move = complex(row, y)
                        grid[position_to_move], grid[position_to_move - pos_diff] = grid[position_to_move - pos_diff], '.'
                else: # command == '>'
                    for y in range(col_start, col_end, -1):
                        position_to_move = complex(row, y)
                        grid[position_to_move], grid[position_to_move - pos_diff] = grid[position_to_move - pos_diff], '.'
                grid[rob_pos] = '.'
                rob_pos = rob_pos + pos_diff

        else: # command in '^' or 'v'
            box_positions = [next_space, next_space + box_offset[next_space_char]]

            rows_to_move = []
            while True:

                # Once we have all positions of boxes, check the next line
                positions_being_checked = [pos + pos_diff for pos in box_positions]

                rows_to_move.append(list(set(box_positions)))

                box_positions = positions_being_checked

                # If all the next row is clear, move all the boxes up
                if all([val == '.' for val in [grid[pos] for pos in positions_being_checked]]):
                    for i in range(len(rows_to_move) - 1, -1, -1):
                        for pos in rows_to_move[i]:
                            grid[pos + pos_diff], grid[pos] = grid[pos], '.'
                    grid[rob_pos + pos_diff], grid[rob_pos] = '@', '.'
                    rob_pos = rob_pos + pos_diff
                    break

                # If any of the boxes are blocked at any point, don't move any of them
                if any([val == '#' for val in [grid[pos] for pos in positions_being_checked]]):
                    break

                # Didn't hit a wall, and wasn't all clear, meaning we hit at least some boxes. Check if they're clear
                box_positions = [pos for pos in positions_being_checked if grid[pos] in ['[', ']']]
                for box_pos in box_positions.copy():
                    box_positions.append(box_pos + box_offset[grid[box_pos]])


print_grid(grid, row_count, col_count)
coordinates_sum = sum([int(100 * position.real + position.imag) for (position, value) in grid.items() if value == '['])
print(coordinates_sum)
assert coordinates_sum == 1561175