package tictactoe;

import java.util.*;

public class Main {

    public static final String INPUT_COMMAND = "Input command:\t";
    public static final String BAD_PARAMETERS = "Bad Parameters!";
    public static final String EXIT = "exit";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputCommand;
        do {
            System.out.print(INPUT_COMMAND);
            inputCommand = scanner.nextLine();
            if(validateInputs(inputCommand)) {
                Board board = new Board();
                // Get players
                String[] players = inputCommand.split(" ");
                Player playerOne = PlayerFactory.getPlayer(PlayerLevel.valueOf(players[1].toUpperCase()), 1);
                Player playerTwo = PlayerFactory.getPlayer(PlayerLevel.valueOf(players[2].toUpperCase()), 0);

                // Start the game
                letsPlayTicTacToe(board, playerOne, playerTwo);
            } else if (!EXIT.equals(inputCommand)){
                System.out.println(BAD_PARAMETERS);
            }

        } while (!inputCommand.equals(EXIT));
    }

    private static void letsPlayTicTacToe(Board board, Player playerOne, Player playerTwo) {
        board.printBoard();
        Player currentChance = playerOne;
        GameStates state;
        do {
            currentChance.move(board, currentChance.getPlayerLevel());
            currentChance = currentChance == playerOne ? playerTwo : playerOne;
            board.printBoard();
            state = board.processState();
        } while(state == GameStates.GAME_NOT_FINISHED);
        System.out.println(state.getValue());
    }

    private static boolean validateInputs(String input) {
        String[] values = input.split(" ");
        return values.length == 3;
    }
}

interface Player {
    String ENTER_COORDINATES = "Enter the coordinates: ";
    String COORDINATES_1_3 = "Coordinates should be from 1 to 3!";
    String CELL_OCCUPIED = "This cell is occupied! Choose another one!";
    String ENTER_NUMBERS = "You should enter numbers!";
    void move(Board board, PlayerLevel level);
    PlayerLevel getPlayerLevel();
}

class Coordinates {
    private final int x;
    private final int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

abstract class AbstractPlayer implements Player {
    private final int choice;
    public static String MAKING_MOVE_LEVEL = "Making move level \"%s\"";
    private final PlayerLevel playerLevel;

    public AbstractPlayer(int choice, PlayerLevel playerLevel) {
        this.choice = choice;
        this.playerLevel = playerLevel;
    }

    public int getChoice() {
        return choice;
    }
    @Override
    public PlayerLevel getPlayerLevel() {
        return playerLevel;
    }

    public void move(Board board, PlayerLevel level) {
        if (level != PlayerLevel.USER) {
            System.out.println(String.format(MAKING_MOVE_LEVEL, level.toString().toLowerCase()));
        }
        this.makeMove(board);
    }
    protected abstract void makeMove(Board board);
}

class PlayerFactory {
    public static Player getPlayer(PlayerLevel playerLevel, int choice) {
        Player player;
        switch (playerLevel) {
            case USER:
                player = new UserPlayer(choice, PlayerLevel.USER);
                break;
            case EASY:
                player =  new EasyPlayer(choice, PlayerLevel.EASY);
                break;
            case MEDIUM:
                player =  new MediumPlayer(choice, PlayerLevel.MEDIUM);
                break;
            case HARD:
                player =  new HardPlayer(choice, PlayerLevel.HARD);
                break;
            default:
                player = null;
        }
        return player;
    }
}

class UserPlayer extends AbstractPlayer {

    public UserPlayer(int choice, PlayerLevel level) {
        super(choice, level);
    }

    @Override
    public void makeMove(Board board) {
        Coordinates userCoordinates = getUserCoordinates(board);
        board.markPosition(userCoordinates, getChoice());
    }
    private Coordinates getUserCoordinates(Board board) {
        Coordinates coordinates = null;
        Scanner scanner = new Scanner(System.in);
        boolean available = false;
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
                available = board.isCellAvailable(x, y);
                if (!available){
                    System.out.println(CELL_OCCUPIED);
                } else {
                    coordinates = new Coordinates(x, y);
                }
            } catch (Exception e) {
                System.out.println(ENTER_NUMBERS);
                scanner = new Scanner(System.in);
            }

        }while (!available);
        return coordinates;
    }
}

class EasyPlayer extends AbstractPlayer {

    static Random random = new Random(Board.SIZE);
    public EasyPlayer(int choice, PlayerLevel level) {
        super(choice, level);
    }

    @Override
    public void makeMove(Board board) {
        int x; int y;
        do {
            x = random.nextInt(Board.SIZE) + 1;
            y = random.nextInt(Board.SIZE) + 1;
        } while (!board.isCellAvailable(x, y));
        board.markPosition(new Coordinates(x, y), getChoice());
    }
}

class MediumPlayer extends EasyPlayer {

    public MediumPlayer(int choice, PlayerLevel level) {
        super(choice, level);
    }

    @Override
    public void makeMove(Board board) {
        Board tempBoard = new Board(board.getBoard());
        GameStates canWin = GameStates.values()[getChoice()];
        //Check for winning state
        Coordinates winningCoordinates = getDesiredCoordinates(tempBoard, canWin, getChoice());
        if (winningCoordinates == null) {
            //Check for opponent winning state
            tempBoard = new Board(board.getBoard());
            int choice = Math.abs(getChoice() - 1);
            Coordinates drawCoordinates = getDesiredCoordinates(tempBoard, GameStates.values()[choice], choice);
            // Make a random move
            if (drawCoordinates == null) {
                super.makeMove(board);
            } else {
                board.markPosition(drawCoordinates, getChoice());
            }
        } else {
            board.markPosition(winningCoordinates, getChoice());
        }
    }

    private Coordinates getDesiredCoordinates(Board tempBoard, GameStates desiredState, int choice) {
        for (Coordinates coordinates : tempBoard.getAvailableCellCoordinates()) {
            tempBoard.tryMarking(coordinates, choice);
            GameStates state = tempBoard.processState();
            if (desiredState == state) {
                tempBoard.unMarkPosition(coordinates);
                return coordinates;
            }
            tempBoard.unMarkPosition(coordinates);
        }
        return null;
    }
}

class HardPlayer extends MediumPlayer {
    public HardPlayer(int choice, PlayerLevel level) {
        super(choice, level);
    }
}

enum PlayerLevel {
    USER, EASY, MEDIUM, HARD
}

enum GameStates {
    O_WINS ("O wins"),
    X_WINS ("X wins"),
    GAME_NOT_FINISHED ("Game not finished"),
    DRAW ("Draw"),
    IMPOSSIBLE ("Impossible");

    private final String value;
    GameStates(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

class Board {
    public static final String LEFT_EDGE = "| ";
    public static final String RIGHT_EDGE = "|\n";
    public static final String BORDER = "---------";
    public static final String X_ = "X ";
    public static final String O_ = "O ";
    public static final String EMPTY = "_ ";
    public static final int SIZE = 3;

    private final Integer[][] board;
    private final Set<Coordinates> availableCellCoordinates = new HashSet<>();
    Board() {
        this.board = new Integer[][]{{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}};
        for (int i = 1; i <= SIZE; i++) {
            for (int j = 1; j <= SIZE; j++) {
                availableCellCoordinates.add(new Coordinates(i,j));
            }
        }
    }

    Board(Integer[][] board) {
        this.board = board;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == -1) {
                    availableCellCoordinates.add(new Coordinates(j+1, SIZE - i));
                }
            }
        }
    }

    public Set<Coordinates> getAvailableCellCoordinates() {
        return availableCellCoordinates;
    }

    public Integer[][] getBoard() {
        return board;
    }

    public void printBoard() {
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
                    System.out.print(EMPTY);
                }
            }
            System.out.print(RIGHT_EDGE);
        }
        System.out.println(BORDER);
    }

    public void markPosition(Coordinates coordinates, int value) {
        if(isCellAvailable(coordinates.getX(), coordinates.getY())) {
            board[SIZE - coordinates.getY()][coordinates.getX() - 1] = value;
            availableCellCoordinates.remove(coordinates);
        }
    }

    public void tryMarking(Coordinates coordinates, int value) {
        if(isCellAvailable(coordinates.getX(), coordinates.getY())) {
            board[SIZE - coordinates.getY()][coordinates.getX() - 1] = value;
        }
    }

    public boolean isCellAvailable(int x, int y) {
        return board[SIZE - y][x - 1] == -1;
    }

    public GameStates processState() {
        GameStates state;
        GameStates rowWin = processRows();
        GameStates columnWin = processColumns();
        GameStates diagonalWin = processDiagonal();
        if (rowWin != null && columnWin != null && !rowWin.equals(columnWin)) {
            state = GameStates.IMPOSSIBLE;
        } else if (rowWin != null) {
            state = rowWin;
        } else if (columnWin != null) {
            state = columnWin;
        } else if (diagonalWin != null) {
            state = diagonalWin;
        } else if (availableCellCoordinates.size() > 0) {
            state = GameStates.GAME_NOT_FINISHED;
        } else {
            state = GameStates.DRAW;
        }
        return state;
    }

    private GameStates processDiagonal() {
        GameStates state = null;
        int sum = 0;
        for (int i = 0; i < SIZE; i++) {
            if (board[i][i] == -1) {
                sum = -1;
                break;
            }
            sum += board[i][i];
        }
        if (sum == 0) {
            state = GameStates.O_WINS;
        }else if (sum == SIZE) {
            state = GameStates.X_WINS;
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
                state = GameStates.O_WINS;
            }else if (sum == SIZE) {
                state = GameStates.X_WINS;
            }
        }
        return state;
    }

    private GameStates processColumns() {
        GameStates state = null;
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
                state = GameStates.X_WINS;
            } else if (sum == 0) {
                oWon = true;
                state = GameStates.O_WINS;
            }
        }
        if (xWon && oWon) {
            return GameStates.IMPOSSIBLE;
        }
        return state;
    }

    private GameStates processRows() {
        GameStates state = null;
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
                state = GameStates.X_WINS;
                xWon = true;
            } else if (sum == 0) {
                state = GameStates.O_WINS;
                oWon = true;
            }
        }
        if (xWon && oWon) {
            return GameStates.IMPOSSIBLE;
        }
        return state;
    }

    public void unMarkPosition(Coordinates coordinates) {
        board[SIZE - coordinates.getY()][coordinates.getX() - 1] = -1;
    }
}
