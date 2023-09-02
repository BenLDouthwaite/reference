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

const st = "TODO" // Search Term
const maxLen = 120

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
		if strings.Contains(text, st) {
			text = trimText(text)
			outputText := getOutputText(text)

			fmt.Print(
				tf(35, fileName),
				":",
				tf(31, strconv.Itoa(lc)),
				"\t",
				outputText,
				"\n")
		}
	}

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}
}

func trimText(text string) string {
	text = strings.TrimSpace(text)
	if len(text) > maxLen {
		index := strings.Index(text, st)
		contextLength := (maxLen - len(st)) / 2
		start := 0
		if index-contextLength > 0 {
			start = index - contextLength
		}
		end := len(text)
		if index+contextLength < len(text) {
			end = index + contextLength
		}

		text = text[start:end]
	}
	return text
}

func getOutputText(text string) string {
	index := strings.Index(text, st)

	returnText := fmt.Sprint(
		text[:index],
		tf(32, st),
		text[index+len(st):])
	return returnText
}

// Terminal Format
func tf(formatCode int, s string) string {
	return fmt.Sprint("\033[", formatCode, "m", s, "\033[0m")
}
