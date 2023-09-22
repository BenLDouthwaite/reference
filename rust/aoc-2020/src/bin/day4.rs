use regex::Regex;
use std::collections::HashMap;

fn day4_p1(input: &str) -> u32 {
    return valid_passport_fields_iter(input).count() as u32;
}

fn valid_passport_fields_iter(input: &str) -> impl Iterator<Item = HashMap<&str, &str>> {
    return input
        .split("\n\n")
        .map(|passport| passport.split_whitespace())
        .map(|pair_list| {
            let hash_map: HashMap<&str, &str> = pair_list
                .map(|pair| pair.split_at(pair.find(":").unwrap()))
                .map(|(key, val)| (key, &val[1..]))
                .filter(|(key, _)| key != &"cid")
                .collect();
            let hash_map = dbg!(hash_map);
            return hash_map;
        })
        .filter(|map| map.keys().len() == 7)
        .into_iter();
}
fn day4_p2(input: &str) -> u32 {
    let valid_passports = valid_passport_fields_iter(input)
        .filter(|map| {
            return map.into_iter().all(|(&key, val)| match key {
                "byr" => (1920..=2002).contains(&val.parse().unwrap_or(0)),
                "iyr" => (2010..=2020).contains(&val.parse().unwrap_or(0)),
                "eyr" => (2020..=2030).contains(&val.parse().unwrap_or(0)),
                "hgt" => {
                    (val.ends_with("cm")
                        && (150..=193).contains(&val[..(val.len() - 2)].parse().unwrap()))
                        || (val.ends_with("in")
                            && (59..=76).contains(&val[..(val.len() - 2)].parse().unwrap()))
                }
                "hcl" => Regex::new(r"#[0-9a-f]{6}").unwrap().is_match(val),
                "ecl" => ["amb", "blu", "brn", "gry", "grn", "hzl", "oth"].contains(val),
                "pid" => Regex::new(r"^[0-9]{9}$").unwrap().is_match(val),
                _ => unreachable!(),
            });
        })
        .count();

    return valid_passports as u32;
}
#[cfg(test)]
mod tests {

    use crate::{day4_p1, day4_p2};

    #[test]
    fn day4_p1_example_test() {
        let input = include_str!("../../inputs/day4_example.txt");
        let result = day4_p1(input);
        assert_eq!(result, 2);
    }

    #[test]
    fn day4_p1_test() {
        let input = include_str!("../../inputs/day4.txt");
        let result = day4_p1(input);
        assert_eq!(result, 237);
    }

    #[test]
    fn day4_p2_example_test() {
        let input = include_str!("../../inputs/day4_example.txt");
        let result = day4_p2(input);
        assert_eq!(result, 2);
    }

    #[test]
    fn day4_p2_test() {
        let input = include_str!("../../inputs/day4.txt");
        let result = day4_p2(input);
        assert_eq!(result, 172);
    }
}
