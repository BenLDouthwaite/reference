nums = [*map(int, open("./puzzleInputs/aoc_puzzle_input_2024_day11.txt").read().split())]
def step(value):
    if value == 0: return [1]
    elif len(str(value)) % 2 == 0: return [int(str(value)[:len(str(value)) // 2]), int(str(value)[len(str(value)) // 2:])]
    else: return [value * 2024]

for steps in 25, 75:
    number_count = dict()
    for n in nums:
        number_count[n] = number_count.get(n, 0) + 1
    for i in range(steps):
        for num, count in [(k, v) for k, v in number_count.items() if v > 0]:
            for n in step(num):
                number_count[n] = number_count.get(n, 0) + count
            number_count[num] = number_count.get(num, 0) - count
    print(sum(number_count.values()))
    if steps == 25: assert sum(number_count.values()) == 202019
    if steps == 75: assert sum(number_count.values()) == 239321955280205