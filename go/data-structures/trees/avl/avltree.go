package main

import (
	"fmt"
	"math"
)

func main() {
	fmt.Print("test")

	t := avltree{root:nil}
	t.insert(10)
}

type avltree struct {
	root *node
}

func (t *avltree) insert(value int) {
	if t.root == nil {
		t.root = &node{value: value, height:1}
	} else {
		t.root = insert(t.root, value)
	}
}

func insert(n *node, value int) *node {
	if n.value >= value {
		if n.left == nil {
			n.left = &node{value:value, height:1}
		} else {
			insert(n.left, value)
		}
	} else {
		if n.right == nil {
			n.right = &node{value:value, height:1}
		} else {
			insert(n.right, value)
		}
	}

	n.getHeight()

	n = getBalance(n)
	if math.Abs(float64(n.balance)) > 1 {
		n = rebalance(n)
	}
	return n
}

func getBalance(n *node) *node {
	lh := 0
	//lh := -1
	if n.left != nil {
		lh = n.left.height
	}
	rh := 0
	//rh := -1
	if n.right != nil {
		rh = n.right.height
	}
	n.balance = rh - lh
	return n
}

func rebalance(n *node) *node {
	if n.balance == -2 {
		n = rotateRight(n)
	} else if n.balance == 2 {
		fmt.Println("Right heavy tree")
	} else {
		fmt.Println("Something went wrong, should never get here")
	}
	return n
}

func rotateRight(n *node) *node {
	save := n.left
	n.left = save.right
	save.right = n

	n.getHeight()
	getBalance(n)

	save.getHeight()
	getBalance(save)

	return save
}

func (n *node) getHeight() {
	lh := 0
	if n.left != nil {
		lh = n.left.height
	}
	rh := 0
	if n.right != nil {
		rh = n.right.height
	}
	n.height = int(math.Max(float64(lh), float64(rh))) + 1
}

type node struct {
	value int
	left  *node
	right *node
	height int
	balance int
}

func (n node) String() string {
	l := ""
	if n.left != nil {
		l = "[" + n.left.String() + "]"
	}
	r := ""
	if n.right != nil {
		r = "{" + n.right.String() + "}"
	}
	return fmt.Sprintf("%v%v%v<%v,%v>", n.value, l, r, n.height, n.balance)
}

func (t avltree) String() string {
	if t.root == nil {
		return "empty tree"
	}
	return fmt.Sprintf(t.root.String())
}
