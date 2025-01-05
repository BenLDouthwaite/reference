from typing import List


def asteroid_collision(asteroids: List[int]) -> List[int]:

    results = []

    for a in asteroids:

        while len(results) > 0 and results[-1] > 0 > a:

            if -a == results[-1]:
                results.pop()
                break

            if -a > results[-1]:  # right wins
                results.pop()
                continue # Keep checking left until resolved before moving to next 'a'
            break
        else:
            results.append(a)
    return results
