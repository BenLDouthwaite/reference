package main

import (
	"fmt"
	"math"
	"math/rand"
	"time"
)

var global bool

const Pi = 3.14159

func main() {
	rand.Seed(time.Now().UTC().UnixNano())
	fmt.Println("Welcome to the playground")
	fmt.Println("The time now is", time.Now())
	fmt.Println("Random number", rand.Intn(10))
	fmt.Println("Square root of 10 ", math.Sqrt(10))
	fmt.Println("Pi", math.Pi)
	fmt.Println("Add method. 5 and 6 ", add(5, 6))
	a, b := swap("Hello", "World")
	fmt.Println("Multiple return function demo. swap 'Hello', 'World' ", a, b)
	fmt.Println("Factorise 17 , 5 expect 3, 2 result ")
	fmt.Println(factorise(17, 5))

	vars()
	types()
	typeconversions()
	//bitshifting()

	looping()
	branching()
}

func branching() {
	x := 7
	if x < 10 {
		fmt.Println("X is less than 10, val = ", x)
	}

	// if can pre started with a short statment, limited to its scope
	if pow := x * x; pow < 100 {
		fmt.Println("Pow < 100. val = ", pow)
	}
	// Could now use 'pow' here

	if half := x / 2; half > 1 {
		fmt.Println("Half greater than 1. val = ", half)
	} else {
		fmt.Println("Half less than 1. val = ", half)
	}
	// half cannot be used here
}

func looping() {
	sum := 0
	for i := 0; i < 10; i++ {
		sum += i
	}

	counter := 1
	for counter < 100 { // Init and post statements optional
		counter += counter
	}

	/** The following would be an infinit loop
	for {
	}
	**/
	fmt.Println("SUM = ", sum, ".Counter = ", counter)
}

func add(x int, y int) int {
	return x + y
}

func swap(x, y string) (string, string) {
	return y, x
}

func factorise(value, divisor int) (factor, remainder int) {
	factor = value / divisor
	remainder = value % divisor
	return
}

func vars() {
	var i int
	age := 12
	happy := false
	fmt.Println("Vars ", i, global, age, happy)
}

func types() {
	// TODO Too boring to do now
}

func typeconversions() {
	i := 42
	f := float64(i)
	u := uint(f)
	fmt.Println("Formatted u ", u)
}

const (
	// Create a huge number by shifting a 1 bit left 100 places.
	// In other words, the binary number that is 1 followed by 100 zeroes.
	Big = 1 << 100
	// Shift it right again 99 places, so we end up with 1<<1, or 2.
	Small = Big >> 99
)
