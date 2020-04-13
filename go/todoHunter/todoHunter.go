package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"path/filepath"
	"strconv"
	"strings"
)

func main() {

	// TODO Provide context
	// TODO trim long lines
	// TODO Ignore files in the .gitignore

	pathRoot := setPathRoot()

	filepath.Walk(pathRoot, walkPath)
}

func setPathRoot() string {
	var pathRoot = "."
	if len(os.Args) >= 2 {
		pathRoot = os.Args[1]
	}
	return pathRoot
}

func walkPath(path string, info os.FileInfo, err error) error {

	if !info.IsDir() && !isExec(info) {
		readFile(path)
	}
	return nil
}

func isExec(info os.FileInfo) bool {
	isExec := info.Mode().Perm()&0111 != 0
	return isExec
}

func readFile(fileName string) {
	file, err := os.Open(fileName)
	if err != nil {
		log.Fatal(err)
	}

	defer file.Close()

	lc := 0
	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		lc++
		text := scanner.Text()
		if strings.Contains(text, "TODO") {
			fmt.Print(
				tf(35, fileName),
				":",
				tf(31, strconv.Itoa(lc)),
				"\t",
				strings.TrimSpace(text),
				"\n")
		}
	}

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}
}

// Terminal Format
func tf(formatCode int, s string) string {
	return fmt.Sprint("\033[", formatCode, "m", s, "\033[0m")
}
