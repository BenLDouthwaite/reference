def wire_mapping(line):
    values = line.split(",")
    coordinates = set()
    loc = [0, 0]
    for val in values:
        direction = val[0]
        axes_diff = {"L": (0, -1), "R": (0, 1), "U": (1, 1), "D": (1, -1)}[direction]
        for i in range(0, int(val[1:])):
            loc[axes_diff[0]] += axes_diff[1]
            coordinates.add((loc[0], loc[1]))
    return coordinates

def min_distance(wire1, wire2):
    return min([abs(x) + abs(y) for (x, y) in get_overlapping_coords(wire1, wire2)])

def get_overlapping_coords(wire1, wire2):
    return wire_mapping(wire1).intersection(wire_mapping(wire2))

def min_steps(wire1, wire2):
    overlappingCoords = list(get_overlapping_coords(wire1, wire2))
    steps = [total_steps(coords, wire1, wire2) for coords in overlappingCoords]
    return min(steps)

def total_steps(coords, wire1, wire2):
    (target_x, target_y) = coords
    wire1_steps = get_steps_to_coords(wire1, target_x, target_y)
    wire2_steps = get_steps_to_coords(wire2, target_x, target_y)
    return wire1_steps + wire2_steps

def get_steps_to_coords(wire, targetX, targetY):
    values = wire.split(",")
    loc = [0, 0]
    steps = 0
    for val in values:
        direction = val[0]
        delta = {"L": (0, -1), "R": (0, 1), "U": (1, 1), "D": (1, -1)}
        axes_diff = delta[direction]
        for i in range(0, int(val[1:])):
            loc[axes_diff[0]] += axes_diff[1]
            steps += 1
            if loc[0] == targetX and loc[1] == targetY:
                return steps

with open("./puzzleInputs/aoc_puzzle_input_day3.txt") as file:
    lines = [line.rstrip() for line in file]

    print(min_distance("R8,U5,L5,D3", "U7,R6,D4,L4")) # 6
    print(min_distance("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83")) # 159
    print(min_distance("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")) # 135
    print(min_distance(lines[0], lines[1])) # 293

    print(min_steps("R8,U5,L5,D3", "U7,R6,D4,L4")) # 30
    print(min_steps("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83")) # 610
    print(min_steps("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")) # 410
    print(min_steps(lines[0], lines[1])) # 27306