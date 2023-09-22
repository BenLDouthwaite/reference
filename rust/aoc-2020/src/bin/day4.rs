fn day4_p1(input: &str) -> u32 {
    return 1;
}
fn day4_p2(input: &str) -> u32 {
    return 1;
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
        assert_eq!(result, 0);
    }

    #[test]
    #[ignore]
    fn day4_p2_example_test() {
        let input = include_str!("../../inputs/day4_example.txt");
        let result = day4_p2(input);
        assert_eq!(result, 0);
    }

    #[test]
    #[ignore]
    fn day4_p2_test() {
        let input = include_str!("../../inputs/day4.txt");
        let result = day4_p2(input);
        assert_eq!(result, 0);
    }
}
