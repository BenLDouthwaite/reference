package main

import (
  "bufio"
  "fmt"
  "math/rand"
  "os"
  "time"
  "strconv"
  "strings"
)

type square struct {
  bomb bool
  adjBombs byte
  visible bool
}

func main() {

  scanner := bufio.NewScanner(os.Stdin)

  boardSize := 7;

  // TODO Extract a board type, with methods (using receivers)
  board := generateBoard(boardSize)

  printBoard(board)

  // Main Game Loop
  for {
    // TODO Convert input to letter and digit based, and include in print
    fmt.Print("Enter selected square : ")
    // Scans a line from Stdin

    scanner.Scan()

    // Store text
    text := scanner.Text()

    if len(text) != 2 {
      continue
    }
    // TODO Actually validate the input

    // Take lowercase letter, get rune int value, offset to A=1, then minus 1 for 0 based array indexing
    sr := int(strings.ToUpper(text)[0]) - 64 - 1
    sc, _ := strconv.Atoi(text[1:2])
    guess(board, sr, sc)

    printBoard(board)
  }
}

func guess(board [][]square, sr int, sc int) {

  sq := &board[sr][sc]
  sq.visible = true

  if sq.bomb {
    printBoard(board)
    fmt.Println("Boom goes the dynamite. Game Over")
    os.Exit(1)
  }

  if sq.adjBombs == 0 {

    // Now it gets interesting, need to expand out. Try DFS
    visitedSquares := make([][]bool, len(board))
    for i := range visitedSquares {
      visitedSquares[i] = make([]bool, len(board))
    }
    clearEmpty(board, sr, sc, visitedSquares)
  }

  gameWon := checkWin(board)
  if gameWon {
    printBoard(board)
    fmt.Println("Congratulations. All squared (except bombs) revealed")
    os.Exit(1)
  }
}

func checkWin(board [][]square) bool {
  for i := 0; i < len(board); i++ {
    for j := 0; j < len(board[0]); j++ {
      if !board[i][j].visible && !board[i][j].bomb {
        return false
      }
    }
  }
  return true
}

func clearEmpty(board [][]square, r int, c int, vs [][]bool) {
  if (vs[r][c]) {
    return
  }

  vs[r][c] = true

  sq := &board[r][c]
  sq.visible = true

  if sq.adjBombs != 0 {
    return
  }

  boardSize := len(board)
  if r > 0 {
    clearEmpty(board,r - 1,c, vs)
  }
  if r > 0 && c + 1 < boardSize {
    clearEmpty(board,r - 1,c + 1, vs)
  }
  if c + 1 < boardSize {
    clearEmpty(board,r,c + 1, vs)
  }
  if r + 1 < boardSize && c + 1 < boardSize {
    clearEmpty(board,r + 1,c + 1, vs)
  }
  if r + 1 < boardSize {
    clearEmpty(board,r + 1,c, vs)
  }
  if r + 1 < boardSize && c > 0 {
    clearEmpty(board,r + 1,c - 1, vs)
  }
  if c > 0 {
    clearEmpty(board,r,c - 1, vs)
  }
  if r > 0 && c > 0 {
    clearEmpty(board,r - 1,c - 1, vs)
  }

}

func generateBoard(boardSize int) [][]square {
  board := make([][]square, boardSize)
  // Initialise empty board
  for i := 0; i < boardSize; i++ {
    boardRow := make([]square, boardSize)
    board[i] = boardRow
  }

  // Generate bomb positions randomly
  rand.Seed(time.Now().UnixNano())
  for i := 0; i < 3; i++ {
    r := rand.Intn(boardSize)
    c := rand.Intn(boardSize)

    sq := &board[r][c]
    if sq.bomb {
      // Hack to avoid duplicates
      // Warning: due to hack, if number of bombs set too high can loop forever
      i--
      continue
    }

    sq.bomb = true

    // Clockwise from 12 o'clock 
    if r > 0 {
      board[r - 1][c].adjBombs++
    }
    if r > 0 && c + 1 < boardSize {
      board[r - 1][c + 1].adjBombs++
    }
    if c + 1 < boardSize {
      board[r][c + 1].adjBombs++
    }
    if r + 1 < boardSize && c + 1 < boardSize {
      board[r + 1][c + 1].adjBombs++
    }
    if r + 1 < boardSize {
      board[r + 1][c].adjBombs++
    }
    if r + 1 < boardSize && c > 0 {
      board[r + 1][c - 1].adjBombs++
    }
    if c > 0 {
      board[r][c - 1].adjBombs++
    }
    if r > 0 && c > 0 {
      board[r - 1][c - 1].adjBombs++
    }

  }
  return board
}

func printBoard(board [][]square) {
  bs := len(board)

  fmt.Println()
  fmt.Print(" ")
  for i := 0; i < bs; i++ {
    fmt.Printf(" %v", i)
    fmt.Print()
  }
  fmt.Println()

  fmt.Print(" ┌")
  for i := 0; i < bs -1; i++ {
    fmt.Print("─┬")
    fmt.Print()
  }
  fmt.Println("─┐")

  for i := 0; i < len(board); i++ {

    fmt.Printf("%v", strings.ToLower(string('A' + i)))

    for j := 0; j < len(board); j++ {
      fmt.Print("│")

      // Printing character logic (the rest is just the board)
      sq := board[i][j]
      if sq.visible {
        if sq.bomb {
            fmt.Print("*")
        } else {
          fmt.Print(board[i][j].adjBombs)
        }
      } else {
        fmt.Print(" ")
      }
    }
    fmt.Println("│")

    if (i != bs -1) {
      fmt.Print(" ├")
      for i := 0; i < bs -1; i++ {
        fmt.Print("─┼")
        fmt.Print()
      }
      fmt.Println("─┤")
    }
  }

  fmt.Print(" └")
  for i := 0; i < bs -1; i++ {
    fmt.Print("─┴")
    fmt.Print()
  }
  fmt.Println("─┘")
}
