package main

import (
	"fmt"
	"io"
	"net/http"
	"os"
)

type myConsoleWriter struct {}

func main() {
	url := "http://google.com"
	response, err := http.Get(url)
	if err != nil {
		fmt.Print("Err1:", err)
		os.Exit(1)
	}

	io.Copy(myConsoleWriter{}, response.Body)
}

func (myConsoleWriter) Write(bs []byte) (int, error) {
	fmt.Println(string(bs))
	fmt.Println("Done with write")
	return len(bs), nil
}