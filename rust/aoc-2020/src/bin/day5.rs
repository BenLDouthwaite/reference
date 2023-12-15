
#[derive(Debug, PartialEq)]
struct BoardingPass {
    row: u32,
    column: u32,
    seat_id: u32
}
fn parse_boarding_pass(pass: &str) -> BoardingPass {

    let (row_str, column_str) = pass.split_at(7);

    let row_bin = row_str.chars().map(|c| match c {
        'F' => '0',
        'B' => '1',
        _ => unreachable!()
     }).collect::<String>();
    let row_int = isize::from_str_radix(&row_bin, 2).unwrap();

    let col_bin = column_str.chars().map(|c| match c {
        'R' => '1',
        'L' => '0',
        _ => unreachable!()
    }).collect::<String>();
    let col_int = isize::from_str_radix(&col_bin, 2).unwrap();

    let row = row_int as u32;
    let column = col_int as u32;
    return BoardingPass {
        row,
        column,
        seat_id: (row * 8) + column 
    };
}

fn day5_p1(input: &str) -> u32 {
    return input.lines()
        .map(|line| parse_boarding_pass(line))
        .map(|pass| pass.seat_id)
        .max().unwrap()
}

fn day5_p2(input: &str) -> u32 {
    let mut seat_ids: Vec<_> = input.lines()
        .map(|line| parse_boarding_pass(line))
        .map(|pass| pass.seat_id)
        .collect();
    seat_ids.sort();
    for (i, el) in seat_ids.iter().enumerate() {
        if seat_ids[i] + 1 != seat_ids[i + 1] {
            return seat_ids[i] + 1;
        }
    }
    return 1;
}
#[cfg(test)]
mod tests {

    use crate::{day5_p1, day5_p2, parse_boarding_pass, BoardingPass};

    #[test]
    fn parse_boarding_pass_e1() {
        let pass = "FBFBBFFRLR";
        let result = parse_boarding_pass(pass);
        let expected_boarding_pass = BoardingPass {
            row: 44,
            column: 5,
            seat_id: 357 
        };
        assert_eq!(result, expected_boarding_pass);
    }

    #[test]
    fn parse_boarding_pass_e2() {
        let pass = "BFFFBBFRRR";
        let result = parse_boarding_pass(pass);
        let expected_boarding_pass = BoardingPass {
            row: 70,
            column: 7,
            seat_id: 567 
        };
        assert_eq!(result, expected_boarding_pass);
    }

    #[test]
    fn parse_boarding_pass_e3() {
        let pass = "FFFBBBFRRR";
        let result = parse_boarding_pass(pass);
        let expected_boarding_pass = BoardingPass {
            row: 14,
            column: 7,
            seat_id: 119 
        };
        assert_eq!(result, expected_boarding_pass);
    }
    
    #[test]
    fn parse_boarding_pass_e4() {
        let pass = "BBFFBBFRLL";
        let result = parse_boarding_pass(pass);
        let expected_boarding_pass = BoardingPass {
            row: 102,
            column: 4,
            seat_id: 820 
        };
        assert_eq!(result, expected_boarding_pass);
    }

    #[test]
    fn day5_p1_test() {
        let input = include_str!("../../inputs/day5.txt");
        let result = day5_p1(input);
        assert_eq!(result, 928);
    }

    #[test]
    fn day5_p2_test() {
        let input = include_str!("../../inputs/day5.txt");
        let result = day5_p2(input);
        assert_eq!(result, 610);
    }
}
