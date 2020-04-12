package main

import "fmt"

const ec = "\033" // Escape code for formatting

func main() {

	// 1 : Bold
	printlnFormat(1, "Bold")

	// 4 : Underline
	printlnFormat(4, "Underline")

	// 4 : Underline
	printlnFormat(5, "Blink")

	// 7 : Highlight
	printlnFormat(7, "Highlight")

	// 30 to 37 : Text Colour.
	for x := 30; x < 38; x++ {
		printlnFormat(x, "Colour")
	}

	// 40 to 47 : Highlight Colour.
	for x := 40; x < 48; x++ {
		printlnFormat(x, "Highlight Colour")
	}

	// 90 to 97 : More Text Colour
	for x := 90; x < 98; x++ {
		printlnFormat(x, "Colour - Intense")
	}

	// 100 to 107 : More Text Colour
	for x := 100; x < 107; x++ {
		printlnFormat(x, "Highlight Colour - Intense")
	}

	// Combining Codes

}

func printlnFormat(formatCode int, s string) {
	printFormat(formatCode, s)
	fmt.Print("\n")
}

func printFormat(formatCode int, s string) {
	fmt.Print(terminalColourFormat(formatCode, s))
}

func terminalColourFormat(formatCode int, s string) string {
	return fmt.Sprint("\033[", formatCode, "m", formatCode, ":", s, "\033[0m")
}
