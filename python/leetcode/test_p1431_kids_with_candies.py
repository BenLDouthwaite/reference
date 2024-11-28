from unittest import TestCase

from leetcode.p1432_kids_with_candies import kids_with_candies


class Test(TestCase):
    def test_kids_with_candies(self):
        self.assertEqual(kids_with_candies([2,3,5,1,3], 3), [True,True,True,False,True] )
        self.assertEqual(kids_with_candies([4,2,1,1,2], 1), [True,False,False,False,False] )
        self.assertEqual(kids_with_candies([12,1,12], 10), [True,False,True] )
