from typing import List


class Solution:
    # An actually good answer from here: https://leetcode.com/problems/number-of-provinces/solutions/1134925/dfs-explanation-comments/?envType=study-plan-v2&envId=leetcode-75
    def find_circle_num(self, isConnected: List[List[int]]) -> int:
        N = len(isConnected)
        visited = set()

        def dfs(cityI):
            # cityIConnections is a row in isConnected, which contains the city i's connections
            cityIConnections = isConnected[cityI]
            visited.add(cityI)  # Add cityI to seen, so we won't dfs it again (because we called it just now!)
            # We want to take all 1's in cityIConnections to put together into a province
            for cityJ in range(N):
                # Check cityJ's connections first before finishing cityI's connections
                if (cityJ not in visited) and (cityIConnections[cityJ] == 1) and (cityI != cityJ):
                    dfs(cityJ)
            # We're done searching cityI's direct connections
            return

        numProvinces = 0
        for cityI in range(N):
            # Each entire dfs recursion set is going to be one province
            if cityI not in visited:
                dfs(cityI)
                numProvinces += 1
        return numProvinces

    # My initial attempt, thought it could be solved easily with a map tracking it, but quickly grew too complex
    # Hacked into a state it works, but is a bad solution.
    def find_circle_num_naive_first_pass(self, isConnected: List[List[int]]) -> int:

        groups = [] # List of lists.
        n = len(isConnected)

        city_group_index_map = dict()
        for i in range(n):
            if i in city_group_index_map:
                # group already exists
                group_index = city_group_index_map[i]
                group = groups[group_index]
            else:
                # Need to make a new group
                group_index = len(groups)
                group = []
                groups.append(group)
            for j in range(i, n):
                if i == j:
                    print(i, j)
                    if not group:
                        group.append(i)
                        city_group_index_map[i] = group_index
                    continue
                ic = isConnected[i][j]
                if ic:

                    if j in city_group_index_map:
                        # Need to combine existing groups.

                        j_group_index = city_group_index_map[j]
                        j_group = groups[j_group_index]
                        if i in j_group:
                            print("I already in J group")
                            # group += j_group
                            print(group, j_group)
                        else:
                            print("I not in J group")
                            group += j_group

                            for val in j_group:
                                city_group_index_map[val] = group_index
                            groups[j_group_index] = None
                    else:
                        group += [j]
                        city_group_index_map[j] = group_index

            # Adding the group if needed as new
        return len([g for g in groups if g])