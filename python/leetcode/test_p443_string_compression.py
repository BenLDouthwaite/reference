from unittest import TestCase

from leetcode.p443_string_compression import compress


class Test(TestCase):
    def test_compress(self):
        self.assertEqual(6, compress(["a","a","b","b","c","c","c"]))
        self.assertEqual(1, compress(["a"]))
        self.assertEqual(4, compress(["a","b","b","b","b","b","b","b","b","b","b","b","b"]))
