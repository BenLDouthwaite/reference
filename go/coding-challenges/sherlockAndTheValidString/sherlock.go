package sherlock

import (
	"math"
)

func sherlock(s string) string {

	charCount := make(map[rune]int)
	for _, c := range s {
		charCount[c]++
	}

	baseCount := charCount[rune(s[0])]
	allowedChanges := 1
	for _, v := range charCount {
		if v != baseCount {
			if v == 1 {
				allowedChanges--
			} else {
				allowedChanges -= int(math.Abs(float64(baseCount - v)))
			}
			if allowedChanges < 0 {
				return "NO"
			}
		}
	}
	return "YES"
}
