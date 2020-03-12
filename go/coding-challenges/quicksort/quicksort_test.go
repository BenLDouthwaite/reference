package quicksort

import (
	"reflect"
	"testing"
)

func Test_quicksort(t *testing.T) {
	type args struct {
		input []int
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		{"t1_1-5", args{[]int{5,4,3,2,1}}, []int{1,2,3,4,5}},
		{"t1", args{[]int{31,41,59,26,53,58,97,93,23,84}},[]int{23,26,31,41,53,58,59,84,93,97}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := quicksort(tt.args.input); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("quicksort() = %v, want %v", got, tt.want)
			}
		})
	}
}