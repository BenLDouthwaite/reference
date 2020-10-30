fn main() {
    println!("Hello, world!");

    const PI: f64 = 3.14159;
    println!("Constant PI is: {}", PI);
    let mut x = 5;
    println!("The value of x is: {}", x);
    x = 6;
    println!("The value of x is: {}", x);

    // Shadowing
    let spaces = "   ";
    let spaces = spaces.len();
    println!("Spaces len is: {}", spaces);

    another_function();

    func_with_params(123, 456);

    let val = func_with_return_value();
    println!("Returned val is: {}", val);

    // References and borrowing
    let s1 = String::from("Hello to all the world");
    let len = calculate_length(&s1);
    println!("Line len = {}", len);

    // Mutable refernces
    let mut s2 = String::from("The start");
    println!("S2, pre change: {} ", s2);
    change(&mut s2);
    println!("S2, post change: {}", s2);

    // Slices
    let s3 = String::from("Test string");
    let w1 = first_word(&s3);
    println!("First word slice: {}", w1);

    let s = String::from("hello world");
    let hello = &s[0..5];
    let world = &s[6..11];
    println!("World slice : {}", world);

    // Structs
    let mut user1 = User {
        email: String::from("someone@example.com"),
        username: String::from("someusername123"),
        active: true,
        sign_in_count: 1,
    };
    // Can use dot syntax to update
    user1.email = String::from("anotheremail@example.com");

    let user2 = build_user(String::from("Email"), String::from("username"));

    // Struct update syntax, only override specified fields, use existing for the resr
    let user3 = User {
        email: String::from("another@example.com"),
        ..user1
    };
}

fn build_user(email: String, username: String) -> User {
    User {
        email, // init shorthand means we don't redefine with the same name
        username,
        active: true,
        sign_in_count: 1,
    }
}

/**
used the owned String type rather than the &str string slice type.
This is a deliberate choice because we want instances of this struct
    to own all of its data and for that data to be valid for as long as the entire struct is valid.
**/
struct User {
    username: String,
    email: String,
    sign_in_count: u64,
    active: bool,
}

fn first_word(s: &str) -> &str {
    let bytes = s.as_bytes();

    for (i, &item) in bytes.iter().enumerate() {
        if item == b' ' {
            return &s[0..i];
        }
    }

    &s[..]
}

fn change(s: &mut String) {
    s.push_str(" of a sentence");
}

// Take &String, rather than String. & = reference
fn calculate_length(s: &String) -> usize {
    // s is a reference to a string
    s.len()
    // s goes out of scope, but because we don't have ownership, nothing happen"
}

fn another_function() {
    println!("Test function");
}

fn func_with_params(x: i32, y: i32) {
    println!("Param x is: {}", x);
    println!("Param y is: {}", y);
}

fn func_with_return_value() -> i32 {
    let x = 3 + 1;
    x + 1
}