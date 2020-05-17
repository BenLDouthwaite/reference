# GO 

## Language features

* Go is pass by value
* Can define receiver with a pointer to a type, we can call methods on the type directly. (See structs dir example)
    * If a receiver is not used in a function, we can define only the type in the method definition.
* Go contains reference types and value types. We can ignore pointers with reference types (inc slices)
    * Value Types: int, float, string, bool, structs
    * Reference Types: slices, maps, channels, pointers, functions
* Go allows easy implementation of concurrency
    * The Go Scheduler manages go routines
    * By default, go uses 1 CPU core!
    * easy to finish the main routine while child routines are not finished
    * use channels to talk between routines
        * channels are typed
    * Never access the same variable from child routines, pass in as arg / via channels
* Go supports function literals

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

can assign vars to functions.
`func t1() {...}
t2 := t1
t2()` is valid

Printing custom structs with labels for values
`fmt.Printf("%+v", alex)`

Use the `go` keyword to create new goroutines

To send data into channels `channel <- 5`
Can wait for values to be sent to the channel `fmt.Println(<- channel)`

Go routine with function literal and sleep
`go func(s string) {
    time.Sleep(time.Second)
    checkSite(s, c)
}(s)`

## Data Structures

* Array: Fixed length list of things
    * Rarely used in go, seen as primitive
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
    * Keys are not strongly types. Can access with dot syntax
    * Value type
* Maps
    * Keys and values are statically typed
    * Reference type
* Interfaces
    * Any type matching the interface, can use the methods for the interface type.
    * Can 'nest' interfaces, must meet requirements of all.
    
    
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

# Demo - Interesting Features :

- Works easily with foreign character sets [example](./helloworld/main.go)
- Binaries to execute [example](./gameOfLife/main.go)
- Scatter and gather
- Error handling 
- Composition over inheritance - Maintain loosely couples components [example](./interfaces/shapes/shapes.go)
- Powerful standard lib. -> http server [example](./http/webServer/main.go)

- No warnings, only errors or don't bother,
- Mantra of 'Do more with less'
