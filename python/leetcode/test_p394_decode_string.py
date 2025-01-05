from unittest import TestCase

from leetcode.p394_decode_string import decode_string


class Test(TestCase):
    def test_decode_string(self):
        self.assertEqual("aaabcbc", decode_string("3[a]2[bc]"))
        self.assertEqual("accaccacc", decode_string("3[a2[c]]"))
        self.assertEqual("abcabccdcdcdef", decode_string("2[abc]3[cd]ef"))