package main

import "testing"

func Test_robotMovement_N(t *testing.T) {
	inputString := "N"
	expected := 1
	actual:=robotMovement(inputString)
	if actual != expected {
		t.Errorf("TF %d %d ", expected, actual)
	}
}

func Test_robotMovement_NN(t *testing.T) {
	inputString := "NN"
	expected := 2
	actual:=robotMovement(inputString)
	if actual != expected {
		t.Errorf("TF %d %d ", expected, actual)
	}
}