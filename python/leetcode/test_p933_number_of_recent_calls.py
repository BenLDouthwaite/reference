from unittest import TestCase
from p933_number_of_recent_calls import RecentCounter

class TestRecentCounter(TestCase):
    def test_ping(self):
        recent_counter = RecentCounter()
        self.assertEqual([1, 2, 3, 4, 5], [recent_counter.ping(i) for i in [1178, 1483, 1563, 4054, 4152]])
