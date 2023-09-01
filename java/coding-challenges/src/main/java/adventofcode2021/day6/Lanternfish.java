package adventofcode2021.day6;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class Lanternfish {

    public static final int MAX_FISH_AGE = 8;
    public static final int AFTER_BIRTH_AGE = 6;

    public static void main(String[] args) {
        String input = "2,5,3,4,4,5,3,2,3,3,2,2,4,2,5,4,1,1,4,4,5,1,2,1,5,2,1,5,1,1,1,2,4,3,3,1,4,2,3,4,5,1,2,5,1,2,2,5,2,4,4,1,4,5,4,2,1,5,5,3,2,1,3,2,1,4,2,5,5,5,2,3,3,5,1,1,5,3,4,2,1,4,4,5,4,5,3,1,4,5,1,5,3,5,4,4,4,1,4,2,2,2,5,4,3,1,4,4,3,4,2,1,1,5,3,3,2,5,3,1,2,2,4,1,4,1,5,1,1,2,5,2,2,5,2,4,4,3,4,1,3,3,5,4,5,4,5,5,5,5,5,4,4,5,3,4,3,3,1,1,5,2,4,5,5,1,5,2,4,5,4,2,4,4,4,2,2,2,2,2,3,5,3,1,1,2,1,1,5,1,4,3,4,2,5,3,4,4,3,5,5,5,4,1,3,4,4,2,2,1,4,1,2,1,2,1,5,5,3,4,1,3,2,1,4,5,1,5,5,1,2,3,4,2,1,4,1,4,2,3,3,2,4,1,4,1,4,4,1,5,3,1,5,2,1,1,2,3,3,2,4,1,2,1,5,1,1,2,1,2,1,2,4,5,3,5,5,1,3,4,1,1,3,3,2,2,4,3,1,1,2,4,1,1,1,5,4,2,4,3";

        long result1 = lanternfish(input, 80);
        System.out.println("Part 1: Result for puzzle input: " + result1);

        long result2 = lanternfish(input, 256);
        System.out.println("Part 2: Result for puzzle input: " + result2);
    }

    public static long lanternfish(String input, int days) {
        Map<Integer, Long> startingFishValueCountMap = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::valueOf)
                .collect(Collectors.toList()).stream()
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()));
        Map<Integer, Long> fishCountMap = new HashMap<>();
        for (int i = 0; i <= MAX_FISH_AGE; i++) {
            fishCountMap.put(i, startingFishValueCountMap.getOrDefault(i, 0L));
        }
        for (int day = 1; day <= days; day++) {
            long zeroFishCount = fishCountMap.get(0);
            for (int i = 0; i < MAX_FISH_AGE; i++) {
                fishCountMap.put(i, fishCountMap.get(i + 1));
            }
            fishCountMap.put(AFTER_BIRTH_AGE, fishCountMap.get(AFTER_BIRTH_AGE) + zeroFishCount);
            fishCountMap.put(MAX_FISH_AGE, zeroFishCount);
            log.info("After Day. Count = {}", fishCountMap);
        }
        return fishCountMap.values().stream().mapToLong(a -> a).sum();
    }

}
