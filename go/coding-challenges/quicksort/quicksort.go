package quicksort

import (
	"fmt"
	"math/rand"
)

func main() {
	ints := quicksort([]int{30, 40, 50, 20, 60, 70, 90, 10, 100, 110});
	fmt.Print(ints)
}

func quicksort(a []int) []int {

	if len(a) < 2 {
		return a
	}

	left, right := 0, len(a) - 1

	pivotIndex := rand.Int() % len(a)

	// Move the pivot to the right
	a[pivotIndex], a[right] = a[right], a[pivotIndex]

	// Smaller than pivot go to the left
	for i := range a {
		if a[i] < a[right] {
			a[i], a[left] = a[left], a[i]
			left++
		}
	}

	// Place pivot after last smallest element
	a[left], a[right] = a[right], a[left]

	// Recursion
	quicksort(a[:left])
	quicksort(a[left + 1:])

	return a
}
