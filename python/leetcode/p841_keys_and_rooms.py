from collections import defaultdict
from typing import List


class Solution:
    def can_visit_all_rooms(self, rooms: List[List[int]]) -> bool:
        keyring = defaultdict(bool)
        keyring[0] = True

        def visit(room: int):
            keys = rooms[room]
            for key in keys:
                if not keyring[key]:
                    keyring[key] = True
                    visit(key)
        visit(0)
        return len(keyring) == len(rooms)