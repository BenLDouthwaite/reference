package main

import "fmt"

type person struct {
	firstName string
	lastName string
	contactInfo
}

type contactInfo struct {
	email string
	zip int
}

func main() {
	// Different ways of defining structs
	john := person{
		firstName: "John",
		lastName:  "Smith",
		contactInfo: contactInfo{
			email: "JohnSmith@gmail.com",
			zip: 789,
		},
	}
	john.print()

	ben := person{
		"Ben",
		"Jones",
		contactInfo{"a@b.c", 123}}
	ben.updateName("Benji") // can use type to access pointer receiver directly
	ben.print()


	var alex person
	alex.firstName = "Alex"
	alex.lastName = "Anderson"
	alex.contactInfo.email = "alex@gmail.com"
	alex.contactInfo.zip = 123

	alexPointer := &alex
	alexPointer.updateName("Alexander")
	alex.print()
}

// Go is pass by value, we need to pass a pointer
func (pPtr *person) updateName(newFirstName string) {
	(*pPtr).firstName = newFirstName
}

func (p person) print() {
	fmt.Printf("%+v\n", p)
}
