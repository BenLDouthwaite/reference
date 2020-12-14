use std::io;

fn main() {
    println!("Hello, world!");

    let question = "hello";
    let correct_answer = String::from("bonjour");

    loop {
        println!("Question : {}. Show answer?", question);

        // Wait for any user input while thinking of an answer
        let mut line = String::new();
        io::stdin().read_line(&mut line).unwrap();

        println!("Answer : {}", correct_answer);

        println!("Did you get it correct? (y/n)");

        let mut guess_correct = String::new();
        io::stdin()
            .read_line(&mut guess_correct)
            .expect("Failed to read line");

        match guess_correct.trim() {
            "y" => { println!("gg"); break },
            _ => println!("try again later")
        }
    }

    println!("Done")
}
