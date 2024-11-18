from unittest import TestCase

from leetcode.merge_alternately import merge_alternately

class TestSolution(TestCase):
    def test_merge_alternately(self):
        self.assertEqual(merge_alternately("abc", "pqr"), "apbqcr")
        self.assertEqual(merge_alternately("ab", "pqrs"), "apbqrs")
        self.assertEqual(merge_alternately("abcd", "pq"), "apbqcd")
