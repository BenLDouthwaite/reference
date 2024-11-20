def execute_intcode_processing(values):
    i = 0
    while values[i] != 99:
        opcode = values[i]
        first_input_pos = values[i+1]
        second_input_pos = values[i+2]
        output_pos = values[i+3]

        if opcode == 1:
            values[output_pos] = values[first_input_pos] + values[second_input_pos]
        if opcode == 2:
            values[output_pos] = values[first_input_pos] * values[second_input_pos]
        i += 4

    return values[0]

with open("./puzzleInputs/aoc_puzzle_input_day2.txt") as file:
    values = [int(x) for x in file.read().split(",")]

    # P1
    p1_values = values.copy()
    p1_values[1] = 12
    p1_values[2] = 2
    print(execute_intcode_processing(p1_values))

    for i in range(0, 100):
        for j in range(0, 100):
            clean_values = values.copy()
            clean_values[1] = i
            clean_values[2] = j
            output = execute_intcode_processing(clean_values)
            if output == 19690720:
                print(f"Success, noun = {clean_values[1]}, verb = {clean_values[2]}")
                print(f"Output = {100 * i + j}")
                exit(1)

