from unittest import TestCase

from leetcode.p649_dota2_senate import predict_party_victory


class Test(TestCase):
    def test_predict_party_victory(self):
        self.assertEqual("Radiant", predict_party_victory("RD"))
        self.assertEqual("Dire", predict_party_victory("DR"))
        self.assertEqual("Dire", predict_party_victory("RDD"))
