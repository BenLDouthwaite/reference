package main

import (
	"testing"
)

// Single test case
// TODO Get this passing
func Test_arrays_checkduplication(t *testing.T) {
	s1 := "hello"
	s2 := "hi"
	want := true

	res := checkPermutation("abcdef", "fedcba")
	if res != want {
		t.Errorf("checkPermutation(%v, %v) = %v, want = %v", s1, s2, res, want)
	}

}

func Test_arrays_checkduplication_parameterised(t *testing.T) {
	type args struct {
		a string
		b string
	}
	tests := []struct {
		name string
		args args
		want bool
	}{
		{"Matching single characters", args{"a", "a"}, true},
		{"Different length strings", args{"aa", "a"}, false},
		{"Different single characters", args{"a", "b"}, false},
		{"Matching multiple characters", args{"abc", "cba"}, true},
		{"Different multiple characters", args{"abcdef", "ghijkl"}, false},
		{"Matching repeated characters", args{"aaabbc", "cbbaaa"}, true},
		{"Matching capitalised characters", args{"John Smith", "Smith John"}, true},
		{"Different capitalisation", args{"ABC", "abc"}, false},
		{"Different whitespace", args{" abc ", "abc"}, false},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if res := checkPermutation(tt.args.a, tt.args.b); res != tt.want {
				t.Errorf("checkPermutation(%v, %v) = %v, want %v", tt.args.a, tt.args.b, res, tt.want)
			}
		})
	}
}
