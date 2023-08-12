use std::fs::read_to_string;
use itertools::Itertools;

// Core Library Only - More idiomatic, using references from Reddit
fn day1_p1(input: &str) -> u32 {
         let pair = input 
            .lines()
            .map(|line| line.parse::<u32>().unwrap())
            .combinations(2)
            .find(|x| x.iter().sum::<u32>() == 2020);
    return pair.map(|pair| pair.iter().product()).unwrap(); 
}

fn day1_p2(input: &str) -> u32 {
        let pair = input 
            .lines()
            .map(|line| line.parse::<u32>().unwrap())
            .combinations(3)
            .find(|x| x.iter().sum::<u32>() == 2020);
    return pair.map(|pair| pair.iter().product()).unwrap(); 

}

// Naive Solution
fn day1_naive(filename: &str) -> u32 {
    let lines = read_lines(filename);

    for l in &lines {
        for l2 in &lines {
            if l.parse::<u32>().unwrap() + l2 .parse::<u32>().unwrap()== 2020 {
                return l.parse::<u32>().unwrap() * l2 .parse::<u32>().unwrap()
            }
        }
    }
    return 1;
}

fn day1_part2(filename: &str) -> u32 {
    let lines = read_lines(filename);

    for l in &lines {
        dbg!(l);
        for l2 in &lines {
            for l3 in &lines {
                if l.parse::<u32>().unwrap() + l2 .parse::<u32>().unwrap() + l3.parse::<u32>().unwrap() == 2020 {
                    return l.parse::<u32>().unwrap() * l2 .parse::<u32>().unwrap()  * l3 .parse::<u32>().unwrap() 
                }
            }
        }
    }
    return 123;
}

fn read_lines(filename: &str) -> Vec<String> {
    
    let mut result = Vec::new();

    for line in read_to_string(filename).unwrap().lines() {
        result.push(line.to_string())
    }

    result
}

#[cfg(test)]
mod tests {

    use crate::{day1_p1, day1_p2, day1_part2, day1_naive};


    #[test]
    fn day1_p1_example_test() {
        let input = include_str!("../../inputs/day1_example.txt");
        let result = day1_p1(input);
        assert_eq!(result, 514579); 
    }

    #[test]
    fn day1_p1_test() {
        let input = include_str!("../../inputs/day1.txt");
        let result = day1_p1(input);
        assert_eq!(result, 969024);
    }

    #[test]
    fn day1_p2_example_test() {
        let input = include_str!("../../inputs/day1_example.txt");
        let result = day1_p2(input);
        assert_eq!(result, 241861950);
    }

    #[test]
    fn day1_p2_test() {
        let input = include_str!("../../inputs/day1.txt");
        let result = day1_p2(input);
        assert_eq!(result, 230057040);
    }  
    
    #[test]
    fn day1_p1_example_naive_test() {
        let result = day1_naive("./inputs/day1_example.txt");
        assert_eq!(result, 514579);
    }

    #[test]
    fn day1_p1_naive_test() {
        let result = day1_naive("./inputs/day1.txt");
        assert_eq!(result, 969024);
    }


    #[test]
    fn day1_p2_naive_example_test() {
        let result = day1_part2("./inputs/day1_example.txt");
        assert_eq!(result, 241861950);
    }

    #[test]
    fn day1_p2_naive_test() {
        let result = day1_part2("./inputs/day1.txt");
        println!("Day 1. Part 2. Result : {}", result);
        assert_eq!(result, 230057040);
    }  
}
