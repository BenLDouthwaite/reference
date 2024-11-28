def find_path(graph, start, end, path=[]):
    path = path + [start]
    if start == end:
        return path
    if start not in graph:
        return None
    for node in graph[start]:
        if node not in path:
            newpath = find_path(graph, node, end, path)
            if newpath: return newpath
    return None


def read_unidirectional_graph(lines):
    graph = {}
    for line in lines:
        command = line.split(')')
        key, value = command
        if value in graph.keys():
            graph[value] = graph[value] + [key]
        else:
            graph[value] = key
    return graph

def read_bidirectional_graph(lines):
    graph = {}
    for line in lines:
        command = line.split(')')
        key, value = command
        if value in graph.keys():
            graph[value] = graph[value] + [key]
        else:
            graph[value] = [key]

        if key in graph.keys():
            graph[key] = graph[key] + [value]
        else:
            graph[key] = [value]
    return graph

with open("./puzzleInputs/aoc_puzzle_input_day6.txt") as file:
# with open("./puzzleInputs/aoc_day6_example.txt") as file:
    lines = [line.rstrip() for line in file]
    unidirectional_graph = read_unidirectional_graph(lines)

    print(unidirectional_graph)

    total_orbits = 0
    for key in unidirectional_graph.keys():
        orbits = 1
        dest = unidirectional_graph[key]
        while dest != 'COM':
            orbits += 1
            dest = unidirectional_graph[dest]
        total_orbits += orbits

    print(total_orbits)

    bd_graph = read_bidirectional_graph(lines)
    path = find_path(bd_graph, 'YOU', 'SAN')
    print(len(path) - 3) # -2 for the start and end nodes, another -1 for the jumps rather than nodes

