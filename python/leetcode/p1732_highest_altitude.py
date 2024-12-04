from typing import List


def largest_altitude(gain: List[int]) -> int:
    altitude = 0
    max_altitude = 0
    for n in gain:
        altitude += n
        if max_altitude < altitude:
            max_altitude = altitude
    return max_altitude
