package main

import (
	"strings"

	"golang.org/x/tour/wc"
)

func WordCount(s string) map[string]int {

	words := strings.Fields(s)
	wordCount := make(map[string]int)
	for _, word := range words {
		if _, ok := wordCount[word]; ok {
			wordCount[word]++
		} else {
			wordCount[word] = 1
		}
	}
	return wordCount
}

func main() {
	wc.Test(WordCount)
}
