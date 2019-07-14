package main

import (
	"fmt"
	"time"
	"math"
	"math/rand"
)

var global bool

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
