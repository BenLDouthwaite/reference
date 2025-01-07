def predict_party_victory(senate: str) -> str:

    radiant_bans, dire_bans = 0, 0
    while True:
        remaining_senate = ''
        for party in senate:
            if party == 'R':
                if dire_bans == 0:
                    radiant_bans += 1
                    remaining_senate += party
                else:
                    dire_bans -= 1
            elif party == 'D':
                if radiant_bans == 0:
                    dire_bans += 1
                    remaining_senate += party
                else:
                    radiant_bans -= 1
        if all([c == 'R' for c in remaining_senate]):
            return 'Radiant'
        if all([c == 'D' for c in remaining_senate]):
            return 'Dire'
        senate = remaining_senate