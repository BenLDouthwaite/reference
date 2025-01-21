from collections import deque
from typing import List


class Trie:

    def __init__(self):
        self.root = dict()

    def insert(self, word: str) -> None:
        node = self.root
        for c in word:
            node[c] = node.get(c, dict())
            node = node[c]
        node['.'] = True

    def search_n(self, search_prefix: str, n: int) -> List[str]:
        res = []
        node = self.root
        for c in search_prefix:
            node = node.get(c, dict())

        def dfs(node, prefix):
            if len(res) == n:
                return
            keys = list(node.keys())
            keys.sort()
            for key in keys:
                if key == '.':
                    res.append(prefix)
                    if len(res) == n:
                        return
                else:
                    dfs(node[key], prefix + key)
        dfs(node, search_prefix)
        return (node, res)


class Solution:
    def suggested_products(self, products: List[str], searchWord: str) -> List[List[str]]:
        trie = Trie()
        for word in products:
            trie.insert(word)

        search_results = []
        for i in range(len(searchWord)):
            search_prefix = searchWord[:i + 1]
            res = trie.search_n(search_prefix, 3)

            # TODO As eact search will use a branch of the trie on each pass - could cache that
            search_results.append(res)
        return search_results