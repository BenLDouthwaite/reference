package main

import "fmt"

func main() {
	var is []int
	for i := 0; i <= 10; i++ {
		is = append(is, i)
	}
	for i := range is {
		fmt.Printf("%v is ", i)
		if i % 2 == 0 {
			fmt.Print("even\n")
		} else {
			fmt.Print("odd\n")
		}
	}
}
