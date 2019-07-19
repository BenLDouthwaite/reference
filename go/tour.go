package main

import (
	"fmt"
	"math"
	"math/rand"
	"runtime"
	"strings"
	"time"
)

var global bool

const Pi = 3.14159

type Vertex struct {
	X int
	Y int
}

func main() {

	introprinting()

	vars()
	types()
	typeconversions()
	//bitshifting()

	looping()
	branching()
	switches()
	deferingexecution()
	pointers()
	vertexes()
	arrays()
	slices()
	slicemaking()
	slicingslices()
	sliceAppending()
	ranges()
}

func ranges() {
	pow := make([]int, 10)
	for i := range pow {
		pow[i] = 1 << uint(i) // == 2**i
	}

	// Similar to Java 'for each'
	for i, v := range pow {
		fmt.Printf("2**%d = %d\n", i, v)
	}

	// Can inline range definition. Use '_' to ignore index or value
	for _, v := range []int{1, 2, 3} {
		fmt.Printf("v = %d\n", v)
	}
}

func sliceAppending() {
	var s []int
	printSlice(s)

	// append works on nil slices.
	s = append(s, 0)
	printSlice(s)

	// slice grows as needed
	s = append(s, 1)
	printSlice(s)

	// Can append many elements at once
	s = append(s, 2, 3, 4)
	printSlice(s)
}

func slicingslices() {
	// Create a tic-tac-toe board.
	board := [][]string{
		[]string{"_", "_", "_"},
		[]string{"_", "_", "_"},
		[]string{"_", "_", "_"},
	}

	// The players take turns
	board[0][0] = "X"
	board[0][2] = "0"
	board[2][0] = "X"

	for i := 0; i < len(board); i++ {
		fmt.Printf("%s\n", strings.Join(board[i], " "))
	}
}

func slicemaking() {
	// TODO https://tour.golang.org/moretypes/13
	a := make([]int, 5)
	printSlice(a)

	b := make([]int, 0, 5)
	printSlice(b)

	c := b[:2]
	printSlice(c)

	d := c[2:5]
	printSlice(d)
}

func slices() {
	// slices are dynamic, flexible views inrto arrays
	// []T is a slice with elements of type T
	// formed with two indices a[low: high]
	// Includes first element, excludes last one
	primes := [6]int{2, 3, 5, 7, 11, 13}

	var slic []int = primes[1:4]
	fmt.Println(slic)

	// slices just describe an array
	// changing a slice modifies the array
	// and other slices pointing to the same array

	names := [4]string{
		"John",
		"Paul",
		"George",
		"Ringo",
	}
	fmt.Println(names)

	a := names[0:2]
	b := names[1:3]
	fmt.Println(a, b)

	b[0] = "XXX"
	fmt.Println(a, b)
	fmt.Println(names)

	// Slice literals
	q := []int{2, 3, 5, 7, 11, 13}
	fmt.Println(q)

	r := []bool{true, false, true, true, false, true}
	fmt.Println(r)

	sl := []struct {
		i int
		b bool
	}{
		{2, true},
		{3, false},
		{5, true},
		{7, true},
		{11, false},
		{13, true},
	}
	fmt.Println(sl)

	// Can omit low or high bound for defaults
	// 0 for low bound, array length for hight bound
	// ( Java substring behavior)

	// Slices have length and capacity
	// length is the number of elements it contaisn
	// capacity is the # of elements in the underlying array
	s := []int{2, 3, 5, 7, 11, 13}
	printSlice(s)

	// Slice the slice to give it zero length.
	s = s[:0]
	printSlice(s)

	// Extend its length.
	s = s[:4]
	printSlice(s)

	// Drop its first two values.
	s = s[2:]
	printSlice(s)

	// expand too much -> out of bounds error
	// s = s[:100]
	// printSlice(s)
}

func printSlice(s []int) {
	fmt.Printf("len=%d cap=%d %v\n", len(s), cap(s), s)
}

func arrays() {
	// [n]T is an array of n values of type T
	// Arrays cannot be resized
	var a [2]string
	a[0] = "Hello"
	a[1] = "World"
	fmt.Println(a[0], a[1])
	fmt.Println(a)

	primes := [6]int{2, 3, 5, 7, 11, 13}
	fmt.Println(primes)

}
func vertexes() {
	fmt.Println(Vertex{1, 2})
	v := Vertex{7, 2}
	fmt.Println(v)
	v.X = 4
	fmt.Println(v.X)
	fmt.Println(v)

	p := &v
	p.X = 1e9
	fmt.Println(v)

	// Struct literals
	v1 := Vertex{X: 6, Y: 7}
	fmt.Println(v1)
}
func pointers() {
	// '*T' is a pointer to a 'T' value
	// Zero value is 'nil'
	// the '&' operator gives a pointer to its operand
	i, j := 42, 2701
	p := &i

	fmt.Println(*p) // read i through p
	*p = 21         // set i through p
	fmt.Println(i)  // new value
	p = &j
	*p = *p / 37
	fmt.Println(j)
}

func introprinting() {
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
}

func deferingexecution() {

	// Deferred call will wait until the surrounding funtion returns
	// Deferred calls can be pushed onto a LIFO stack
	defer fmt.Println("world")

	fmt.Println("Hello, ")
}

func switches() {

	switch os := runtime.GOOS; os {
	case "darwin":
	case "linux":
		fmt.Println("Linux OS")
	default:
		fmt.Println("Default os. OD = ", os)
	}

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
	// Could not use 'pow' here

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
