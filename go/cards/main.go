package main

func main() {
	cards := newDeck()

	hand, remainingCards := deal(cards, 5)

	hand.print()
	remainingCards.print()

	fileName := "cards/cardsFile"
	cards.saveToFile(fileName)

	newDeck := readFromFile(fileName)
	newDeck.print()

	newDeck.shuffle()

	newDeck.print()
}