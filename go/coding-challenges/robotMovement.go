package main

import (
	"math"
	"strings"
)

func robotMovement(inputString string) int {
	characters := strings.Split(inputString, "")

	xStepCount := 0
	yStepCount := 0
	for i := 0; i < len(characters); i++ {
		s := characters[i]
		switch s {
		case "N": yStepCount++
		case "E": xStepCount++
		case "S": yStepCount--
		case "W": xStepCount--
		}
	}
	return int(math.Abs(float64(xStepCount)) + math.Abs(float64(yStepCount)))
}
