import unittest

from mymath import increment

class TestMyMath(unittest.TestCase):

	def test_increment(self):
		input = 10
		expectedResult = 11

		result = increment(input)

		self.assertEqual(result, expectedResult);

if __name__ == '__main__':
    unittest.main()