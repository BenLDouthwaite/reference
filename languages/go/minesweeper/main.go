package main

import (
  //"bufio"
  "fmt"
  //"os"
)

type square struct {
  bomb bool
  adjBombs byte
}

func main() {
  fmt.Println("Test")

  //arr := make([]string, 0)
  //scanner := bufio.NewScanner(os.Stdin)

  boardSize := 7;

  board := make([][]square, boardSize)
  for i := 0; i < boardSize; i++ {
    boardRow := make([]square, boardSize)
    board[i] = boardRow
  }

  printBoard(board)

  // Main Game Loop
  //for {
  //  fmt.Print("Enter Text: ")
  //  // Scans a line from Stdin

  //  scanner.Scan()

  //  // Store text
  //  text := scanner.Text()

  //  if len(text) != 0  {
  //    fmt.Println("Text:", text)
  //    arr = append(arr, text)
  //  } else {
  //    break
  //  }
  //}
}

func printBoard(board [][]square) {
  bs := len(board)

  fmt.Print("┌")
  for i := 0; i < bs -1; i++ {
    fmt.Print("─┬")
    fmt.Print()
  }
  fmt.Println("─┐")

  for i := 0; i < len(board); i++ {
    for j := 0; j < len(board); j++ {
      fmt.Print("│")
      fmt.Print(board[i][j].adjBombs)
    }
    fmt.Println("│")
 
    if (i != bs -1) {
      fmt.Print("├")
      for i := 0; i < bs -1; i++ {
        fmt.Print("─┼")
        fmt.Print()
      }
      fmt.Println("─┤")
    }
  }

  fmt.Print("└")
  for i := 0; i < bs -1; i++ {
    fmt.Print("─┴")
    fmt.Print()
  }
  fmt.Println("─┘")
}
