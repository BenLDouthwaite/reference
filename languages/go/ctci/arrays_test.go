package main

import (
	"testing"
)

// Single test case
// TODO Get this passing
func Test_arrays_checkduplication(t *testing.T) {
	s1 := "hello"
	s2 := "hi"
	want := false

	res := checkPermutation("hello", "hi")
	if res != want {
		t.Errorf("checkPermutation(%v, %v) = %v, want = %v", s1, s2, res, want)
	}

}

// TODO Create reference for parameterised tests (check 'coding_challenges')
