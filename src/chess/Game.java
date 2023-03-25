package chess;

import java.util.Scanner;

public class Game
{
    private GameBoard board;

    public Game()
    {
        board = new GameBoard();
    }

    /**
     * Allows players to take turns making moves until the game ends.
     * Prompts players for input and makes moves on the game board
     * accordingly. Checks for checkmate and stalemate conditions after
     * each move. Ends the game when a player is checkmated or when a
     * stalemate is reached.
     */
    public void play()
    {
        Scanner scanner = new Scanner(System.in);
        Color currentPlayer = Color.WHITE;

        while (true) {
            // Display the board
            board.printBoard();
        
            // Get the player's move
            System.out.print(Utils.capitalize(currentPlayer.toString()) + "'s move: ");
            String input = scanner.nextLine();

            if (input == "q") { break; }
            // String[] tokens = input.split(" ");
            // int fromRow = Integer.parseInt(tokens[0]);
            // int fromCol = Integer.parseInt(tokens[1]);
            // int toRow = Integer.parseInt(tokens[2]);
            // int toCol = Integer.parseInt(tokens[3]);

            // Make the move
            //board.movePiece(fromRow, fromCol, toRow, toCol);

            // Check for game-ending conditions
            // if (board.isCheckmate(currentPlayer)) {
            //     System.out.println(currentPlayer + " is in checkmate. Game over!");
            //     break;
            // } else if (board.isStalemate(currentPlayer)) {
            //     System.out.println(currentPlayer + " is in stalemate. Game over!");
            //     break;
            // }

            // Switch to the other player
            currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
        }
        scanner.close();
    }
}
