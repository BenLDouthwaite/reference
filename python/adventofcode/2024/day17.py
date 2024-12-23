input = """
Register A: 729
Register B: 0
Register C: 0

Program: 0,1,5,4,3,0
""".strip()
text_io_wrapper = open("./puzzleInputs/aoc_puzzle_input_2024_day17.txt")
input = text_io_wrapper.read()

registers, program = input.split("\n\n")

pull_value = lambda s: s.split(':')[1].strip()
A, B, C = [int(pull_value(r)) for r in registers.splitlines()]
program = [int(num) for num in pull_value(program).split(',')]
print(A, B, C)
print(program)

a, b, c = A, B, C

def operand_val(operand, a, b, c):
    match operand:
        case 0 | 1 | 2 | 3: return operand
        case 4: return a
        case 5: return b
        case 6: return c


def run(a, b, c, program):
    # global A, B, instruction_pointer, C
    instruction_pointer = 0
    result = []
    while instruction_pointer < len(program):
        instruction = program[instruction_pointer]
        literal_operand = program[instruction_pointer + 1]
        combo_operand = operand_val(literal_operand, a, b, c)

        print(f"I: {instruction} ptr {instruction_pointer}, lit: {literal_operand}, combo: {combo_operand}. A={a},B={b},C={c}")

        match instruction:
            case 0:
                a = a // pow(2, combo_operand)
            case 1:
                b = b ^ literal_operand
            case 2:
                b = combo_operand % 8
            case 3:
                if a != 0:
                    print(f"Jump! set inst ptr  {instruction_pointer} to {literal_operand}\n")
                    instruction_pointer = literal_operand
                    continue
            case 4:
                # test_b = b
                # test_c = c
                b = b ^ c
                # print(f"Before: {test_b}, {test_c}. After {b}, {c}")
            case 5:
                res = combo_operand % 8
                result.append(res)
                print(f"Add to res: {res}. a={a},b={b},c={c}")
            case 6:
                b = a // pow(2, combo_operand)
            case 7:
                c = a // pow(2, combo_operand)

        instruction_pointer += 2
    return ','.join([str(n) for n in result])


print(run(A, B, C, program))

# for i in range(10): # Lets not brute force yet
#     res_string = run(i, B, C, program)
#     # print(f"Iteration {i}: {res_string}")
#     if res_string.startswith('3,6,3,7,0,7'):
#         print(f"Iteration {i} has first correct chars")

# print("\nHALT")

