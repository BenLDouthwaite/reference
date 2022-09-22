import unittest

from sandbox.codingchallenges.mymath import increment

class TestMyMath(unittest.TestCase):

	def test_increment(self):
		input = 10
		expectedResult = 11

		result = increment(input)

		self.assertEqual(result, expectedResult)

	def test_increment_2(self):
		input = 0
		expectedResult = 1

		result = increment(input)

		self.assertEqual(result, expectedResult)