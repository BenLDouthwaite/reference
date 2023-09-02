package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
)

const draw = 0;

func main() {
	tictactoe(5, nil, false)
}

func tictactoe(boardSize int, moves []int, scheduled bool) int{

	boardState := make([][]int, boardSize)
	for i := range boardState {
		boardState[i] = make([]int, boardSize)
	}

	printBoard(boardState)

	playerNumber := 1

	scanner := bufio.NewScanner(os.Stdin)

	for x := 0; x < boardSize * boardSize; x++ {

		var userPosition int
		if scheduled {
			userPosition = moves[x] - 1
		} else {
			userPosition = readUserPosition(scanner, playerNumber, boardState)
		}

		setUserPosition(boardState, userPosition, playerNumber)

		printBoard(boardState)

		gameWinner := checkWinCondition(boardState, boardSize, userPosition, playerNumber)
		if gameWinner > -1 {
			fmt.Printf("Game over. Winner: %d\n", gameWinner)
			return gameWinner
		}

		playerNumber = (playerNumber % 2) + 1
	}

	return draw
}

func setUserPosition(state [][]int, position int, number int) {
	state[position / len(state)][position % len(state)] = number
}

func checkWinCondition(state [][]int, boardSize int, position int, playerNumber int) int {
	x, y := getBoardCoordinatesFromPosition(position, boardSize)

	// Check horizontal
	for i := 0; i < boardSize; i++ {
		if state[x][i] != playerNumber {
			break
		}
		if i == boardSize-1 {
			return playerNumber
		}
	}

	// Check vertical
	for i := 0; i < boardSize; i++ {
		if state[i][y] != playerNumber {
			break
		}
		if i == boardSize-1 {
			return playerNumber
		}
	}

	// Check top left to bottom right diagonal
	if x == y {
		for i := 0; i < boardSize; i++ {
			if state[i][i] != playerNumber {
				break
			}
			if i == boardSize-1 {
				return playerNumber
			}
		}
	}

	// Check top right to bottom left diagonal
	if x+y == boardSize-1 {
		for i := 0; i < boardSize; i++ {
			if state[(boardSize-i)-1][i] != playerNumber {
				break
			}
			if i == boardSize-1 {
				return playerNumber
			}
		}
	}

	return -1
}

func getBoardCoordinatesFromPosition(position int, boardSize int) (int, int) {
	x, y := position/boardSize, position%boardSize
	return x, y
}

func readUserPosition(scanner *bufio.Scanner, userTurn int, board [][]int) int {
	maxValue := len(board) * len(board)
	for {
		fmt.Printf("Player %d: select an available square between 1 and %d\n", userTurn, maxValue)
		scanner.Scan()
		space := scanner.Text()
		spaceInt, err := strconv.Atoi(space)
		if err == nil && spaceInt >= 1 && spaceInt <= maxValue {
			spaceInt--  // Map fom 1 index display to 0 index for access
			x, y := getBoardCoordinatesFromPosition(spaceInt, len(board))
			if board[x][y] == 0 {
				return spaceInt
			}
		}
	}
}

func printBoard(board [][]int) {

	posSize := intLength(len(board) * len(board))

	for x := 0; x < len(board); x++ {
		fmt.Print("|")
		for y := 0; y < len(board); y++ {
			index := x * len(board) + y
			val := board[x][y]
			if val == 1 {
				printCharacter("x", posSize)
			} else if val == 2 {
				printCharacter("o", posSize)
			} else {
				printPosition(index + 1, posSize)
			}
			fmt.Print("|")
		}
		fmt.Println()
	}
}

func printCharacter(char string, posSize int) {
	if len(char) < posSize {
		for x := 0; x < posSize - len(char); x++ {
			fmt.Printf(" ")
		}
	}
	fmt.Printf("%s", char)
}

func printPosition(value int, posSize int) {
	if intLength(value) < posSize {
		for x := 0; x < posSize - intLength(value); x++ {
			fmt.Printf(" ")
		}
	}
	fmt.Printf("%d", value)
}

func intLength(val int) int {
	switch {
	case val >= 0   && val < 10:   return 1
	case val >= 10  && val < 100:  return 2
	case val >= 100 && val < 1000: return 3
	default:
		return int(math.Log10(float64(val))) + 1
	}
}