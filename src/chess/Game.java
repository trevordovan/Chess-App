/**
 * @author Trevor Dovan
 * @author Kate Liu
 */ 

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
    private Gameboard board;

    /**
     * Creates a new game of chess.
     */
    public Game()
    {
        board = new Gameboard();
    }

    /**
     * Allows players to take turns making moves until the game ends.
     * Prompts players for input and makes moves on the game board
     * accordingly. Checks for check and checkmate each move. Ends 
     * the game when a player is checkmated is reached.
     */
    public void play() {
        Scanner scanner = new Scanner(System.in);
        board.setCurrentPlayer(Color.WHITE);
        int enPassantCol = -1; // initialize to -1, which means no en passant capture is possible
        
        while (true) {
            board.printBoard();
    
            if (board.isCheckmate(board.getCurrentPlayer())) {
                System.out.println("Checkmate");
                Color winner = board.getCurrentPlayer().opposite();
                System.out.println(Utils.capitalize(winner.toString()) + " wins");
                scanner.close();
                return;
            }
    
            if (board.isCheck(board.getCurrentPlayer())) {
                System.out.println("Check");
            }
    
            System.out.print(Utils.capitalize(board.getCurrentPlayer().toString()) + "'s move: ");
    
            String input;
            int[] fromRowCol;
            int[] toRowCol; 
            while (true) {
                input = scanner.nextLine();
                if (input.equals("q")) {
                    scanner.close();
                    return;
                }
    
                if (input.equals("resign")) {
                    Color winner = board.getCurrentPlayer().opposite();
                    System.out.println(Utils.capitalize(winner.toString()) + " wins");
                    scanner.close();
                    return;
                }
    
                if (input.length() > 5) {
                    if (input.substring(6).equals("draw?")) {
                        String draw = scanner.nextLine();
                        if (draw.equals("draw")) {
                            scanner.close();
                            return;
                        }
                        else {
                            break;
                        }
                    }
                }
                
                if (!Utils.isValidInput(input)) {
                    System.out.println("Invalid input format");
                    continue;
                }
                
                fromRowCol = Utils.convertFileRankToRowCol(input.substring(0, 2));
                toRowCol = Utils.convertFileRankToRowCol(input.substring(3, 5));
                
                // Check for en passant move
                if (input.contains("e.p.")) {
                    enPassantCol = Utils.convertFileRankToRowCol(input.substring(6))[1];
                } else {
                    enPassantCol = -1;
                }
    
                Piece tempFromPiece = board.getPieceAt(fromRowCol[0], fromRowCol[1]);
                Piece tempToPiece = board.getPieceAt(toRowCol[0], toRowCol[1]);
    
                if (board.movePiece(fromRowCol[0], fromRowCol[1], toRowCol[0], toRowCol[1])) {
                    if (board.isCheck(board.getCurrentPlayer())) {
                        // undo move
                        board.setPieceAt(tempFromPiece, fromRowCol[0], fromRowCol[1]);
                        board.setPieceAt(tempToPiece, toRowCol[0], toRowCol[1]);
                        System.out.println("Illegal move, try again");
                        continue;
                    }
                    break;
                }
                else {
                    System.out.println("Illegal move, try again");
                    continue;
                }
            }
            board.setCurrentPlayer(board.getCurrentPlayer().opposite());
        }
    }
    
}
