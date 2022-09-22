import unittest
from arrays import uniquecharacters

class TestArraysMethods(unittest.TestCase):

    def test_isunique(self):
        self.assertTrue(uniquecharacters('cat'))
        self.assertTrue(uniquecharacters('abcdef'))
        self.assertTrue(uniquecharacters('a'))
        self.assertFalse(uniquecharacters('aa'))
        self.assertFalse(uniquecharacters('bee'))

if __name__ == '__main__':
    unittest.main()
