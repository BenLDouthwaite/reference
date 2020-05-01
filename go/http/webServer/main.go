package main

import (
	"fmt"
	"html"
	"net/http"
)

func main() {
	http.ListenAndServe(":8080", handler())
}

func handler() http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		fmt.Fprintf(w, "Hello, %q", html.EscapeString(r.URL.Path))
	}
}
