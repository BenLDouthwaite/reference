import unittest

from sandbox.codingchallenges.mymath import increment


class TestMyMath(unittest.TestCase):

    def test_increment(self):
        x = 10
        expected_result = 11

        result = increment(x)

        self.assertEqual(result, expected_result)

    def test_increment_2(self):
        x = 0
        expected_result = 1

        result = increment(x)

        self.assertEqual(result, expected_result)
