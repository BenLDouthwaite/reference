from unittest import TestCase

from leetcode.p345_reverse_vowels_of_string import reverse_vowels, reverse_vowels_optimised


class Test(TestCase):
    def test_reverse_vowels(self):
        self.assertEqual("AceCreIm", reverse_vowels("IceCreAm"))
        self.assertEqual("leotcede",reverse_vowels("leetcode"))

    def reverse_vowels_optimised(self):
        self.assertEqual("AceCreIm", reverse_vowels_optimised("IceCreAm"))
        self.assertEqual("leotcede", reverse_vowels_optimised("leetcode"))
