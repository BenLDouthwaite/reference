package main

import "fmt"

// Complete the aVeryBigSum function below.
func aVeryBigSum(ar []int64) int64 {
	sum := int64(0)
	for _, num := range ar {
		fmt.Printf("num = %d", num)
		sum += num
	}
	return sum
}

func main() {

	ar := []int64{1000000001, 1000000002, 1000000003, 1000000004, 1000000005}

	result := aVeryBigSum(ar)

	fmt.Printf("Sum = %d", result)
}
