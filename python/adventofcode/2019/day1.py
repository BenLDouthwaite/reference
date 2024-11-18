
def get_total_fuel_amount(mass: int) -> int:
    fuel_for_mass = (mass // 3) - 2 # None of the inputs go negative here
    extra_fuel = get_fuel_weight(fuel_for_mass)
    return fuel_for_mass + extra_fuel

def get_fuel_weight(fuel: int) -> int:
    extra_fuel_weight = max((fuel // 3) - 2, 0)
    if extra_fuel_weight == 0:
        return 0
    return extra_fuel_weight + get_fuel_weight(extra_fuel_weight)

with open("./puzzleInputs/aoc_puzzle_input_day1.txt") as file:
    lines = [line.rstrip() for line in file]
    print(sum([get_fuel_weight(int(line)) for line in lines]))