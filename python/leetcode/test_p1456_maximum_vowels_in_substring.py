from unittest import TestCase

from leetcode.p1456_maximum_vowels_in_substring import max_vowels


class Test(TestCase):
    def test_max_vowels(self):
        self.assertEqual(3, max_vowels(s = "abciiidef", k = 3))
        self.assertEqual(2, max_vowels(s = "aeiou", k = 2))
        self.assertEqual(2, max_vowels(s = "leetcode", k = 3))
