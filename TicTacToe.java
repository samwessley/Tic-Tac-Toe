import java.util.*;

public class TicTacToe {

    char[] board = new char[9];
    boolean gameOver = false;
    boolean playerTurn = false;

    public static void main(String[] args) {

        TicTacToe newGame = new TicTacToe();
        newGame.playGame();
    }

    public void playGame() {

        // Print game instructions
        System.out.println();
        System.out.println("Let's play Tic Tac Toe!");
        System.out.println("Each space on the board has a number:");
        System.out.println();
        System.out.println(" 1 | 2 | 3 ");
        System.out.println("---+---+---");
        System.out.println(" 4 | 5 | 6 ");
        System.out.println("---+---+---");
        System.out.println(" 7 | 8 | 9 ");
        System.out.println();
        
        // While the game is still ongoing, alternate between players and prompt them to place their mark on the board
        while (!gameOver) {
            if (!playerTurn) {
                System.out.print("It's Player 1's turn. Player 1 is X's. Player 1: Enter a number 1 through 9 to place your X on the board: ");
                playTurn(1);
                printBoard();
                
                if (playerHasWon(1)) {
                    System.out.println("Player 1 Wins!");
                    gameOver = true;
                    System.exit(0);
                } else {
                    checkForDraw();
                    playerTurn = !playerTurn;
                }
            } else {
                System.out.print("It's Player 2's turn. Player 2 is O's. Player 2: Enter a number 1 through 9 to place your O on the board: ");
                playTurn(2);
                printBoard();

                if (playerHasWon(2)) {
                    System.out.println("Player 2 Wins!");
                    gameOver = true;
                    System.exit(0);
                } else {
                    checkForDraw();
                    playerTurn = !playerTurn;
                }
            }
        }
    }

    private void playTurn(int player) {
        char c;
        Scanner scanner = new Scanner(System.in);
        boolean markPlaced = false;

        // Set the char to the player we're checking for
        if (player == 1) {
            c = 'X';
        } else {
            c = 'O';
        }
        
        while (!markPlaced) {
            int inputInt = scanner.nextInt();

            if (board[inputInt - 1] == '\u0000') {
                board[inputInt - 1] = c;
                markPlaced = true;
            } else {
                System.out.println("This spot is taken. Try another spot:");
            }
        }        
    }

    private String getChar(int index) {
        if (board[index] == '\u0000') {
            return " ";
        } else {
            String s = Character.toString(board[index]);  
            return s;
        }
    }

    private void printBoard() {
        System.out.println();
        System.out.println(" " + getChar(0) + " | " + getChar(1) + " | " + getChar(2) + " ");
        System.out.println("---+---+---");
        System.out.println(" " + getChar(3) + " | " + getChar(4) + " | " + getChar(5) + " ");
        System.out.println("---+---+---");
        System.out.println(" " + getChar(6) + " | " + getChar(7) + " | " + getChar(8) + " ");
        System.out.println();
    }

    private boolean playerHasWon(int player) {

        int[][] possibleWins = {{0,3,6},{1,4,7},{2,5,8},{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6}};
        char c;

        // Set the char to the player we're checking for
        if (player == 1) {
            c = 'X';
        } else {
            c = 'O';
        }

        // Loop through the possible winning scenarios for this player and return true if any are true
        for(int[] possibleWin : possibleWins) {
            if (board[possibleWin[0]] == c && board[possibleWin[1]] == c && board[possibleWin[2]] == c) {
                return true;
            }
        }
        
        // If none have returned true, this player hasn't won yet
        return false;
    }

    private void checkForDraw() {
        if (draw()) {
            System.out.println("It's a draw!");
            gameOver = true;
            System.exit(0);
        }
    }

    private boolean draw() {
        boolean draw = true;

        for (char boardPosition : board) {
            if (boardPosition == '\u0000') {
                draw = false;
            }
        }

        return draw;
    }
}