package main

import "fmt"

func main() {
	instantiation()

	printMap(map[string]string {
		"A": "1",
		"B": "2",
		"Z": "26",
	})
}

func printMap(c map[string]string) {
	for k, v := range c {
		fmt.Printf("Key %v, Value %v\n", k, v)
	}
}

func instantiation() {
	var countries map[string]string
	countries = make(map[string]string)
	countries["uk"] = "United Kingdom"
	fmt.Println(countries)


	colours := map[string]string{
		"red": "#FF0000",
		"green": "#00FF00",
	}
	fmt.Println(colours)

	delete(colours, "red")
	fmt.Println(colours)
}
