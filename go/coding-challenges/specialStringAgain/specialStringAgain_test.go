package main

import (
	"testing"
)

func Test_substrCount(t *testing.T) {
	type args struct {
		n int32
		s string
	}
	tests := []struct {
		name string
		args args
		want int32
	}{
		{"s0", args{5, "asasd"}, 7},
		{"s1", args{7, "abcbaba"},10},
		{"s2", args{4, "aaaa"},10},
		{"c0", args{1, "a"},1},
		{"c1", args{2, "aa"},3},
		{"c2", args{3, "aba"},4},
		{"c3", args{5, "aaaaa"},15},
		{"c4", args{6, "aaaaaa"},21},
		{"c5", args{7, "aaaaaaa"},28},
		{"c6", args{5, "aabaa"},9},
		{"c7", args{7, "aaabaaa"},16},

	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := substrCount(tt.args.n, tt.args.s); got != int64(tt.want) {
				t.Errorf("substrCount() = %v, want %v", got, tt.want)
			}
		})
	}
}