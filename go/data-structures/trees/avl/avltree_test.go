package main

import (
	"fmt"
	//"reflect"
	"testing"
)

func Test_avltree_insert(t *testing.T) {
	type args struct {
		value int
	}
	tests := []struct {
		name   string
		init avltree
		args   args
		want avltree
	}{
		{"root insert",
			avltree{root:nil},
			args{value: 10},
			avltree{root:&node{
				value:10,
				height:1,
			}},
		},
		{
			"left insert",
			generateTree([]int{10}),
			args{value: 8},
			avltree{root:&node{
				value:10,
				left:&node{value:8, height:1},
				height:2,
				balance:-1,
			}},
		},
		{
			"right insert",
			generateTree([]int{10}),
			args{value:12},
			avltree{root:&node{
				value:10,
				right:&node{value:12, height:1},
				height:2,
				balance:1,
			}},
		},
		{
			"left insert - rebalance",
			generateTree([]int{10, 8}),
			//avltree{root:&node{value:10, left:&node{value:8}, height:1}},
			args{value:6},
			avltree{
				root:&node{
					value:8,
					left:&node{value:6, height:1},
					right:&node{value:10, height:1},
					height:2,
				},
			},
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t1 *testing.T) {
			tr := tt.init
			tr.insert(tt.args.value)

			if tr.String() != tt.want.String() {
				t.Errorf("insert() = %v, want %v", tr, tt.want)
			}
		})
	}
}

func generateTree(values[] int) avltree {
	t := avltree{root:nil}
	for _, value := range values {
		t.insert(value)
	}
	fmt.Println(t)
	return t
}