package main

import (
	"fmt"
	"strings"
)

func robotMovement(inputString string) int {
	characters := strings.Split(inputString, "")

	for c := range characters {
		fmt.Println(c)
	}
	fmt.Println(inputString)
	return 1
}