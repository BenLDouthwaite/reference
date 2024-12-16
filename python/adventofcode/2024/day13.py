# A button = 3 token
# B button = 1 token
# Position above prize
# never more than 100 per prize.

text_io_wrapper = open("./puzzleInputs/aoc_puzzle_input_2024_day13.txt")

def solve_linear_equations(A, B, T):
    print(f"Equation 1. {A[0]}*A + {B[0]}*B = X = {T[0]}")
    print(f"Equation 2. {A[1]}*A + {B[1]}*B = Y = {T[1]}")
    print(f"Reduced 1. A = {T[0]}/{A[0]} - {B[0]}*B/{A[0]}")
    print(f"Reduced 2. A = {T[1]}/{A[1]} - {B[1]}*B/{A[1]}")
    print(f"Equivalence. {T[0]}/{A[0]} - {B[0]}*B/{A[0]} == {T[1]}/{A[1]} - {B[1]}*B/{A[1]}")
    print(f"Remove fractions: {A[1]}*({T[0]}-{B[0]}B) == {A[0]}*({T[1]}-{B[1]}B)")
    left_b = A[1] * B[0]
    right_b = A[0] * B[1]
    left_constant = A[1] * T[0]
    right_constant = A[0] * T[1]
    print(f"Expand Brackets: {left_constant}-{left_b}B) == {right_constant}-{right_b}B)")
    print(f"Move all B to left side: {left_constant}-{left_b}B+{right_b}B) == {right_constant}")
    total_b = (left_b * -1) + right_b
    print(f"Move all B to left side: {left_constant} + {total_b}B) == {right_constant}")
    print(f"Move constants to right side: {total_b}B == {right_constant} - {left_constant}")
    total_constant = right_constant - left_constant
    print(f"Move constants to right side: {total_b}B == {total_constant}")
    b = total_constant / total_b
    print(f"Value of B: {total_constant} / {total_b} == {b}")
    print(f"A == {T[0]}/{A[0]} - {B[0]}*B/{A[0]}")
    print(f"A == {T[0]}/{A[0]} - {B[0] * b}/{A[0]}")
    print(f"{A[0]}A == {T[0]} - {B[0] * b}")
    print(f"{A[0]}A == {T[0] - (B[0] * b)}")
    a = (T[0] - (B[0] * b)) / A[0]
    print(f"A == {a}")
    return a, b

# test_input = """
# Button A: X+94, Y+34
# Button B: X+22, Y+67
# Prize: X=8400, Y=5400
# """.strip()

# test_input = """
# Button A: X+26, Y+66
# Button B: X+67, Y+21
# Prize: X=10000000012748, Y=10000000012176
# """.strip()

# print(test_input)

inputs = text_io_wrapper.read().split('\n\n')
print(inputs)

parse_input = lambda line: [*map(lambda v: int(v.strip()[2:]), line.split(','))]

total_cost = 0

# p1

for input in inputs:
    A, B, T = [*map(lambda s: parse_input(s.split(':')[1].strip()), input.splitlines())]
    print(A, B, T)

    a, b = solve_linear_equations(A, B, T)

    print(a, b)

    if a % 1 == 0 and b % 1  == 0:
        a_cost = 3 * int(a)
        b_cost = int(b)
        cost = a_cost + b_cost
        print(f"Token cost = 3 * a ({a_cost}) + 1 * b ({b_cost}) = {cost}")
        total_cost += cost
    else:
        print(f"No exact match for {A, B, T}")
print(total_cost)

#p2
p2_total_cost = 0
for input in inputs:

    A, B, T = [*map(lambda s: parse_input(s.split(':')[1].strip()), input.splitlines())]
    print(A, B, T)
    T[0], T[1] = T[0] + 10000000000000, T[1] + 10000000000000
    a, b = solve_linear_equations(A, B, T)

    print(a, b)

    if a % 1 == 0 and b % 1  == 0:
        a_cost = 3 * int(a)
        b_cost = int(b)
        cost = a_cost + b_cost
        print(f"Token cost = 3 * a ({a_cost}) + 1 * b ({b_cost}) = {cost}")
        p2_total_cost += cost
    else:
        print(f"No exact match for {A, B, T}")
print(p2_total_cost)

