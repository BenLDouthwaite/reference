from unittest import TestCase

from leetcode.p1268_search_suggestions_system import Solution


class TestSolution(TestCase):
    def test_suggested_products(self):
        solution = Solution()
        self.assertEqual([
            ["mobile","moneypot","monitor"],
            ["mobile","moneypot","monitor"],
            ["mouse","mousepad"],
            ["mouse","mousepad"],
            ["mouse","mousepad"]
        ], solution.suggested_products(
            products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
        ))
