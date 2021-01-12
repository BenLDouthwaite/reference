package main

import (
	"os"
	"testing"
)

func TestNewDeck(t *testing.T) {

	d := newDeck()

	if len(d) != 16 {
		t.Errorf("Expected len 16, but got %v", len(d))
	}

	expectedFirstCard := "Ace of Spades"
	if d[0] != expectedFirstCard {
		t.Errorf("Expected %v, but got %v", expectedFirstCard, d[0])
	}

	expectedLastCard := "Four of Clubs"
	if d[len(d) - 1] != expectedLastCard {
		t.Errorf("Expected %v, but got %v", expectedLastCard, d[len(d) - 1])
	}
}

func TestSaveToFileAndReadFromFile(t *testing.T) {

	fileName := "_testDeckFile"

	os.Remove(fileName)

	d := newDeck()
	d.saveToFile(fileName)

	loadedDeck := readFromFile(fileName)

	if len(loadedDeck) != 16 {
		t.Errorf("Expected %v, but got %v", 16, len(loadedDeck))
	}
}
