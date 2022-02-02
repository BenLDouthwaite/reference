package main

import (
	"fmt"

	"example.com/greetings"
)


func main() {
	message := greetings.Hello("Bob")
	fmt.Println(message)
}
