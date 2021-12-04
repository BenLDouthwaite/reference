package adventofcode2021.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GiantSquid {

    public static void main(String[] args) throws IOException {
        Path puzzleInputPath = Path.of("./src/main/java/adventofcode2021/day4/puzzleInput.txt");
        List<String> input = Files.readAllLines(puzzleInputPath);

        int result1 = bingo(input);
        System.out.println("Part 1: Result for puzzle input: " + result1);

//        int result2 = binaryDiagnosticLifeSupport(input);
//        System.out.println("Part 2: Result for puzzle input: " + result2);
    }

    public static int bingoLast(List<String> input) {
        return 0;
    }

    public static int bingo(List<String> input) {

        List<Integer> numberDraw = Stream.of(input.get(0)
                        .split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int bingoBoardsCount = (input.size() -1) / 6;
        int[][][] boards = parseBingoBoards(input, bingoBoardsCount);

        int winningBoardIndex = -1;
        int finalDrawnNumber = -1;
        for (Integer drawnNumber: numberDraw) {
            markDrawnNumber(boards, drawnNumber);

            Optional<Integer> winner = checkWinner(boards);

            if (winner.isPresent()) {
                winningBoardIndex = winner.get();
                finalDrawnNumber = drawnNumber;
                break;
            }

//            printBoards(boards); // Available to debug if needed.
        }

        if (winningBoardIndex != -1) {
            int[][] winningBoard = boards[winningBoardIndex];
            return calculateAnswer(winningBoard, finalDrawnNumber);
        } else {
            throw new IllegalStateException("Game must have a winner. Logic error");
        }
    }

    private static Optional<Integer> checkWinner(int[][][] boards) {
        for (int i = 0; i < boards.length; i++) {
            int [][] board = boards[i];
            row:
            for (int j = 0; j < 5; j++) {
                int [] row = board[j];
                for (int k = 0; k < 5; k++) {
                    if (row[k] > 0) {
                        continue row; // found an unmarked value, row is not a winner
                    }
                }
                return Optional.of(i); // Index of winning board
            }
        }

        // TODO Check Column winners too.
            // Apparently not needed for part 1!

        return Optional.empty();
    }

    private static void markDrawnNumber(int[][][] boards, Integer drawnNumber) {
        for (int i = 0; i < boards.length; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (boards[i][j][k] == drawnNumber) {
                        boards[i][j][k] *= -1; // Store as negative, to flag as 'ticked'
                    }
                }
            }
        }
    }

    private static int[][][] parseBingoBoards(List<String> input, int bingoBoardsCount) {
        int [][][] bingoBoards = new int[bingoBoardsCount][5][5];
        for (int i = 0; i < bingoBoardsCount; i++) {
            int boardStartIndex = (i * 6) + 2;
            int[][] board = new int[5][5];
            for (int j = 0; j < 5; j ++) {
                int rowNum = boardStartIndex + j;
                String[] rowStringNums = input.get(rowNum).trim()
                        .replaceAll(" +", " ")
                        .split(" ");
                int[] nums = Stream.of(rowStringNums)
                        .mapToInt(Integer::parseInt)
                        .toArray();
                board[j] = nums;
            }
            bingoBoards[i] = board;
        }
        return bingoBoards;
    }

    private static void printBoards(int[][][] boards) {
        for (int i = 0; i < boards.length; i++) {
            printBoard(boards[i]);
        }
    }

    private static void printBoard(int[][] board) {
        for (int j = 0; j < 5; j++) {
            for (int k = 0; k < 5; k++) {
                System.out.print(board[j][k]);
                if (k < 4) {
                    System.out.print(",");
                }
            }
            System.out.print("\n");
        }
        System.out.println();
    }

    private static int calculateAnswer(int[][] winningBoard, int finalDrawnNumber) {
        printBoard(winningBoard);

        int totalUnmarked = 0;
        for (int j = 0; j < 5; j++) {
            for (int k = 0; k < 5; k++) {
                if (winningBoard[j][k] > 0) {
                    totalUnmarked += winningBoard[j][k];
                }
            }
        }

        return totalUnmarked * finalDrawnNumber;
    }
}
