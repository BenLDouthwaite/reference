package evenFibonacciNumbers

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

// Used on HackerRank on submission
func main() {
	scanner := bufio.NewScanner(os.Stdin)
	scanner.Scan()
	numberOfTestCases, _ := strconv.Atoi(scanner.Text())
	for i := 0; i < numberOfTestCases; i++ {
		scanner.Scan()
		input, _ := strconv.Atoi(scanner.Text())
		result := evenFibonacciNumbers(input)
		fmt.Println(result)
	}
}

func evenFibonacciNumbers(n int) int {
	// E(n)= 4*E(n-1) + E(n-2)....
	current := 2
	previous := 0
	count := 2
	for next := 8; next < n; next = 4 * current + previous {
		count += next
		previous = current
		current = next
	}
	return count
}

