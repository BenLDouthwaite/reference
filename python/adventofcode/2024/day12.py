text_io_wrapper = open("./puzzleInputs/aoc_day12_example1.txt")
# text_io_wrapper = open("./puzzleInputs/aoc_day12_example2.txt")

# text_io_wrapper = open("./puzzleInputs/aoc_puzzle_input_2024_day12.txt")
grid = dict()
for row_index, line in enumerate(text_io_wrapper):
    for column_index, character in enumerate(line.strip()):
        grid[row_index + column_index * 1j] = character

def search(pos, seen, region_pos):
    val = grid[pos]
    # TODO Where is best to place the validation?
    if pos not in grid:
        return
    if pos in seen:
        return
    seen.add(pos)
    adjacent_positions = [pos + n for n in [1, -1, 1j, -1j]]
    valid_adjacent_positions = [adj for adj in adjacent_positions if adj in grid]
    unseen_valid_adjacent_positions = [adj for adj in valid_adjacent_positions if adj not in seen]

    same_val_adj = [n for n in unseen_valid_adjacent_positions if grid[n] == val]

    for adj in same_val_adj:
        region_pos.add(adj)
        search(adj, seen, region_pos)

regions = dict()

for pos, character in grid.items():

    char_regions = regions.get(character, [])
    if any(pos in region for region in char_regions):
        continue # Don't trigger a search if it's already been found in a region
    region_positions = {pos}
    search(pos, set(), region_positions)
    regions[character] = char_regions + [region_positions]

total_price = 0
for char in regions:
    char_regions = regions[char]
    for char_region in char_regions:
        total_barriers_needed = 0
        for pos in char_region:
            adjacent_positions = [pos + n for n in [1, -1, 1j, -1j]]
            barriers_not_needed = [n for n in adjacent_positions if n in char_region]
            total_barriers_needed += 4 - len(barriers_not_needed)
        total_price += len(char_region) * total_barriers_needed
print(total_price)
# assert total_price == 1518548


# p2 todo - check boundary number of contiguous barriers
a_region_1 = regions['A'][0]
print(a_region_1)