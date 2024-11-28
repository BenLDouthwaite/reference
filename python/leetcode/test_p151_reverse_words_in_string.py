from unittest import TestCase

from leetcode.p151_reverse_words_in_string import reverse_words_initial, reverse_words


class Test(TestCase):
    def test_reverse_words(self):
        self.assertEqual("blue is sky the", reverse_words("the sky is blue"))
        self.assertEqual("world hello", reverse_words("  hello world  "))
        self.assertEqual("example good a", reverse_words("a good   example"))
