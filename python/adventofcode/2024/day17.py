input = open("./puzzleInputs/aoc_puzzle_input_2024_day17.txt").read()

registers, program = input.split("\n\n")

pull_value = lambda s: s.split(':')[1].strip()
A, B, C = [int(pull_value(r)) for r in registers.splitlines()]
program = [int(num) for num in pull_value(program).split(',')]

a, b, c = A, B, C

def operand_val(operand, a, b, c):
    match operand:
        case 0 | 1 | 2 | 3: return operand
        case 4: return a
        case 5: return b
        case 6: return c

def run(a):
    b, c, instruction_pointer, result = 0, 0, 0, []
    while instruction_pointer < len(program):
        literal_operand = program[instruction_pointer + 1]
        combo_operand = operand_val(literal_operand, a, b, c)

        match program[instruction_pointer]:
            case 0: a = a // pow(2, combo_operand)
            case 1: b = b ^ literal_operand
            case 2: b = combo_operand % 8
            case 3:
                if a != 0:
                    instruction_pointer = literal_operand
                    continue
            case 4: b = b ^ c
            case 5: result.append(combo_operand % 8)
            case 6: b = a // pow(2, combo_operand)
            case 7: c = a // pow(2, combo_operand)

        instruction_pointer += 2
    return ','.join([str(n) for n in result])

part_1 = run(A)
print(f"p1: {part_1}")
assert part_1 == '3,6,3,7,0,7,0,3,0'

def possible_a(a_vals):
    a_values_to_test = []
    for a in a_vals:
        for i in range(8):
            a_values_to_test.append((a * 8) + i)
    return a_values_to_test

reversed_program = program.copy()
reversed_program.reverse()

a_values = [3]
target_rem = [3]

for p in range(1, len(program)):
    possible_a_values = possible_a(a_values)
    a_values.clear()
    for a in possible_a_values:
        final_3_digits = (a % 8)
        b5 = (final_3_digits ^ 5 ^ 6 ^ (a >> (final_3_digits ^ 5)))
        if b5 % 8 == reversed_program[p] and a // 8 in target_rem:
            a_values.append(a)

    target_rem = a_values.copy()

print(f"p2: {a_values[0]}")
assert a_values[0] == 136904920099226