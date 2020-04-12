package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strings"
)

func main() {

	// TODO Iterate over directory.
	// TODO Provide context

	fileName := "./todoHunter/test.txt"
	file, err := os.Open(fileName)
	if err != nil {
		log.Fatal(err)
	}

	defer file.Close()

	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		text := scanner.Text()
		if strings.Contains(text, "TODO") {
			fmt.Print(text)
		}
	}

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}
}
