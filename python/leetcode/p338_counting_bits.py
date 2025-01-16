from typing import List


class Solution:
    def countBits(self, n: int) -> List[int]:

        res = [0,1]
        for i in range(n.bit_length()):
            res += [n + 1 for n in res]
        return res[:n + 1]
