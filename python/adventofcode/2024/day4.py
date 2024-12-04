def check_mas_cross(lines, x, y, pos_deltas):
    top_m = lines[y + pos_deltas[0][1]][x + pos_deltas[0][0]]
    top_s = lines[y + pos_deltas[1][1]][x + pos_deltas[1][0]]
    mid_a = lines[y + pos_deltas[2][1]][x + pos_deltas[2][0]]
    btm_m = lines[y + pos_deltas[3][1]][x + pos_deltas[3][0]]
    btm_s = lines[y + pos_deltas[4][1]][x + pos_deltas[4][0]]
    ans = ''.join([top_m, top_s, mid_a, btm_m, btm_s])
    return ans == 'MSAMS'

with open("./puzzleInputs/aoc_puzzle_input_2024_day4.txt") as file:
    lines = file.read().splitlines()

    line_count = len(lines)
    line_length = len(lines[0])

    up_valid = lambda y : y >= 3
    right_valid = lambda x : x < line_length - 3
    down_valid = lambda y : y < line_count - 3
    left_valid = lambda x : x >= 3

    # P1
    def check_xmas(lines, x, y, d_x, d_y) -> bool:
        return ''.join([(lines[y][x]),  # X
                        (lines[y + d_y][x + d_x]),  # M
                        (lines[y + 2 * d_y][x + 2 * d_x]),  # A
                        (lines[y + 3 * d_y][x + 3 * d_x])  # S
                        ]) == 'XMAS'
    p1_count = 0
    xmas_deltas = [
        (lambda x, y: up_valid(y), 0, -1),  # Up, only if on the 3rd row. Check up 3
        (lambda x, y: up_valid(y) and right_valid(x), 1, -1),  # Up-right,
        (lambda x, y: right_valid(x), 1, 0),  # Right,
        (lambda x, y: down_valid(y) and right_valid(x), 1, 1),  # Down-Right,
        (lambda x, y: down_valid(y), 0, 1),  # Down,
        (lambda x, y: down_valid(y) and left_valid(x), -1, 1),  # Down-Left,
        (lambda x, y: left_valid(x), -1, 0),  # Left,
        (lambda x, y: up_valid(y) and left_valid(x), -1, -1),  # Up-Left,
    ]
    for y in range(line_length):
        for x in range(line_count):
            if lines[y][x] == 'X':
                for check, d_x, d_y in xmas_deltas:
                    if check(x, y) and check_xmas(lines, x, y, d_x, d_y):
                        p1_count += 1
    print(p1_count)


    mas_deltas = [ # Second argument is offset to find M, S, A, M, S
        # Right and down
        # root -> M.S 0.1
        #         .S. .2.
        #         M.S 3.4
        (lambda x, y: x < line_length - 2 and y < line_count - 2, [(0,0), (2, 0), (1,1), (0,2), (2,2)]), # Growing right and down.

        # Down and left
        #     3.0 M.M <- root
        #     .2. .A.
        #     4.1 S.S
        (lambda x, y: x >= 2 and y < line_count - 2, [(0,0), (0, 2), (-1,1), (-2,0), (-2,2)]), # Growing down and left

        # Left and Up
        #     4.3 S.M
        #     .2. .A.
        #     1.0 S.M <- root
        (lambda x, y: x >= 2 and y >= 2, [(0,0), (-2, 0), (-1,-1), (0,-2), (-2,-2)]), # Growing left and up

        # Up and right
        #         S.S  1.4
        #         .A.  .2.
        # root -> M.M  0.3
        (lambda x, y: x < line_length - 2 and y >= 2, [(0,0), (0, -2), (1,-1), (2,0), (2,-2)]) # Growing up and right
    ]
    # P2
    p2_count = 0
    for y in range(line_length):
        for x in range(line_count):
            val = lines[y][x]
            if val == 'M':
                for check, pos_deltas in mas_deltas:
                    if check(x, y) and check_mas_cross(lines, x, y, pos_deltas):
                        p2_count += 1
    print(p2_count)