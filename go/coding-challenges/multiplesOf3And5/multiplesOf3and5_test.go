package main

import "testing"

func Test_multiplesOf3And5(t *testing.T) {
	type args struct {
		input int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		{"10 - PD1", args{10}, 23},
		{"100 - PD1", args{100}, 2318},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := multiplesOf3And5(tt.args.input); got != tt.want {
				t.Errorf("multiplesOf3And5() = %v, want %v", got, tt.want)
			}
		})
	}
}