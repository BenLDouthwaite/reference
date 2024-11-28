from unittest import TestCase

from leetcode.p238_product_of_array_except_self import product_except_self, product_except_self_optimised


class Test(TestCase):
    def test_product_except_self(self):
        self.assertEqual([24,12,8,6], product_except_self([1,2,3,4]))
        self.assertEqual([0,0,9,0,0], product_except_self([-1,1,0,-3,3]))

    def test_product_except_self_optimised(self):
        self.assertEqual([24,12,8,6], product_except_self_optimised([1,2,3,4]))
        self.assertEqual([0,0,9,0,0], product_except_self_optimised([-1,1,0,-3,3]))