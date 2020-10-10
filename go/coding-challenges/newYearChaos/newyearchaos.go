package newyearchaos

import (
	"fmt"
)

func minimumBribes(q []int32) int {

	// Decrement to match indexes
	for i := 0; i < len(q); i++ {
		q[i] = q[i] - 1
	}

	var count = 0
	for i := 0; i < len(q); i++ {
		posVal := q[i]
		if int(posVal)-i > 2 {
			fmt.Printf("Too chaotic\n")
			return -1
		}

		for j := max(int(posVal-1), 0); j < i; j++ {
			if q[j] > posVal {
				count++
			}
		}
	}
	fmt.Println(count)
	return count
}

func max(x, y int) int {
	if x > y {
		return x
	}
	return y
}

// func minimumBribes_naiveDoubleLoop(q []int32) int {

// 	fmt.Printf("\n====\nInput array: %v\n", q)

// 	// naive double loop
// 	const maxSwaps = 2

// 	var count = 0

// 	// naive double loop
// 	var swaps = make([]int, len(q))
// 	for i := 0; i < len(q)-1; i++ {
// 		for j := 0; j < len(q)-i; j++ {
// 			// fmt.Printf("i.%d, j.%d. q.%v\n", i, j, q)

// 			if j+1 == int(q[j]) { // Value in correct location
// 				continue
// 			}

// 			if q[j] > q[j+1] {
// 				if swaps[q[j]-1] == maxSwaps {
// 					fmt.Printf("Too chaotic\n")
// 					return -1
// 				}
// 				swaps[q[j]-1]++ // Need -1 for 0 based array
// 				count++
// 				q[j], q[j+1] = q[j+1], q[j] // Swap with next
// 			}
// 		}
// 	}
// 	fmt.Printf("Final val, %v", q)
// 	fmt.Println(count)
// 	return count
// }
