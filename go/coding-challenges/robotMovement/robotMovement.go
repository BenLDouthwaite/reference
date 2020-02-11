package robotMovement

import (
	"math"
)

func robotMovement(inputString string) int {
	xStepCount := 0
	yStepCount := 0
	for _, c := range inputString {
		switch string(c) {
		case "N": yStepCount++
		case "E": xStepCount++
		case "S": yStepCount--
		case "W": xStepCount--
		}
	}
	return int(math.Abs(float64(xStepCount)) + math.Abs(float64(yStepCount)))
}
