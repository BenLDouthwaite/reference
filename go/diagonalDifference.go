package main

import (
	"fmt"
	"math"
)

func main() {

	arr := [][]int32{
		[]int32{11, 2, 4},
		[]int32{4, 5, 6},
		[]int32{10, 8, -12},
	}

	result := diagonalDiffernce(arr)

	fmt.Printf("Res = %d", result)
}

func diagonalDiffernce(arr [][]int32) int32 {

	primaryDiagonal := int32(0)
	secondaryDiagonal := int32(0)
	arrLen := len(arr)

	for i := 0; i < arrLen; i++ {
		primaryDiagonal += arr[i][i]
		secondaryDiagonal += arr[i][arrLen-i-1]
	}

	result := int32(math.Abs(float64(primaryDiagonal - secondaryDiagonal)))
	return result
}
