package main

import (
	"golang.org/x/tour/pic"
)

func Pic(dx, dy int) [][]uint8 {
	// fmt.Println("Creating pic")
	mypic := make([][]uint8, dy)
	for i := 0; i < dy; i++ {
		mypic[i] = make([]uint8, dx)
		for j := 0; j < dx; j++ {
			mypic[i][j] = uint8(i + j)
		}
	}
	// fmt.Println("Created pic")
	return mypic
}

func main() {
	// Works in browser, but only prints on local
	pic.Show(Pic)
}
