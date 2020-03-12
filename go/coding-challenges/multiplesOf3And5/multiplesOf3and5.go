package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

func main() {
	scanner := bufio.NewScanner(os.Stdin)
	scanner.Scan()
	numberOfTestCases , _ := strconv.Atoi(scanner.Text())
	for i := 0; i < numberOfTestCases; i++ {
		scanner.Scan()
		input, _ := strconv.Atoi(scanner.Text())
		result := multiplesOf3And5(input)
		fmt.Println(result)
	}
}

func multiplesOf3And5(input int) int {
	sum := 0
	sum += getFactorSum(input, 3)
	sum += getFactorSum(input, 5)
	sum -= getFactorSum(input, 15)
	return sum
}

func getFactorSum(input int, n int) int {
	m := (input - 1) / n
	return ((m * m) + m) / 2 * n
}
