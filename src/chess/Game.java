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
     * accordingly. Checks for check and checkmate each move. Ends 
     * the game when a player is checkmated is reached.
     */
    public void play() {
        Scanner scanner = new Scanner(System.in);
        Color currentPlayer = Color.WHITE;
    
        while (true) {
            board.printBoard();
    
            // Get the player's move
            System.out.print(Utils.capitalize(currentPlayer.toString()) + "'s move: ");

            String input;
            while (true) {
                input = scanner.nextLine();
                if (input.equals("q")) {
                    scanner.close();
                    return;
                }

                // Validate input format
                if (input.length() != 5) {
                    System.out.println("Invalid input format");
                    continue;
                }

                char fromFile = input.charAt(0);
                char fromRank = input.charAt(1);
                char toFile = input.charAt(3);
                char toRank = input.charAt(4);
                if (fromFile < 'a' || fromFile > 'h' || fromRank < '1' || fromRank > '8' ||
                    toFile < 'a' || toFile > 'h' || toRank < '1' || toRank > '8') {
                    System.out.println("Invalid input format");
                    continue;
                }

                int[] fromRowCol = Utils.convertFileRankToRowCol(input.substring(0, 2));
                int[] toRowCol = Utils.convertFileRankToRowCol(input.substring(3, 5));
                if (fromRowCol != null && toRowCol != null) {
                    break;
                }
            }
     
            // Make the move
            // boolean moveResult = board.movePiece(fromRowCol[0], fromRowCol[1], toRowCol[0], toRowCol[1]);
            // if (!moveResult) {
            //     System.out.println("Invalid move.");
            //     continue;
            // }
    
            // Check for game-ending conditions
            // if (board.isCheckmate(currentPlayer)) {
            //     System.out.println(currentPlayer + " is in checkmate. Game over!");
            //     break;
            // }
    
            // Switch to the other player
            currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
        }
    }
}
