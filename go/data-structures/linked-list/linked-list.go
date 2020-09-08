package main

import "fmt"

func main() {
	ll := linkedlist{
		head:  nil,
		tail:  nil,
		count: 0,
	}

	ll.append(10)
	ll.print("1")

	ll.append(12)
	ll.print("2")

	ll.append(18)
	ll.print("3")

	ll.append(6)
	ll.print("4")
}

type linkedlist struct {
	head *node
	tail *node
	count int
}

type node struct {
	value int
	next *node
}

func (ll *linkedlist) append(value int) {
	n := &node{value:value}
	if ll.head == nil {
		ll.head = n
		ll.tail = n
	} else {
		ll.tail.next = n
		ll.tail = n
		ll.count++
	}
}

func (ll *linkedlist) print(ref string) {
	fmt.Println("--: ", ref, " :------------------------")
	if ll.head == nil {
		fmt.Println("empty list")
	} else {
		fmt.Print("head: ")
		ll.head.print()

		fmt.Print("\ntail: ")
		ll.tail.print()

		n := ll.head
		fmt.Print("\ntraversal: ", n.value)
		for n.next != nil {
			n = n.next
			fmt.Print("->", n.value)
		}
	}
	fmt.Println("\n=================================\n")
}

func (n *node) print() {
	fmt.Print("val: ", n.value, ". ")
	if n.next != nil {
		fmt.Print("next: {")
		n.next.print()
		fmt.Print("}")
	} else {
		fmt.Print("next : nil")
	}
}