package sgh;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TicTacToe {

    public enum Result { NOT_FINISHED, NO_WINNER, X_WON, O_WON }

    public static Result checkBoard(String boardFileName) {
        int[][] board = new int[3][3];
        try {
            File boardFile = new File(boardFileName);
            System.out.println(boardFile.getAbsolutePath());
            Scanner scan = new Scanner(boardFile);
            int rowCount = 0;
            while (scan.hasNextLine()) {
                String [] marks = scan.nextLine().split(";");
                int columnCount =0;
                for (String mark: marks) {
                    if (mark.equals("x")) {
                        board[rowCount][columnCount] = 1;
                        columnCount++;
                    } else if (mark.equals("o")) {
                        board[rowCount][columnCount] = -1;
                        columnCount++;
                    } else {
                        board[rowCount][columnCount] = 0;
                        columnCount++;
                    }
                }
                rowCount++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        if (checkRows(board) == 1 || checkColumns(board) == 1|| checkDiagonals(board) == 1) {
            return Result.X_WON;
        } else if (checkRows(board) == -1 || checkColumns(board) == -1 || checkDiagonals(board) == -1) {
            return Result.O_WON;
        } else if (checkForEmpty(board)) {
            return Result.NOT_FINISHED;
        } else {
            return Result.NO_WINNER;
        }
    }

    public static int checkRows(int[][] board) {
        for (int mark : new int[]{-1, 1}) {
            for (int i = 0; i < 3; i++) {
                if (board[i][0] == mark && board[i][1] == mark && board[i][2] == mark) {
                    return mark;
                }
            }
        }
        return 0;
    }

    public static int checkColumns(int[][] board) {
        for (int mark : new int[]{-1, 1}) {
            for (int i = 0; i < 3; i++) {
                if (board[0][i] == mark && board[1][i] == mark && board[2][i] == mark) {
                    return mark;
                }
            }
        }
        return 0;
    }

    public static int checkDiagonals(int[][] board) {
        for (int mark : new int[]{-1, 1}) {
            if (board[0][0] == mark && board[1][1] == mark && board[2][2] == mark) {
                return mark;
            } else if (board[0][2] == mark && board[1][1] == mark && board[2][0] == mark) {
                return mark;
            }
        }
        return 0;
    }

    public static boolean checkForEmpty(int[][] board) {
        for (int[] row : board) {
            for (int mark : row) {
                if (mark == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Result res = checkBoard("boards/tick0.csv");
        System.out.println(res);
    }

}
