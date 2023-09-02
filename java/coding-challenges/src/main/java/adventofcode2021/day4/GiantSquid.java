package adventofcode2021.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GiantSquid {

    public static void main(String[] args) throws IOException {
        Path puzzleInputPath = Path.of("./src/main/java/adventofcode2021/day4/puzzleInput.txt");
        List<String> input = Files.readAllLines(puzzleInputPath);

        // TODO Fix checkWinners function to make part 1 pass test
//        int result1 = bingo(input);
//        System.out.println("Part 1: Result for puzzle input: " + result1);

        int result2 = bingoLast(input);
        System.out.println("Part 2: Result for puzzle input: " + result2);
    }

    public static int bingoLast(List<String> input) {
        List<Integer> numberDraw = Stream.of(input.get(0)
                        .split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int bingoBoardsCount = (input.size() -1) / 6;
        int[][][] boards = parseBingoBoards(input, bingoBoardsCount);

        int finalDrawnNumber = -1;

        List<Integer> winners = new ArrayList<>();

        for (Integer drawnNumber: numberDraw) {
            markDrawnNumber(boards, drawnNumber);

            Optional<List<Integer>> newWinners = checkWinners(boards, winners);

            if (newWinners.isPresent()) {
                List<Integer> newWinnersValues = newWinners.get();
                winners.addAll(newWinnersValues);

                if (winners.size() == bingoBoardsCount) {
                    finalDrawnNumber = drawnNumber;
                    break;
                }
            }

//            printBoards(boards); // Available to debug if needed.
        }

        if (winners.size() == bingoBoardsCount) {
            int lastWinnerIndex = winners.get(winners.size() - 1);
            int[][] winningBoard = boards[lastWinnerIndex];
            return calculateAnswer(winningBoard, finalDrawnNumber);
        } else {
            throw new IllegalStateException("Game must have a winner. Logic error");
        }
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

            // TODO Get this to work again, somehow. Probably have two methods.
            Optional<List<Integer>> winner = checkWinners(boards, new ArrayList<>());

            if (winner.isPresent()) {
//                winningBoardIndex = winner.get();
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

    private static void markDrawnNumber(int[][][] boards, Integer drawnNumber) {
        for (int i = 0; i < boards.length; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (boards[i][j][k] == drawnNumber) {
                        boards[i][j][k] = (boards[i][j][k] * -1) - 1; // Store as negative, to flag as 'ticked'. Subtract 1 is to handle 0 case.
                    }
                }
            }
        }
    }

    private static Optional<List<Integer>> checkWinners(int[][][] boards, List<Integer> winners) {

        List<Integer> newWinners = new ArrayList<>();
        board:
        for (int i = 0; i < boards.length; i++) {

            if (winners.contains(i)) {
                continue; // Board is already a winner, don't need to check again
            }

            int [][] board = boards[i];

            row:
            for (int j = 0; j < 5; j++) {
                int [] row = board[j];
                for (int k = 0; k < 5; k++) {
                    if (row[k] >= 0) {
                        continue row; // found an unmarked value, row is not a winner
                    }
                }
                newWinners.add(i);
                continue board; // No need to check column too.
            }

            column:
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (board[k][j] >= 0) {
                        continue column; // found an unmarked value, column is not a winner
                    }
                }
                newWinners.add(i);
            }

        }

        if (newWinners.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(newWinners);
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
