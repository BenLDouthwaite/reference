package spyGame

func spyGame(arr []int) bool {

	if len(arr) < 3 {
		return false
	}

	desired := [3]int{0,0,7}
	count := 0
	for _, num := range arr {
		if num == desired[count] {
			count++
			if count == len(desired) {
				return true
			}
		}
	}

	return false
}
