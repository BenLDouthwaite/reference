from bisect import bisect_left
from math import ceil
from typing import List


class Solution:
    def successful_pairs(self, spells: List[int], potions: List[int], success: int) -> List[int]:

        potions.sort()

        n = len(spells)
        m = len(potions)

        spell_lb = None
        spell_ub = None
        pairs = [0] * n
        for i in range(n):
        # for spell in spells:
            spell = spells[i]

            if spell_ub and spell >= spell_ub:
                pairs[i] = m
                continue

            # Verify if we've already seen a spell larger than this fail entirely
            if spell_lb and spell <= spell_lb:
                # Leave score as 0
                continue

            # if the first potion works - they all will
            if spell * potions[0] >= success:
                pairs[i] = m
                if not spell_ub or spell > spell_ub:
                    spell_ub = spell
                continue

            # if the last potion doesn't work - none of them will
            if spell * potions[-1] < success:
                # Leave score as 0
                if not spell_lb or spell < spell_lb:
                    spell_lb = spell
                continue

            # Use bif bin-search to find where a new value would go = the cutoff point
            target = ceil(success / spell)
            j = bisect_left(potions, target)
            pairs[i] = m - j

            # # Manually implement Binary Search - But can use built in function above instead
            # # Binary search to find mid point where 'i' fails, but 'i + 1' succeeds
            # lb, mid, ub = 0, m // 2, m - 1
            # while True:
            #     mid = (lb + ub) // 2
            #
            #     score = spell * potions[mid]
            #     next_score = spell * potions[mid + 1]
            #     if score < success <= next_score:
            #         spell_total += (m - mid)  - 1
            #         break
            #     elif score >= success:
            #         ub = mid
            #     elif next_score < success:
            #         lb = mid
            #
            # pairs.append(spell_total)
        return pairs