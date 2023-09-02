fn day3_p1(input: &str) -> u32 {
    let char_vec_vec: Vec<_> = input.lines()
        .map(|line| line.chars().collect::<Vec<_>>())
        .collect();
    let char_vec_vec = dbg!(char_vec_vec);
    println!("{}", char_vec_vec[0][3]);
    println!("{}", char_vec_vec[0][4]);

    // todo loop for number of rows in input
    // with each iteration, increase coordinates by 3 right, down 1
    return 1
}
fn day3_p2(input: &str) -> u32 {
    return 1
}

#[cfg(test)]
mod tests {

    use crate::{day3_p1, day3_p2};

    #[test]
    fn day3_p1_example_test() {
        let input = include_str!("../../inputs/day3_example.txt");
        let result = day3_p1(input);
        assert_eq!(result, 7);
    }

    #[test]
    #[ignore]
    fn day3_p1_test() {
        let input = include_str!("../../inputs/day3.txt");
        let result = day3_p1(input);
        assert_eq!(result, 0);
    }

    #[test]
    #[ignore]
    fn day3_p2_example_test() {
        let input = include_str!("../../inputs/day3_example.txt");
        let result = day3_p2(input);
        assert_eq!(result, 0);
    }

    #[test]
    #[ignore]
    fn day3_p2_test() {
        let input = include_str!("../../inputs/day3.txt");
        let result = day3_p2(input);
        assert_eq!(result, 0);
    }


}
