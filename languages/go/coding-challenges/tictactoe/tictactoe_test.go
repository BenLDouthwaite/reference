package main

import "testing"

func Test_tictactoe(t *testing.T) {
	type args struct {
		boardSize int
		moves []int
		scheduled bool
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		{"s0. Player 1 wins", args{boardSize:3, moves:[]int{1,2,3,4,5,6,7}, scheduled:true}, 1},
		{"s0. Player 2 wins", args{boardSize:3, moves:[]int{1,2,3,5,4,8}, scheduled:true}, 2},
		{"s0. Draw", args{boardSize:3, moves:[]int{1,2,4,5,8,7,3,6,8}, scheduled:true}, 0},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := tictactoe(tt.args.boardSize, tt.args.moves, tt.args.scheduled); got != tt.want {
				t.Errorf("tictactoe() = %v, want %v", got, tt.want)
			}
		})
	}
}