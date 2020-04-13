package main

import (
	"fmt"
	"net/http"
	"time"
)

func main() {
	sites := []string{
		"http://google.com",
		"http://facebook.com",
		"http://stackoverflow.com",
	}

	c := make(chan string)

	for _, site := range sites {
		go checkSite(site, c)
	}

	for s := range c {
		go func(site string) {
			time.Sleep(5 * time.Second)
			checkSite(site, c)
		}(s)
	}
}

func checkSite(site string, c chan string) {
	_, err := http.Get(site)
	if err != nil {
		fmt.Println("Error with :", site)
		c <- site
		return
	}

	fmt.Println(site, " is up")
	c <- site
}
