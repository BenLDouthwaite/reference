package newyearchaos

import "testing"

func Test_minimumBribes(t *testing.T) {
	type args struct {
		q []int32
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		{"No swap", args{[]int32{1, 2, 3}}, 0},
		{"1 swap", args{[]int32{1, 3, 2}}, 1},
		{"2 swaps", args{[]int32{3, 1, 2}}, 2},
		{"3 swaps", args{[]int32{3, 2, 1}}, 3},
		// {"Too many swaps", args{[]int32{4, 1, 2, 3}}, -1},
		// {"Sample input 0.1", args{[]int32{2, 1, 5, 3, 4}}, 3},
		// {"Sample input 0.2", args{[]int32{2, 5, 1, 3, 4}}, -1},
		// {"Sample input 1.1", args{[]int32{5, 1, 2, 3, 7, 8, 6, 4}}, -1},
		// {"Sample input 1.2", args{[]int32{1, 2, 5, 3, 7, 8, 6, 4}}, 7},
		// {"Sample input 2", args{[]int32{1, 2, 5, 3, 4, 7, 8, 6}}, 4},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimumBribes(tt.args.q); got != tt.want {
				t.Errorf("Minimum Swaps() = %v, want %v", got, tt.want)
			}
		})
	}
}
