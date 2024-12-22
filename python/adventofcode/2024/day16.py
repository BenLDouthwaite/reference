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

cost_dir_deltas = {-1j:'<', -1:'^', 1j:'>', 1:'v'}

def check_score():
    end_pos_paths = distances[end_pos]
    path_tiles = set()
    for score, path in end_pos_paths:
        path_tiles.update(path)

    total_tiles_in_shortest_paths = len(path_tiles) + 1
    print(f"Total tiles involved in all shortest paths :{total_tiles_in_shortest_paths}")
    assert total_tiles_in_shortest_paths == 527


def adjacent_path_positions(position):
    return [position + d for d in [1,-1,1j,-1j] if grid[position + d] in '.ETO*']

def print_grid(grid_dict, row_count, col_count):
    y_index_width = 4

    for y in range(row_count):
        y_index = str(y).ljust(y_index_width)
        print(y_index, end ='')
        for x in range(col_count):
            position = y + x * 1j
            value = grid_dict.get(position, '.')
            print(value, end='')
        print()

# Keep implementation as a refernce.
# print_grid(grid, row_count, col_count)
direction = '>'
start_pos = [position for position, char in grid.items() if char == 'S'][0]
end_pos = [position for position, char in grid.items() if char == 'E'][0]
distances = dict()

came_from = dict()

def get_score(cur_pos, direction, next_pos, score):
    needed_direction = cost_dir_deltas[(next_pos - cur_pos)]
    if direction == needed_direction:
        return score + 1, needed_direction
    else:
        return score + 1001, needed_direction

search_queue = PriorityQueue()
counter = itertools.count() # Only exists to be a tie-breaker if score is the same for 2 paths
search_queue.put((0, next(counter), (start_pos, direction, [])))

visited = dict()
def visit(search_val):

    score, count, (position, direction, path) = search_val
    previous_distances = distances.get(position, [])

    if len(previous_distances) > 0:
        prev_score, _ = previous_distances[0]
        if score > prev_score:
            if (score - prev_score) > 1001:
                return


    for neighbour in adjacent_path_positions(position):
        if neighbour in path: continue

        neighbour_score, new_direction = get_score(position, direction, neighbour, score)
        neighbour_score_list = [(neighbour_score, path + [position])]
        if neighbour not in distances:
            distances[neighbour] = neighbour_score_list
        else:
            (old_neighbour_score, _) = distances[neighbour][0]
            if neighbour_score < old_neighbour_score:  # Better path found - replace
                distances[neighbour] = neighbour_score_list
            elif neighbour_score == old_neighbour_score:  # Equal, keep both
                distances[neighbour] = distances[neighbour] + neighbour_score_list

        search_queue.put((neighbour_score, (next(counter)), (neighbour, new_direction, path + [position])))

while not search_queue.empty():
    visit(search_queue.get())

check_score()
