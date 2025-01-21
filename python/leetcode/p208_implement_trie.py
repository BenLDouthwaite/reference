class Trie:

    def __init__(self):
        self.root = dict()

    def insert(self, word: str) -> None:
        node = self.root
        for c in word:
            node[c] = node.get(c, dict())
            node = node[c]
        node['.'] = True

    def search(self, word: str) -> bool:
        node = self.root
        for c in word:
            if c not in node:
                return False
            node[c] = node.get(c, dict())
            node = node[c]
        return '.' in node

    def startsWith(self, prefix: str) -> bool:
        node = self.root
        for c in prefix:
            if c not in node:
                return False
            node[c] = node.get(c, dict())
            node = node[c]
        return True

obj = Trie()
obj.insert('apple')
obj.insert('applause')

obj.search('apple')
obj.search('apples')
param_2 = obj.search('apple')
param_3 = obj.startsWith('app')
