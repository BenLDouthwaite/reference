fn day3_p1(input: &str) -> u32 {
    let grid = parse_grid(input);
    return count_trees(&grid, 3, 1);
}
fn day3_p2(input: &str) -> u32 {
    let grid = parse_grid(input);
    let a = count_trees(&grid, 1, 1);
    let b = count_trees(&grid, 3, 1);
    let c = count_trees(&grid, 5, 1);
    let d = count_trees(&grid, 7, 1);
    let e = count_trees(&grid, 1, 2);

    return a * b * c * d * e;
}
fn parse_grid(input: &str) -> Vec<Vec<char>> {
    let grid: Vec<_> = input
        .lines()
        .map(|line| line.chars().collect::<Vec<_>>())
        .collect();
    return grid;
}
fn count_trees(grid: &Vec<Vec<char>>, dx: usize, dy: usize) -> u32 {
    let mut x = 0;
    let mut y = 0;
    let lines = grid.len();
    let line_len = grid[0].len();
    let mut tree_counter = 0;

    while y + dy < lines {
        y = y + dy;
        x = (x + dx) % line_len;
        let value = grid[y][x];
        if value == '#' {
            tree_counter = tree_counter + 1;
        }
    }

    return tree_counter;
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
    fn day3_p1_test() {
        let input = include_str!("../../inputs/day3.txt");
        let result = day3_p1(input);
        assert_eq!(result, 203);
    }

    #[test]
    fn day3_p2_example_test() {
        let input = include_str!("../../inputs/day3_example.txt");
        let result = day3_p2(input);
        assert_eq!(result, 336);
    }

    #[test]
    fn day3_p2_test() {
        let input = include_str!("../../inputs/day3.txt");
        let result = day3_p2(input);
        assert_eq!(result, 3316272960);
    }
}
