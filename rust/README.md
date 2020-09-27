# Rust

## Docs 

`rustup docs --book`

## Commands

`rustfmt` Format

`rustc {file.rs}` Compile to binary

## Syntax 

* ! denotes a macro, as opposed to a regular function
* ; needed at the end of the line
## Naming

* Use underscores to separate words in a file name
* 'main' function is the first to be run

## Style

* Indentation is 4 spaces, not a tab

## Language Features

* Rust is an ahead-of-time compiled language, meaning you can compile a program and give the executable to someone else, and they can run it even without having Rust installed.

# Cargo

* System and package manager
* Used TOML for config
* `cargo build` build the executable
* Cargo.lock is generated automatically, we never need to change it
* `cargo run` compile and execute
* `cargo check` check if it will compile, quicker than build
* `cargo build --release` Build prod executable, slower to build, quicker to run