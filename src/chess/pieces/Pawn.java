/**
 * @author Trevor Dovan
 * @author Kate Liu
 */

package chess.pieces;

import java.util.HashSet;
import java.util.Set;

import chess.Gameboard;
import chess.Piece;
import chess.enums.Color;
import chess.utils.Utils;

/**
 * Represents a pawn chess piece on the game board.
 */
public class Pawn extends Piece {
    /**
     * Constructs a new Pawn Piece object with the given
     * color, row, and column.
     * 
     * @param color the color of the piece (either Color.WHITE or Color.BLACK)
     * @param row   the row of the piece on the board (0-7)
     * @param col   the column of the piece on the board (0-7)
     */
    public Pawn(Color color, int row, int col) {
        super(color, row, col, (color == Color.WHITE) ? "wp" : "bp");
    }

    /**
     * Determines whether or not a pawn can move from the specified
     * starting position to the specified ending positionon the given
     * game board.
     * 
     * @param fromRow the starting row of the piece
     * @param fromCol the starting column of the piece
     * @param toRow   the ending row of the piece
     * @param toCol   the ending column of the piece
     * @param board   the game board on which the move is being made
     * @return true if the move is valid, false otherwise
     */
    @Override
    public boolean canMoveTo(int fromRow, int fromCol, int toRow, int toCol, Gameboard board) {
        // -1 for white, 1 for black
        int forwardDirection = this.getColor() == Color.WHITE ? -1 : 1;
    
        // Check that the piece is moving in the correct direction based on its color
        if (this.getColor() == Color.WHITE && toRow >= fromRow) {
            return false;
        } else if (this.getColor() == Color.BLACK && toRow <= fromRow) {
            return false;
        }
    
        // Check that the piece is not moving sideways
        if (fromCol != toCol) {
            // Check if the pawn is capturing a piece diagonally
            if (Math.abs(fromRow - toRow) != 1 || Math.abs(fromCol - toCol) != 1) {
                // Check for en passant
                if (board.hasJustDoubleMoved(this)) {
                    Pawn justDoubleMovedPawn = board.getJustDoubleMoved();
                    if (justDoubleMovedPawn.getRow() == fromRow &&
                            Math.abs(justDoubleMovedPawn.getCol() - toCol) == 1 &&
                            board.getPieceAt(toRow - forwardDirection, toCol) == null) {
                        return true;
                    }
                }                
                return false;
            }
            Piece capturedPiece = board.getPieceAt(toRow, toCol);
            if (capturedPiece != null && capturedPiece.getColor() != this.getColor()) {
                // Capture is valid
                return true;
            } else {
                // Pawn cannot move diagonally without capturing
                return false;
            }
        }
    
        // Check if the pawn is moving one or two squares forward
        if (fromCol == toCol && Math.abs(toRow - fromRow) <= 2) {
            // Check if the pawn is not blocked by another piece
            if (toRow == fromRow + 2 * forwardDirection && fromRow == (this.getColor() == Color.WHITE ? 6 : 1)
                    && board.getPieceAt(fromRow + 1 * forwardDirection, fromCol) == null
                    && board.getPieceAt(fromRow + 2 * forwardDirection, fromCol) == null) {
                // Pawn can move two squares forward from its starting position
                return true;
            } else if (toRow == fromRow + 1 * forwardDirection && board.getPieceAt(toRow, fromCol) == null) {
                // Pawn can move one square forward
                return true;
            }
        }
        // Move is not valid
        return false;
    }    

    /**
     * Returns a set of integers representing the squares that this pawn is
     * attacking on the game board.
     * A pawn can attack diagonally in the forward direction.
     * 
     * @param board the game board on which the pawn is attacking
     * @return a set of integers representing the squares that this pawn is
     *         attacking
     */
    @Override
    public Set<Integer> getAttackSquares(Gameboard board) {
        Set<Integer> attacks = new HashSet<>();

        int forwardDir = (getColor() == Color.WHITE) ? -1 : 1;
        int row = this.getRow();
        int col = this.getCol();

        // Check attacks to the left and right diagonally
        for (int colOffset : new int[] { -1, 1 }) {
            int attackRow = row + forwardDir;
            int attackCol = col + colOffset;

            if (attackRow >= 0 && attackRow < 8 && attackCol >= 0 && attackCol < 8) {
                // Check if there is a piece at the attack square
                Piece pieceAtAttackSquare = board.getPieceAt(attackRow, attackCol);
                if (pieceAtAttackSquare != null && pieceAtAttackSquare.getColor() != this.getColor()) {
                    // Add the attack square to the set of attacks
                    attacks.add(Utils.toIndex(attackRow, attackCol));
                } else {
                    // Check for En passant
                    Piece lastMovedPiece = board.getLastMovedPiece();
                    Pawn justDoubleMovedPawn = board.getJustDoubleMoved();

                    if (lastMovedPiece instanceof Pawn && board.hasJustDoubleMoved((Pawn) lastMovedPiece)) {
                        int lastMovedRow = justDoubleMovedPawn.getRow();
                        int lastMovedCol = justDoubleMovedPawn.getCol();

                        if (lastMovedPiece.getColor() != this.getColor() &&
                                lastMovedRow == row && Math.abs(lastMovedCol - col) == 1) {
                            if (attackRow == lastMovedRow + forwardDir && attackCol == lastMovedCol) {
                                // Add the attack square to the set of attacks
                                attacks.add(Utils.toIndex(attackRow, attackCol));
                            }
                        }
                    }

                }
            }
        }
        return attacks;
    }

}