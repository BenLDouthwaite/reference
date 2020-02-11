package main

import "testing"

func Test_robotMovement(t *testing.T) {
	type args struct {
		inputString string
	}
	var tests = []struct {
		name string
		args args
		want int
	}{
		{"North 1", args{inputString: "N" }  , 1},
		{"North 2", args{inputString: "NN" }  , 2},
		{"North 3", args{inputString: "NNN" }  , 3},
		{"South 1", args{inputString: "S" }  , 1},
		{"South 2", args{inputString: "SS" }  , 2},
		{"South 3", args{inputString: "SSS" }  , 3},
		{"East 1", args{inputString: "E" }  , 1},
		{"East 2", args{inputString: "EE" }  , 2},
		{"East 3", args{inputString: "EEE" }  , 3},
		{"West 1", args{inputString: "W" }  , 1},
		{"West 2", args{inputString: "WW" }  , 2},
		{"West 3", args{inputString: "WWW" }  , 3},
		{"Far North East", args{inputString: "NENENENENE" }  , 10},
		{"Far South East", args{inputString: "SESESESESE" }  , 10},
		{"Circle 1 each dir", args{inputString: "NESW" }  , 0},
		{"Circle 3 each dir", args{inputString: "NNNEEESSSWWW" }  , 0},


	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := robotMovement(tt.args.inputString); got != tt.want {
				t.Errorf("robotMovement() = %v, want %v", got, tt.want)
			}
		})
	}
}