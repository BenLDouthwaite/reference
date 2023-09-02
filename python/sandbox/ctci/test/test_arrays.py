import unittest

from sandbox.ctci.arrays import unique_characters


class TestArraysMethods(unittest.TestCase):

    def test_is_unique(self):
        self.assertTrue(unique_characters('cat'))
        self.assertTrue(unique_characters('abcdef'))
        self.assertTrue(unique_characters('a'))
        self.assertFalse(unique_characters('aa'))
        self.assertFalse(unique_characters('bee'))


if __name__ == '__main__':
    unittest.main()
