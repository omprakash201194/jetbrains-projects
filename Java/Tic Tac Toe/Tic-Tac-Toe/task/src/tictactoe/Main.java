package tictactoe;

import java.util.Scanner;

public class Main {
    public static final String GAME_NOT_FINISHED = "Game not finished";
    public static final String DRAW = "Draw";
    public static final String X_WINS = "X wins";
    public static final String O_WINS = "O wins";
    public static final String IMPOSSIBLE = "Impossible";
    public static final int SIZE = 3;
    public static final String ENTER_COORDINATES = "Enter the coordinates: ";
    public static final String COORDINATES_1_3 = "Coordinates should be from 1 to 3!";
    public static final String CELL_OCCUPIED = "This cell is occupied! Choose another one!";
    public static final String ENTER_NUMBERS = "You should enter numbers!";
    public static final String LEFT_EDGE = "| ";
    public static final String RIGHT_EDGE = "|\n";
    public static final String BORDER = "---------";
    public static final String X_ = "X ";
    public static final String O_ = "O ";
    public static final String EMPTY = " ";
    static int emptyPlaces = 9;
    static Scanner scanner = new Scanner(System.in);
    static Integer[][] board = {{-1, -1, -1},{-1, -1, -1},{-1, -1, -1}};

    public static void main(String[] args) {
        ticTacToe();
    }

    private static void ticTacToe() {
        String state;
        printBoard();
        int counter = 1;
        do {
            userInput(counter++ % 2);
            state = processState();
        }while (state.equals(GAME_NOT_FINISHED));
        System.out.println(state);
    }

    private static String processState() {
        String state;
        String rowWin = processRows();
        String columnWin = processColumns();
        String diagonalWin = processDiagonal();
        if (rowWin != null && columnWin != null && !rowWin.equals(columnWin)) {
            state = IMPOSSIBLE;
        } else if (rowWin != null) {
            state = rowWin;
        } else if (columnWin != null) {
            state = columnWin;
        } else if (diagonalWin != null) {
            state = diagonalWin;
        } else if (emptyPlaces > 0) {
            state = GAME_NOT_FINISHED;
        } else {
            state = DRAW;
        }
        return state;
    }

    private static void userInput(int value) {
        boolean marked = false;
        do {
            System.out.print(ENTER_COORDINATES);
            try {
                String i = scanner.next();
                int x = Integer.parseInt(i);
                String j = scanner.next();
                int y = Integer.parseInt(j);
                if (x > 3 || x < 1 || y > 3 || y < 1){
                    System.out.println(COORDINATES_1_3);
                    continue;
                }
                marked = markPosition(x, y, value);
                if (!marked){
                    System.out.println(CELL_OCCUPIED);
                }
            } catch (Exception e) {
                System.out.println(ENTER_NUMBERS);
            }

        }while (!marked);
        printBoard();
    }

    private static String processDiagonal() {
        String state = null;
        int sum = 0;
        for (int i = 0; i < SIZE; i++) {
            if (board[i][i] == -1) {
                sum = -1;
                break;
            }
            sum += board[i][i];
        }
        if (sum == 0) {
            state = O_WINS;
        }else if (sum == SIZE) {
            state = X_WINS;
        } else {
            sum = 0;
            for (int i = 0,j = SIZE - 1; i < SIZE && j >= 0;) {
                Integer value = board[i++][j--];
                if (value == -1) {
                    return null;
                }
                sum += value;
            }
            if (sum == 0) {
                state = O_WINS;
            }else if (sum == SIZE) {
                state = X_WINS;
            }
        }
        return state;
    }

    private static String processColumns() {
        String state = null;
        boolean xWon = false;
        boolean oWon = false;
        for (int j = 0; j < SIZE; j++) {
            int sum = 0;
            for (int i = 0; i < SIZE; i++) {
                if (board[i][j] == -1) {
                    sum = -1;
                    break;
                }
                sum += board[i][j];
            }
            if (sum == SIZE) {
                xWon = true;
                state = X_WINS;
            } else if (sum == 0) {
                oWon = true;
                state = O_WINS;
            }
        }
        if (xWon && oWon) {
            return IMPOSSIBLE;
        }
        return state;
    }

    private static String processRows() {
        String state = null;
        boolean xWon = false;
        boolean oWon = false;
        for (int i = 0; i < SIZE; i++) {
            int sum = 0;
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == -1) {
                    sum = -1;
                    break;
                }
                sum += board[i][j];
            }
            if (sum == SIZE) {
                state = X_WINS;
                xWon = true;
            } else if (sum == 0) {
                state = O_WINS;
                oWon = true;
            }
        }
        if (xWon && oWon) {
            return IMPOSSIBLE;
        }
        return state;
    }

    private static boolean markPosition(int x, int y, int value) {
        if(board[SIZE - y][x - 1] == -1) {
            board[SIZE - y][x - 1] = value;
            emptyPlaces--;
            return true;
        }
        return false;
    }

    private static void printBoard() {
        System.out.println(BORDER);
        for (int i = 0; i < SIZE; i++) {
            System.out.print(LEFT_EDGE);
            for (int j = 0; j < SIZE; j++) {
                int value = board[i][j];
                if(value == 1){
                    System.out.print(X_);
                } else if (value == 0) {
                    System.out.print(O_);
                } else {
                    System.out.print("_" + EMPTY);
                }
            }
            System.out.print(RIGHT_EDGE);
        }
        System.out.println(BORDER);
    }
}
