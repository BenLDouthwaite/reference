from typing import List


class Solution:
    def calc_equation(self, equations: List[List[str]], values: List[float], queries: List[List[str]]) -> List[float]:

        adj_map = dict()
        val_map = dict()
        for i in range(len(values)):
            (x, y) = equations[i]
            value = values[i]
            val_map[x] = val_map.get(x, []) + [(value, y)]
            adj_map[x] = adj_map.get(x, []) + [y]

            val_map[y] = val_map.get(y, []) + [(1 / value, x)]
            adj_map[y] = adj_map.get(y, []) + [x]


        def dfs(node: str, target: str, path: List[str]) -> List[str]:
            adj_nodes = adj_map[node]
            visited.add(node)
            if target in adj_nodes:
                return path + [target]
            else:
                for adj_node in adj_nodes:
                    if adj_node not in visited:
                        path_to_target = dfs(adj_node, target, path + [adj_node])
                        if path_to_target:
                            return path_to_target

        nodes = adj_map.keys()

        output = []
        for query in queries:
            (x, y) = query
            if x not in nodes or y not in nodes:
                output.append(-1.00000)
                continue

            if x == y:
                output.append(1.00000)
                continue

            if y in adj_map[x]:
                output.append([factor for (factor, node) in val_map[x] if node == y][0])
                continue

            visited = set()
            actual_path_to_target = dfs(x, y, [x])

            if not actual_path_to_target:
                output.append(-1.00000)
                continue

            factor = 1
            for i in range(len(actual_path_to_target) -1):
                a, b = actual_path_to_target[i], actual_path_to_target[i + 1]
                factor *= [factor for (factor, node) in val_map[a] if node == b][0]
            output.append(factor)
        return output