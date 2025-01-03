from unittest import TestCase

from leetcode.p1207_unique_number_of_differences import unique_occurrences


class Test(TestCase):
    def test_unique_occurrences(self):
        self.assertEqual(unique_occurrences([1,2,2,1,1,3]), True)
        self.assertEqual(unique_occurrences([1,2]), False)
