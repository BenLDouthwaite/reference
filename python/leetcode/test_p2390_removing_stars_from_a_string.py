from unittest import TestCase

from leetcode.p2390_removing_stars_from_a_string import remove_stars


class Test(TestCase):
    def test_remove_stars(self):
        self.assertEqual(remove_stars("leet**cod*e"), "lecoe")
        self.assertEqual(remove_stars("erase*****"), "")
