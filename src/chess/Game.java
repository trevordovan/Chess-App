package chess;

import java.util.Scanner;

import chess.enums.Color;
import chess.utils.Utils;

/**
 * The `Game` class represents a game of chess.
 */
public class Game
{
    /**
     * The gameboard.
     */
    private GameBoard board;

    /**
     * Creates a new game of chess.
     */
    public Game()
    {
        board = new GameBoard();
    }

    /**
     * Allows players to take turns making moves until the game ends.
     * Prompts players for input and makes moves on the game board
     * accordingly. Checks for check and checkmate each move. Ends 
     * the game when a player is checkmated is reached.
     */
    public void play() {
        Scanner scanner = new Scanner(System.in);
        Color currentPlayer = Color.WHITE;
    
        while (true) {
            board.printBoard();
            System.out.print(Utils.capitalize(currentPlayer.toString()) + "'s move: ");

            String input;
            int[] fromRowCol;
            int[] toRowCol; 
            while (true) {
                input = scanner.nextLine();
                if (input.equals("q")) {
                    scanner.close();
                    return;
                }

                if (!Utils.isValidInput(input)) {
                    System.out.println("Invalid input format");
                    continue;
                }

                fromRowCol = Utils.convertFileRankToRowCol(input.substring(0, 2));
                toRowCol = Utils.convertFileRankToRowCol(input.substring(3, 5));

                // Is Check
                if (board.isCheck(currentPlayer)) {
                    System.out.println(currentPlayer + " is in checkmate. Game over!");
                    // only moves allowed are king
                }
        
                // Is Checkmate
                if (board.isCheckmate(currentPlayer)) {
                    System.out.println(currentPlayer + " is in checkmate. Game over!");
                    scanner.close();
                    return;
                }

                // Attempt the move
                if (board.movePiece(fromRowCol[0], fromRowCol[1], toRowCol[0], toRowCol[1])) {
                    break;
                }
                else {
                    System.out.println("Invalid move.");
                    continue;
                }
            }
            // Switch to the other player
            currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
            System.out.println();
        }
    }
}
