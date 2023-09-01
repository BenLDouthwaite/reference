package main

import "fmt"

type shape interface {
	getArea() float64
}
type areaGetter interface {
	getArea() float64
}
type triangle struct {
	height float64
	base float64
}
type square struct {
	sideLength float64
}
type rectangle struct {
	length float64
	width float64
}
func main() {
	printArea(triangle{height:3, base:4})
	printArea(square{sideLength:5})
	printArea(rectangle{
		length: 123,
		width:  234,
	})
}

func printArea(s shape) {
	fmt.Println(s.getArea())
}

func (t triangle) getArea() float64 {
	return t.height * t.base * 0.5
}

func (s square) getArea() float64 {
	return s.sideLength * s.sideLength
}

func (r rectangle) getArea() float64 {
	return r.length * r.width
}