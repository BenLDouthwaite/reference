text_io_wrapper = open("./puzzleInputs/aoc_puzzle_input_2024_day13.txt")

# Keep as a reference - too elaborate to actually invoke
def solve_linear_equations_debug(A, B, T):
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

def solve_linear_equations(A, B, T):
    total_b = (A[1] * B[0] * -1) + A[0] * B[1]
    total_constant = A[0] * T[1] - A[1] * T[0]
    b = total_constant / total_b
    a = (T[0] - (B[0] * b)) / A[0]
    return a, b

inputs = text_io_wrapper.read().split('\n\n')
parse_input = lambda line: [*map(lambda v: int(v.strip()[2:]), line.split(','))]

# p1
total_cost = {0:0, 1:0}
for part in [0, 1]:
    offset = (10000000000000 * part)
    for input in inputs:
        A, B, (T1, T2) = [*map(lambda s: parse_input(s.split(':')[1].strip()), input.splitlines())]
        a, b = solve_linear_equations(A, B, (T1 + offset, T2 + offset))
        if a % 1 == 0 and b % 1 == 0:
            total_cost[part] += 3 * int(a) + int(b)

print(total_cost[0])
assert total_cost[0] == 33427
print(total_cost[1])
assert total_cost[1] == 91649162972270