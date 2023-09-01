package commonchild

import (
	"fmt"
)

func commonChild(a, b string) int {

	if a == b {
		return len(a)
	}

	arunes := []rune(a)
	brunes := []rune(b)
	aLen := len(arunes)
	bLen := len(brunes)
	lengths := make([][]int, aLen+1) // +1  size for default '0' value row for each
	for i := 0; i <= aLen; i++ {
		lengths[i] = make([]int, bLen+1)
	}
	/*
		Example. Data Initialised. A harry, B sally
		*        s a l l y
		*	  [0 0 0 0 0 0]
		*	h [0 0 0 0 0 0]
		*	a [0 0 0 0 0 0]
		*	r [0 0 0 0 0 0]
		*	r [0 0 0 0 0 0]
		*	y [0 0 0 0 0 0]
	*/

	//fmt.Printf("Data Initialised. A %v, B %v\n", a, b)
	//printMatrix(a, b, lengths)

	for i, ar := range arunes {
		for j, br := range brunes {
			// fmt.Printf("i%d, j%d, ar%d, br%d.\n", i, j, ar, br)
			if ar == br {
				// fmt.Printf("a%d b%d.%s %s MATCH. + 1 somehow\n", ii, ji, string(i), string(j))
				// lengths[ii+1][ji+1] = maxInt(lengths[ii+1][ji], lengths[ii][ji+1]) + 1
				lengths[i+1][j+1] = lengths[i][j] + 1
				//printMatrix(a, b, lengths)
			} else {
				lengths[i+1][j+1] = maxInt(lengths[i+1][j], lengths[i][j+1]) // max of up or left in printed matrix
			}
		}
	}

	//fmt.Printf("Generated matrix\n")
	//printMatrix(a, b, lengths)

	// read the substring out from the matrix
	s := make([]rune, 0, lengths[aLen][bLen])
	for x, y := aLen, bLen; x != 0 && y != 0; {
		//fmt.Printf("s.%s, x.%d, y.%d. L.xy.%d. L.xy-1.%d. Lx-1y.%d",string(s), x, y, lengths[x][y], lengths[x][y-1], lengths[x-1][y])
		if lengths[x][y] == lengths[x-1][y] {
			//fmt.Printf(" c X")
			x--
		} else if lengths[x][y] == lengths[x][y-1] {
			//fmt.Printf(" Reduce Y")
			y--
		} else {
			//fmt.Printf(" MATCH Reduce X and Y")
			s = append(s, arunes[x-1])
			x--
			y--
		}
		//fmt.Printf("\n")
	}

	for i, j := 0, len(s)-1; i < j; i, j = i+1, j-1 {
		s[i], s[j] = s[j], s[i]
	}

	// return string(s)
	//printMatrix(a, b, lengths)
	//fmt.Printf("a.%s, b.%s, CC.%s\n", a, b, string(s))
	return len(string(s))
}

func maxInt(a int, b int) int {
	if a > b {
		return a
	}
	return b
}

// Formatting doesn't work after 10 chars
func printMatrix(a string, b string, m [][]int) {
	// print top row showing 'b' string
	fmt.Print("     ")
	for _, c := range b {
		fmt.Printf("%c ", c)
	}
	fmt.Println()

	fmt.Printf("  %v\n", m[0]) // Zeroed out first row
	for i := 1; i < len(m); i++ {
		fmt.Printf("%s %v\n", string(a[i-1]), m[i]) // Include character in 'a' being checked
	}
	fmt.Println("---")
}
