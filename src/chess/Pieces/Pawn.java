package chess.pieces;

import chess.GameBoard;
import chess.Piece;
import chess.enums.Color;
import chess.enums.PieceType;

/**
 * Represents a pawn chess piece on the game board.
 */
public class Pawn extends Piece
{
    /**
     * Constructs a new Pawn Piece object with the given
     * color, row, and column.
     * @param color the color of the piece (either Color.WHITE or Color.BLACK)
     * @param row the row of the piece on the board (0-7)
     * @param col the column of the piece on the board (0-7)
     */
    public Pawn(Color color, int row, int col)
    {
        super(color, PieceType.PAWN, row, col, (color == Color.WHITE) ? "wp" : "bp");
    }

    /**
     * Determines whether or not a pawn can move from the specified 
     * starting position to the specified ending positionon the given
     * game board.
     * @param fromRow the starting row of the piece
     * @param fromCol the starting column of the piece
     * @param toRow the ending row of the piece
     * @param toCol the ending column of the piece
     * @param board the game board on which the move is being made
     * @return true if the move is valid, false otherwise
     */
    @Override
    public boolean canMoveTo(int fromRow, int fromCol, int toRow, int toCol, GameBoard board)
    {
        // -1 for white, 1 for black
        int forwardDirection = this.getColor() == Color.WHITE ? -1 : 1; 

        // Check that the piece is moving in the correct direction based on its color
        if (this.getColor() == Color.WHITE && toRow >= fromRow) {
            return false;
        } 
        else if (this.getColor() == Color.BLACK && toRow <= fromRow) {
            return false;
        }

        // Check that the piece is not moving sideways
        if (fromCol != toCol) {
            // Check if the pawn is capturing a piece diagonally
            Piece capturedPiece = board.getPieceAt(toRow, toCol);
            if (capturedPiece != null && capturedPiece.getColor() != this.getColor()) {
                // Capture is valid
                return true;
            } 
            else {
                // Pawn cannot move diagonally without capturing
                return false;
            }
        }

        // Check if the pawn is moving one or two squares forward
        if (fromCol == toCol && Math.abs(toRow - fromRow) <= 2) {
            // Check if the pawn is not blocked by another piece
            if (toRow == fromRow + 2*forwardDirection && fromRow == (this.getColor() == Color.WHITE ? 6 : 1) 
                    && board.getPieceAt(fromRow+1*forwardDirection, fromCol) == null 
                    && board.getPieceAt(fromRow+2*forwardDirection, fromCol) == null) {
                // Pawn can move two squares forward from its starting position
                return true;
            } else if (toRow == fromRow + 1*forwardDirection && board.getPieceAt(toRow, fromCol) == null) {
                // Pawn can move one square forward
                return true;
            }
        }
        // Move is not valid
        return false;
    }
}
