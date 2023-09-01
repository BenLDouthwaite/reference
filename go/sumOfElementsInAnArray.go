package main

import (
	"fmt"
)

func main() {
	arrayOfInts := []int32{1, 2, 3, 4}
	sumOfInts := simpleArraySum(arrayOfInts)
	fmt.Printf("Sum of ints = %d", sumOfInts)
}

func simpleArraySum(ar []int32) int32 {
	sum := int32(0)
	for _, num := range ar {
		sum += num
	}
	return sum
}
