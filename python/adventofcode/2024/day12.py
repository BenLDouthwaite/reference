text_io_wrapper = open("./puzzleInputs/aoc_day12_example1.txt")
text_io_wrapper = open("./puzzleInputs/aoc_day12_example2.txt")
# text_io_wrapper = open("./puzzleInputs/aoc_day12_example3.txt")
# text_io_wrapper = open("./puzzleInputs/aoc_day12_example4.txt")

text_io_wrapper = open("./puzzleInputs/aoc_puzzle_input_2024_day12.txt")

# Indexed via a + bj. Where 'a' is the row, and 'b' is the column
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

p1_total_price = 0
p2_total_price = 0

for char in regions:
    char_regions = regions[char]
    for char_region in char_regions:
        all_barriers_needed = []
        for pos in char_region:
            possible_perpendicular_barriers = [(pos, pos + n) for n in [1, -1, 1j, -1j]]
            barriers_needed = [(start, end) for (start, end) in possible_perpendicular_barriers if end not in char_region]
            all_barriers_needed += barriers_needed
        p1_total_price += len(char_region) * len(all_barriers_needed)

        # For p2, analyse the barriers to see
        corners = []
        diagonal_offset = 0
        for pos in char_region:

            # AB
            # CD Imagine ever corner is the centre of 4 points.
            offset_deltas = [
                [-1 + -1j, -1, -1j, 0], # Top left
                [-1, -1 + 1j, 0, 1j], # Top right
                [-1j, 0, 1 + -1j, 1], # Bottom Left
                [0, 1j, 1, 1 + 1j] # Bottom right
            ]

            for offset_delta in offset_deltas:
                char_positions = [pos + n for n in offset_delta]
                if set(char_positions) not in corners:

                    A_pos, B_pos, C_pos, D_pos = char_positions
                    chars = [grid[pos] for pos in char_positions if pos in grid and pos in char_region]
                    if len(chars) == 1 or len(chars) == 3:
                        corners.append(set(char_positions))
                    elif len(chars) == 2:
                        A, B, C, D = grid.get(A_pos, '-'), grid.get(B_pos, '-'), grid.get(C_pos, '-'), grid.get(D_pos, '-')
                        debug = ''.join([A, B, '\n', C, D])
                        if A == D == char or B == C == char:
                            corners.append(set(char_positions))
                            diagonal_offset += 1 # Test hack - is this enough to catch from both sides?

        price = len(char_region) * (len(corners) + diagonal_offset)
        p2_total_price += price

print(p1_total_price)
print(p2_total_price)
assert p1_total_price == 1518548
assert p2_total_price == 909564