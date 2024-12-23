from Xlib.Xcursorfont import pirate

input = """
Register A: 729
Register B: 0
Register C: 0

Program: 0,1,5,4,3,0
""".strip()
# Target output = 2,4,1,5,7,5,1,6,0,3,4,6,5,5,3,0
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


def run(a, program):
    # global A, B, instruction_pointer, C
    b, c = 0, 0
    instruction_pointer = 0
    result = []
    while instruction_pointer < len(program):
        instruction = program[instruction_pointer]
        literal_operand = program[instruction_pointer + 1]
        combo_operand = operand_val(literal_operand, a, b, c)

        # print(f"I: {instruction}, lit: {literal_operand}, combo: {combo_operand}. A={a},B={b},C={c}")
        # print(f"I: {instruction}, A={a},B={b},C={c}. binA={bin(a)[2:]}, binB={bin(b)[2:]}, binC={bin(c)[2:]}")
        # print(f"I: {instruction}, A={a},B={b},C={c}.")
        # b, c = 0, 0
        # print(f"I: {instruction}, A={a},B={b},C={c}.")

        match instruction:
            case 0:
                a = a // pow(2, combo_operand)
            case 1:
                b = b ^ literal_operand
            case 2:
                b = combo_operand % 8
                # print(f"Set 'b' = {b} = 'a % 8' (a >> 3)")
            case 3:
                if a != 0:
                    # print(f"Jump! set inst ptr  {instruction_pointer} to {literal_operand}\n")
                    instruction_pointer = literal_operand
                    # Can reset b and c, will be set again after
                    b, c = 0,0
                    continue
            case 4:
                # test_b = b
                # test_c = c
                b = b ^ c
                # print(f"Before: {test_b}, {test_c}. After {b}, {c}")
            case 5:
                res = combo_operand % 8
                result.append(res)
                # print(f"Add to res: {res}. a={a},b={b},c={c}")
            case 6:
                b = a // pow(2, combo_operand)
            case 7:
                c = a // pow(2, combo_operand)
                c = c % 8 # We only care about last 8 bits?
                # print(f"Set 'c' = {c} = 'a // pow(2, {combo_operand})' = 'a >> {b}' (keeping only final 3 bits)")

        instruction_pointer += 2
    return ','.join([str(n) for n in result])


part_1 = run(A, program)
print(part_1)
assert part_1 == '3,6,3,7,0,7,0,3,0'

print(f"Debug p2\n=\n=\n=\n\n\n\n=")

vals = []
for target in program[-3:]:
    # Reformat the above to be in fewer terms
    for i in range(1, 100):
        a = i
        b2 = (a % 8) ^ 5 # max value of b2 is 7.
        b5 = (b2 ^ 6) ^ (a >> b2)
        if b5 % 8 == target:
            print(f"Looking for {target} result. a={a},b5 = {b5}")
            vals.append(i)
            # break

print("\n\n\n")

target = 0
# vals = []
# for i in range(0, 7):
#     a = i
#     b2 = (a % 8) ^ 5  # max value of b2 is 7.
#     l = (b2 ^ 6)
#     r = (a >> b2)
#     r = r % 8  # only care about last 3 digits - can only be 0 to 7
#     b5 = l ^ r
#     if b5 % 8 == target:
#         print(f"Looking for {target} result. a={a},b5 = {b5}. b2={b2}. l={l}, r={r}")
#         # vals.append(i)
#         # break
#     a = i
#     b2 = (a % 8) ^ 5  # max value of b2 is 7.
#     # l = (b2 ^ 6)
#     r = (a >> b2)
#     r = r % 8  # only care about last 3 digits - can only be 0 to 7
#     # b5 = b2 ^ 6 ^ r
#     # b5 = b2 ^ 6 ^ r
#     # if b5 % 8 == target:
#     xored_target = target ^ 6
#     end = (b2 ^ r) % 8
#     if end == xored_target:
#         print(f"** Looking for {target} result.. xored T = {xored_target} a={a},end={end}")
#         vals.append(i)
#         # break

combos = []


def debug(af3d, shifted_a):
    # global b2, c, b5, lower
    b1 = af3d
    b2 = b1 ^ 5
    # c = a // pow(2, b2)
    # c = a >> b2
    c = shifted_a
    b3 = b2 ^ 6
    # a2 = a // 8
    b4 = b3 ^ c
    b5 = b4 % 8
    if b5 % 8 == target:
        # print(f"Looking for {target} result. i={i}, j={j}")
        vals.append(af3d)
        # break

        higher = shifted_a

        lower = af3d
        combined = higher ^ lower  # test
        combos.append(combined)
        print(f"i={af3d}, j={shifted_a}. Combine: higher: {higher}, lower={lower}. combined={combined}. hb={bin(higher)}, lb={bin(lower)}. cb = {bin(combined)}")
        return True
    return False


def debug_rem(af3d, shifted_a, target_val, target_a_rem):
    # global b2, c, b5, lower
    b1 = af3d
    b2 = b1 ^ 5
    # c = a // pow(2, b2)
    # c = a >> b2
    c = shifted_a
    b3 = b2 ^ 6
    a2 = a // 8
    b4 = b3 ^ c
    b5 = b4 % 8
    if b5 % 8 == target_val and a2 in target_a_rem:
        return True
    return False

pos_a = []

pc = program.copy()
pc.reverse()

pos_a_7 = [6504642, 6504650, 6504682, 6504686, 6504698, 6506222, 8160169, 8160174]

a_values = [3]
target_rem = [3]

def get_pos_A_next_step(previous_a_values):
    a_values_to_test = []
    for n in previous_a_values:
        for i in range(8):
            a_values_to_test.append((n * 8) + i)
    print(a_values_to_test)
    return a_values_to_test

for p in range(1, len(program)):
    start = pow(8, p)
    end = pow(8, p + 1)
    print(f"Test in range: {start} - {end}")

    # for i in range(start, end):
    #     # print(i)
    #     pass
    pos_a.clear()

    pos_a_values = get_pos_A_next_step(a_values)
    print(pos_a_values)

    a_values.clear()

    for a in pos_a_values:
        b1 = (a % 8)
        i = b1
        j = a >> (b1 ^ 5)

        target = pc[p]
        # print(f"Testing pow {p}, a = {a}, i={i}, j={j}. Target = {target}. Target_rem = {target_rem}")

        if debug_rem(i, j, target, target_rem):
            print(f"Testing pow {p}, a = {a}, i={i}, j={j}. Target = {target}. Target_rem = {target_rem}")
            a_values.append(a)


    target_rem = a_values.copy()
    print(a_values)

for a in a_values:
    print(run(a, program))