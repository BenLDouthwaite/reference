from typing import List

class Solution:

    # Optimisations taken from examples - not figured out myself
    def min_reorder(self, n: int, connections: List[List[int]]) -> int:

        visited = set()
        visited.add(0)
        count = 0
        while len(visited) < n:
            check = []
            for path in connections:
                if path[1] in visited:
                    visited.add(path[0])
                elif path[0] in visited:
                    visited.add(path[1])
                    count += 1
                else:
                    check.append(path)
            connections = check[::-1]
        return count

    def min_reorder_path_weighting(self, n: int, connections: List[List[int]]) -> int:

        paths = dict()
        for (start, end) in connections:
            paths[start] = paths.get(start, []) + [(end, 1)]
            paths[end] = paths.get(end, []) + [(start, 0)]

        def dfs(val: int, parent: int) -> int:
            node_paths = paths.get(val, [])
            swaps = 0
            for (node, path_weight) in node_paths:
                if node != parent:
                    swaps += path_weight + dfs(node, val)
            return swaps

        return dfs(0, parent=-1)




    def min_reorder_original(self, n: int, connections: List[List[int]]) -> int:

        nodes = set(n for n in range(n))
        paths = dict()
        reverse_paths = dict()
        for (a, b) in connections:
            paths[a] = paths.get(a, []) + [b]
            reverse_paths[b] = reverse_paths.get(b, []) + [a]
        print(paths)
        print(reverse_paths)

        visited = set()

        can_reach_zero = set()
        can_reach_zero.add(0) # bit weird, testing

        def dfs(val: int):
            if val == 0:
                return
            node_paths = paths.get(val, [])
            visited.add(val)
            print(f"Visited {val}, connections = {node_paths}")
            for connection in node_paths:
                if connection == 0:
                    print(f"Able to reach 0 from {val}")
                    can_reach_zero.add(val)
                elif connection in can_reach_zero:
                    can_reach_zero.add(val)
                elif connection not in visited:
                    dfs(connection)

        for key in paths.keys():
            print(f"Test dfs for {key}")
            dfs(key)

        cannot_reach_zero = nodes - can_reach_zero
        print(cannot_reach_zero)

        path_swaps = 0


        while can_reach_zero != nodes:
            cannot_reach_zero = nodes - can_reach_zero
            print(f"After path swaps : {path_swaps}, Can reach zero : {can_reach_zero}, cannot reach zero = {cannot_reach_zero}. Test swaps")
            for val in cannot_reach_zero:
                if val in can_reach_zero:
                    continue

                print(f"Found that {val} cannot reach 0")
                val_rev_paths = reverse_paths.get(val, [])
                for val_rev_path in val_rev_paths:
                    if val in can_reach_zero:
                        continue
                    print(f"Test if {val_rev_path} can be reversed")
                    if val_rev_path in can_reach_zero:
                        print(f"Val rev path {val_rev_path} -> {val} can be inverted, so that {val} can reach 0")

                        # TODO How to simplify this 'swap'
                        paths[val] = paths.get(val, []) + [val_rev_path]
                        paths[val_rev_path].remove(val)

                        reverse_paths[val_rev_path] = reverse_paths.get(val_rev_path, []) + [val]
                        reverse_paths[val].remove(val_rev_path)

                        print(paths.get(val, []), val_rev_paths)

                        can_reach_zero.add(val)
                        for n in reverse_paths.get(val, []):
                            if n not in can_reach_zero:
                                can_reach_zero.add(n)

                        path_swaps += 1
                print(paths.get(val, []), val_rev_paths)

            visited = set()
            cannot_reach_zero = nodes - can_reach_zero
            for n in cannot_reach_zero:
                print(f"After updates, {n} cannot reach 0, test again to verify")
                dfs(n)

        return path_swaps
