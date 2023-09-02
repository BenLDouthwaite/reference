package evenFibonacciNumbers

import "testing"

func Test_evenFibonacciNumbers(t *testing.T) {
	type args struct {
		input int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		{"10_PD1", args{10}, 10}, // 10 is the lower bound
		{"100_PD2", args{100}, 44},
		{"100_PD2", args{100}, 44},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := evenFibonacciNumbers(tt.args.input); got != tt.want {
				t.Errorf("evenFibonacciNumbers() = %v, want %v", got, tt.want)
			}
		})
	}
}