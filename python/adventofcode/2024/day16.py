import itertools
from queue import PriorityQueue

# text_io_wrapper = open("./puzzleInputs/aoc_day16_debug.txt")
# text_io_wrapper = open("./puzzleInputs/aoc_day16_debug2.txt")
# text_io_wrapper = open("./puzzleInputs/aoc_day16_example1.txt")
# text_io_wrapper = open("./puzzleInputs/aoc_day16_example2.txt")
text_io_wrapper = open("./puzzleInputs/aoc_puzzle_input_2024_day16.txt")
grid_lines = text_io_wrapper.read().splitlines()
row_count = len(grid_lines)
col_count = len(grid_lines[0])

# Indexed via a + bj. Where 'a' is the row, and 'b' is the column
grid = {row_index + column_index * 1j: character for row_index, line in enumerate(grid_lines)
        for column_index, character in enumerate(line.strip())}

direction = 1j # Facing east
start_pos = [position for position, char in grid.items() if char == 'S'][0]
end_pos = [position for position, char in grid.items() if char == 'E'][0]
distances = dict()

search_queue = PriorityQueue()
counter = itertools.count() # Only exists to be a tie-breaker if score is the same for 2 paths
search_queue.put((0, next(counter), (start_pos, direction, [])))

tiles_on_path_to_end = []
best = 1e9

while not search_queue.empty():
    score, count, (position, direction, path) = search_queue.get()

    # If already above best total path to E, can't be equal shortest
    if score > best: continue

    # if score > best_score_to_E:
    if grid[position] == 'E' and score <= best:
        tiles_on_path_to_end += path
        best = score
        continue

    # Hack to account for scores changing based on rotation
    if score > distances.get(position, 1e9) + 1000:
        continue

    # Check for neighbours moving forward 1 space, left 1 space or right 1 space. Don't go backwards
    for (neighbour, new_direction, cost) in [(position + direction * rotation, direction * rotation, cost) for rotation, cost in [(1, 1), (1j, 1001), (-1j, 1001)]
                                       if grid[position + direction * rotation] in '.E']:
        if neighbour in path: continue
        neighbour_score = score + cost
        if neighbour_score < distances.get(neighbour, 1e9):
            distances[neighbour] = neighbour_score
        search_queue.put((neighbour_score, (next(counter)), (neighbour, new_direction, path + [position])))

unique_tiles_on_path_to_end = len(set(tiles_on_path_to_end)) + 1 # + 1 to include the end
print(best, unique_tiles_on_path_to_end)
assert best == 102460
assert unique_tiles_on_path_to_end == 527

# Only for debugging
def print_grid(grid_dict, row_count, col_count):
    y_index_width = 4

    for y in range(row_count):
        y_index = str(y).ljust(y_index_width)
        print(y_index, end ='')
        for x in range(col_count):
            position = y + x * 1j
            value = grid_dict.search_val(position, '.')
            print(value, end='')
        print()