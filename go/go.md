# GO 

## TODO

[] Read up on package structure

## Go CLI commands

Run:`go run filename.go`
    Run several files `go run main.go state.go`
Compile: `go build filename.go` and to execute `./filename`
Reformat: `gofmt` is a tool to autoformat files.
    to persist the output, run with the `-w` flag. e.g. `gofmt -w helloworld.go`
Test: `go test`

## Syntax

When two + function parameters share a type, you can omit from all but the last
`x int, y int` -> `x, y int`

can assin vars to functions.
`func t1() {...}
t2 := t1
t2()` is valid

## Data Structures

* Array: Fixed length list of things
* Slice: Array that can grow or shrink
    * Every record in a slice must be of the same type  
    * Define slice: `mySlice := []string{"Hi", "World"}`
    * Add element: `mySlice = append(mySlice, "Test")`
    * Subslices: `mySlice[startIndex:upToNotIncludingIndex]`
        * Excluding either index implies the start or end of the slice      
* Type: we can extend base types to add extra functionality
`type myStrings []string`
And add functions with 'myStrings' as a receiver
`type (m myStrings) print() {...}`
`m myStrings` acts as the receiver type
Any variable of type `myStrings` now gets access to the 'print' method
* Struct
## Project Layout

All files in the same package (directory) need to declare
the same package name at the top of the file.

Go has 2 types of packages:
* Executable - generate executable files 
* Reusable - libraries

When using the package 'main', we generate an executable package.
We must also create a 'main' function inside the main package

Files in the same package can freely call functions defined in other files.

## Naming Conventions

* Capitalise a function you want exposed to the rest of the program

# GOCD

https://www.gocd.org/ 

A pipeline runs in stages.
Stages run many jobs
What is a pod?
What is a build?
What is a job?

# Useful links

Packages: https://golang.org/pkg/ 