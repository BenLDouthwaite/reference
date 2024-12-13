import numpy

input = ("""
190: 10 19
3267: 81 40 27
83: 17 5
156: 15 6
7290: 6 8 6 15
161011: 16 10 13
192: 17 8 14
21037: 9 7 18 13
292: 11 6 16 20
""".strip())

input = open("./puzzleInputs/aoc_puzzle_input_2024_day7.txt").read()

raw_lines = [*map(lambda l : tuple(l.split(':')), input.splitlines())]
lines = [(int(ans), [*map(int, vals.strip().split())]) for ans, vals in raw_lines]

# p1
p1_total = 0
for ans, vals in lines:
    exp = len(vals) - 1
    combinations = pow(2, exp)
    for i in range(combinations):
        bin_i = bin(i)[2:].zfill(exp)
        commands = bin_i.replace('0', '+').replace('1', '*')
        prev_results = [] # Will want to cache the calculation results at some point

        for c_i in range(len(commands)):
            com = commands[c_i]
            prev = (prev_results[-1] if prev_results else vals[c_i])
            cur = vals[c_i + 1]
            if com == '+':
                step_res = prev + cur
            elif com == '*':
                step_res = prev * cur
            prev_results.append(step_res)
        if prev_results[-1] == ans:
            print(f"Answer {ans} found! using {commands} on {vals}")
            p1_total += ans
            break

print(p1_total)
# assert p1_total == 882304362421

# p2 - Repeat with 3 possible options
test = 8
val = numpy.base_repr(test, base=3)
print(val)

p2_total = 0
# TODO Return to this and try with DFS of operations - This works but is really slow
for ans, vals in lines:
    exp = len(vals) - 1
    combinations = pow(3, exp)
    for i in range(combinations):
        bin_i = numpy.base_repr(i, base=3).zfill(exp)
        commands = bin_i.replace('0', '+').replace('1', '*').replace('2', '|')
        # print(bin_i, commands)

        prev_results = []  # Will want to cache the calculation results at some point

        for c_i in range(len(commands)):
            com = commands[c_i]
            prev = (prev_results[-1] if prev_results else vals[c_i])
            cur = vals[c_i + 1]
            if com == '+':
                step_res = prev + cur
            elif com == '*':
                step_res = prev * cur
            elif com == '|':
                step_res = int(str(prev) + str(cur))
            prev_results.append(step_res)
            if step_res > ans: # Minor optimisation
                break
        if prev_results[-1] == ans:
            print(f"Answer {ans} found! using {commands} on {vals}")
            p2_total += ans
            break
print(p2_total)