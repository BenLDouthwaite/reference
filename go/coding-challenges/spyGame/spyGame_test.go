package spyGame

import "testing"

func Test_spyGame(t *testing.T) {
	type args struct {
		arr []int
	}
	tests := []struct {
		name string
		args args
		want bool
	}{
		{"s0", args{arr:[]int{0}}, false},
		{"s1", args{arr:[]int{1}}, false},
		{"s2", args{arr:[]int{1,2}}, false},
		{"s3", args{arr:[]int{0,0,7}}, true},
		{"s4", args{arr:[]int{0,0,7,1}}, true},
		{"s5", args{arr:[]int{0,1,0,2,7}}, true},
		{"s6", args{arr:[]int{0,0,0,7}}, true},
		{"s7", args{arr:[]int{0,1,1,1,1,1,1,0,2,2,2,2,2,2,2,7}}, true},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := spyGame(tt.args.arr); got != tt.want {
				t.Errorf("spyGame() = %v, want %v", got, tt.want)
			}
		})
	}
}