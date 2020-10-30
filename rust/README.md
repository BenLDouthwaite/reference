# Rust

## Docs 

`rustup docs --book`

## Commands

`rustfmt` Format

`rustc {file.rs}` Compile to binary

## Syntax 

* ! denotes a macro, as opposed to a regular function
* ; needed at the end of the line
* Rust doesn’t care where you define your functions, only that they’re defined somewhere.
* In function signatures, you must declare the type of each parameter.
* 'expressions' do not include ending semicolons
* Because `if` is an expression, we can use it on the right side of a let statement, as in Listing 3-2.
** Must be the same return type though
* `for element in (1..4) {...}` to iterate each

## Naming

* Use underscores to separate words in a file name
* 'main' function is the first to be run

## Style

* Indentation is 4 spaces, not a tab
* Rust code uses snake case as the conventional style for function and variable names. 

## Language Features

* Rust is an ahead-of-time compiled language, meaning you can compile a program and give the executable to someone else, and they can run it even without having Rust installed.
* In Rust, variables are immutable by default.
* references are immutable by default.
* Rust allows us to shadow the previous value of variables with a new one.
* You declare constants using the const keyword instead of the let keyword, and the type of the value must be annotated. 
* constants may be set only to a constant expression, not the result of a function call or any other value that could only be computed at runtime.
* Shadowing is different from marking a variable as mut, because we’ll get a compile-time error if we accidentally try to reassign to this variable without using the let keyword. By using let, we can perform a few transformations on a value but have the variable be immutable after those transformations have been completed.
    * Shadowing thus spares us from having to come up with different names, such as spaces_str and spaces_num; instead, we can reuse the simpler spaces name. 
* Rust is an expression-based language

## Ownership

* Checked at compile time, so does not slow down execution at runtime
* Stack: LIFO, push / pop
    * Must be fixed size
* Heap:
    * Variable sized data, can grow at runtime
* Pushing to the stack is faster than allocating to the heap -> Always push to the top fo the stack
* Processors are faster working on data that is close together
* Rules:
    1. Each value in Rust has an owner
    2. There can only be 1 owner at a time
    3. When the owner goes out of scope, the value will be dropped
* When a variable goes out of scope, Rust calls a special function for us. This function is called drop,
*  Rust prevents you from using the invalidated references
* Shallow copy & invalidating reference = a 'move'
* `clone` allows deep copying
* Passing a variable to a method, takes it out of scope of the parent method
* Returning values returns ownership

### References 

* When functions have references as parameters instead of the actual values, we won’t need to return the values in order to give back ownership, because we never had ownership.
* We call having references as function parameters borrowing. As in real life, if a person owns something, you can borrow it from them. When you’re done, you have to give it back.
* We cannot borrow a value as mutable
* *References are immutable by default*
* mutable references have one big restriction: you can have only one mutable reference to a particular piece of data in a particular scope.
    * This helps prevent data races at runtime
*  We also cannot have a mutable reference while we have an immutable one.
* a reference’s scope starts from where it is introduced and continues through the last time that reference is used.
* Rust prevents creating 'dangling references'

### Slices

* Slices do not have ownership
* Slices reference a contiguous sequence of elements in a collection
* A String slice is a reference to a part of a string
* Slices can drop the first or last digit, for the first and last char in a collection respectively (like python)
    * Dropping both values is the same as taking the entire string
* String literals are slices, stored in the binary

## Data Types

* Scalar Types: integer, floating-point, boolean, characters
** Integer can be singed or unsigned
*** _ used as a visual separator, 1_000
*** Can also represent; Hex (0xff), Octal (0o77), Binary (0b1111_0000), Byte (b'A')
*** Do not rely on integer overflow behavior
** Floating point: `f64` is the default over `f32`
** Numeric operations supported as normal
** Boolean. `true` or `false`. Same as usual. 
** `char` literals use single quotes, string literals use double quotes
*** Rust’s char type is four bytes in size and represents a Unicode Scalar Value
*** Includes: Korean, emoji, zero-width spaces
* Compound Types: tuples and arrays
    * Tuples group values of a variety of types into one compound type
        * Fixed length, once declared cannot grow or shrink
        * `destructuring` example: 
        ```
        let tup: (i32, f64, u8) = (500, 6.4, 1);
        let (x, y, z) = tup;
        let five_hundred = x.0;
        let one = x.2;
        println!("The value of y is: {}", y);
        ```
    * Arrays, must be the same type.
        * Fixed length like tuples
        * Arrays are useful when you want your data allocated on the stack rather than the heap 
        ```
        let months = ["January", "February", "March", "April", "May", "June", "July",
                    "August", "September", "October", "November", "December"];
        let a: [i32; 5] = [1, 2, 3, 4, 5];
        let b = [3; 5]; // is the same as `let a = [3, 3, 3, 3, 3];`
        ```
        * A vector is similar (to arrays) type from standard lib, can grow or shrink in size.
        * Rust prevents you accessing arrays at an index above the size of the array 

### Structs

* Entire instance is mutable, or none. Can't mix and match
* Struct update syntax, similar to spread in JS    

#### Tuple Structs

* Gives a name to the 'object' but not to each field
    * e.g. `struct Colour(i32, i32, i32)` for rgb. Is the default, so would be overkill to specify
    
# Cargo

* System and package manager
* `cargo new {name}` to create a new project
* Used TOML for config
* `cargo build` build the executable
* Cargo.lock is generated automatically, we never need to change it
* `cargo run` compile and execute
* `cargo check` check if it will compile, quicker than build
* `cargo build --release` Build prod executable, slower to build, quicker to run
* `cargo update` Update dependencies

























XMJF3MsdnaYyLgsh