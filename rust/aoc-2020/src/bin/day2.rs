use std::str::FromStr;

#[derive(Debug)]
struct Pwd {
    min: usize,
    max: usize,
    letter: char,
    value: String,
}

#[derive(Debug, PartialEq, Eq)]
struct ParsePwdError;

impl FromStr for Pwd {
    type Err = ParsePwdError;
    fn from_str(s: &str) -> Result<Self, Self::Err> {
        let parts: Vec<&str> = s.split_whitespace().collect();
        let limits: Vec<usize> = parts[0]
            .split('-')
            .map(|i| i.parse::<usize>().unwrap())
            .collect::<Vec<_>>();

        let pwd = Pwd {
            min: limits[0],
            max: limits[1],
            letter: parts[1].chars().next().unwrap(),
            value: parts[2].to_string(),
        };
        Ok(pwd)
    }
}
fn day2_p1(input: &str) -> usize {
    return input
        .lines()
        .map(|l| l.parse::<Pwd>().unwrap())
        .filter(|pwd| {
            let match_count = pwd.value.chars().filter(|c| *c == pwd.letter).count();
            match_count >= pwd.min && match_count <= pwd.max
        })
        .count();
}

fn day2_p2(input: &str) -> usize {
    return input
        .lines()
        .map(|l| l.parse::<Pwd>().unwrap())
        .filter(|pwd| {
            (pwd.value.chars().nth(pwd.min - 1).unwrap() == pwd.letter)
                ^ (pwd.value.chars().nth(pwd.max - 1).unwrap() == pwd.letter)
        })
        .count();
}

#[cfg(test)]
mod tests {

    use crate::{day2_p1, day2_p2};

    #[test]
    fn day2_p1_example_test() {
        let input = include_str!("../../inputs/day2_example.txt");
        let result = day2_p1(input);
        assert_eq!(result, 2);
    }

    #[test]
    #[ignore]
    fn day2_p1_test() {
        let input = include_str!("../../inputs/day2.txt");
        let result = day2_p1(input);

        println!("Day 1. Part 1. Result : {}", result);
        assert_eq!(result, 645);
    }

    #[test]
    fn day2_p2_example_test() {
        let input = include_str!("../../inputs/day2_example.txt");
        let result = day2_p2(input);
        assert_eq!(result, 1);
    }

    #[test]
    #[ignore]
    fn day2_p2_test() {
        let input = include_str!("../../inputs/day2.txt");
        let result = day2_p2(input);

        println!("Day 1. Part 2. Result : {}", result);
        assert_eq!(result, 737);
    }
}
