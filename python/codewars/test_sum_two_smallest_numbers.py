from unittest import TestCase

from codewars.sum_two_smallest_numbers import sum_two_smallest_numbers


class Test(TestCase):
    def test_sum_two_smallest_numbers(self):
        self.assertEqual(sum_two_smallest_numbers([5, 8, 12, 18, 22]), 13)
        self.assertEqual(sum_two_smallest_numbers([7, 15, 12, 18, 22]), 19)
        self.assertEqual(sum_two_smallest_numbers([25, 42, 12, 18, 22]), 30)
