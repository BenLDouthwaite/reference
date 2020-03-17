package main

func main() {

}

// Complete the substrCount function below.
func substrCount(n int32, s string) int64 {

	specialStringCount := len(s)
	for i := 0; i < len(s); i++ {
		baseCharacter := s[i]
		differenceIndex := -1
		for j := i + 1; j < len(s); j++ {
			comparisonCharacter := s[j]
			if baseCharacter == comparisonCharacter {
				if differenceIndex == -1 || (j - differenceIndex == differenceIndex - i) {
					specialStringCount++
				}
			} else {
				if differenceIndex == -1  {
					differenceIndex = j
				} else {
					break
				}
			}
		}
	}

	return int64(specialStringCount)
}