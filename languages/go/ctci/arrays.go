package main

// CTCI 1.2. Check Permutation
func checkPermutation(a string, b string) bool {
	if len(a) != len(b) {
		return false
	}

	var cCount [128]int8 // Assumption ASCII
	for _, c := range a {
		cCount[c]++
	}

	for _, c := range b {
		cCount[c]--
		if cCount[c] < 0 {
			return false
		}
	}
	return true
}
