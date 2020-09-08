package main

import (
	"errors"
	"fmt"
)

func main() {
	fmt.Println("\nBinary Tree. lookupRec. insertRec. delete.")

	bt := btree{root: nil}

	//bt.insertRec(10)
	//bt.insertRec(8)
	//bt.insertRec(6)
	//
	//bt.insertRec(12)
	//bt.insertRec(14)
	//bt.insertRec(13)
	//bt.insertRec(11)
	bt.insertRec(4)
	bt.insertRec(2)
	bt.insertRec(3)
	bt.insertRec(1)
	bt.insertRec(6)
	bt.insertRec(5)
	bt.insertRec(7)

	bt.print()

	var n *node
	var err error
	n, err = bt.lookupRec(10)
	n, err = bt.lookupRec(13)
	n, err = bt.lookupRec(999)

	n, err = bt.lookup(19)

	if err != nil {
		fmt.Println("err", err)
	} else {
		n.print()
	}
	fmt.Println("\n", n, err)

}

type btree struct {
	root *node
}

type node struct {
	value int
	left *node
	right *node
}

func (bt *btree) lookup(value int) (*node, error) {
	if bt.root == nil {
		return nil, errors.New("nil root")
	}

	n := bt.root
	for {
		if n.value == value {
			return n, nil
		} else if n.value > value {
			if n.left != nil {
				n = n.left
				continue
			}
		} else { // n.value < value
			if n.right != nil {
				n = n.right
				continue
			}
		}
		return nil, errors.New("not found on leaf node")
	}
}

func (bt *btree) lookupRec(value int) (*node, error) {
	if bt.root == nil {
		return nil, errors.New("nil root")
	}

	res, err := bt.root.lookupRec(value)
	fmt.Println("\nres: ", res, ". err: ", err)
	return res, err
}

func (n *node) lookupRec(value int) (*node, error) {
	if n.value == value {
		return n, nil
	}
	if n.value > value {
		if n.left != nil {
			return n.left.lookupRec(value)
		}
	} else { // n.value < value
		if n.right != nil {
			return n.right.lookupRec(value)
		}
	}
	return nil, errors.New("not found on leaf node")
}

func (bt *btree) insertRec(value int) {
	if bt.root == nil {
		bt.root = &node{value: value}
	} else {
		bt.root.insertRec(value)
	}
}

func (n *node) insertRec(value int) {
	if n.value >= value {
		if n.left == nil {
			n.left = &node{value:value}
		} else {
			n.left.insertRec(value)
		}
	} else {
		if n.right == nil {
			n.right = &node{value:value}
		} else {
			n.right.insertRec(value)
		}
	}
}

func (n node) print() {
	fmt.Print(n.value)
	fmt.Print(",")
	if n.left != nil {
		fmt.Print("L[")
		n.left.print()
		fmt.Print("]")
	}
	if n.right != nil {
		fmt.Print("R{")
		n.right.print()
		fmt.Print("}")
	}
}

func (bt btree) print() {
	if bt.root == nil {
		fmt.Println("Cannot print nil root")
	}
	bt.root.print()
	fmt.Println(".End")
}