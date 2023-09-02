package main

import "fmt"

func main() {
	a := []int32{17, 28, 30}
	b := []int32{99, 16, 8}

	ret := compareTriplets(a, b)
	fmt.Printf("Alice = %d, Bob = %d", ret[0], ret[1])
}

// Complete the compareTriplets function below.
func compareTriplets(a []int32, b []int32) []int32 {

	alicePoints := int32(0)
	bobPoints := int32(0)

	// assuming length is the same for both
	for i := range a {
		if a[i] > b[i] {
			alicePoints++
		} else if a[i] < b[i] {
			bobPoints++
		}
	}

	return []int32{alicePoints, bobPoints}
}
