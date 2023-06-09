/**
 * @author Trevor Dovan
 * @author Kate Liu
 */ 

package chess.pieces;

import java.util.*;

import chess.Gameboard;
import chess.Piece;
import chess.enums.Color;

/**
 * Represents a queen chess piece on the game board.
 */
public class Queen extends Piece {
    /**
     * Constructs a new Queen Piece object with the given
     * color, row, and column.
     * @param color the color of the piece (either Color.WHITE or Color.BLACK)
     * @param row   the row of the piece on the board (0-7)
     * @param col   the column of the piece on the board (0-7)
     */

    public Queen(Color color, int row, int col) {
        super(color, row, col, (color == Color.WHITE) ? "wQ" : "bQ");
    }

    /**
     * Determines whether or not a queen can move from the specified
     * starting position to the specified ending positionon the given
     * game board.
     * @param fromRow the starting row of the piece
     * @param fromCol the starting column of the piece
     * @param toRow   the ending row of the piece
     * @param toCol   the ending column of the piece
     * @param board   the game board on which the move is being made
     * @return true if the move is valid, false otherwise
     */
    @Override
    public boolean canMoveTo(int fromRow, int fromCol, int toRow, int toCol, Gameboard board) {
        // A Queen piece can move like a Bishop or a Rook piece:
        // It can move any number of squares diagonally in any direction, or any number
        // of squares horizontally or vertically.
        // We can reuse the canMoveTo method for Bishop and Rook to check if a move is
        // legal for the Queen.

        // Check if the move is diagonal (like a Bishop)
        if (Math.abs(toRow - fromRow) == Math.abs(toCol - fromCol)) {
            Bishop bishop = new Bishop(getColor(), fromRow, fromCol);
            return bishop.canMoveTo(fromRow, fromCol, toRow, toCol, board);
        }

        // Check if the move is horizontal or vertical (like a Rook)
        if (toRow == fromRow || toCol == fromCol) {
            Rook rook = new Rook(getColor(), fromRow, fromCol, getColor().opposite());
            return rook.canMoveTo(fromRow, fromCol, toRow, toCol, board);
        }

        // If the move is neither diagonal nor horizontal/vertical, it is invalid
        return false;

    }

    /**
     * Returns a set of integers representing the squares that this queen is
     * attacking on the game board.
     * This method should be overridden by subclasses to implement piece-specific
     * attack rules.
     * @param board the game board on which the piece is attacking
     * @return a set of integers representing the squares that this piece is
     *         attacking
     */
    @Override
    public Set<Integer> getAttackSquares(Gameboard board) {
        Set<Integer> attackSquares = new HashSet<Integer>();
        int row = getRow();
        int col = getCol();

        // Add all squares attacked by a Bishop with the same color as this Queen
        Bishop bishop = new Bishop(getColor(), row, col);
        attackSquares.addAll(bishop.getAttackSquares(board));

        // Add all squares attacked by a Rook with the same color as this Queen
        Rook rook = new Rook(getColor(), row, col, getColor().opposite());
        attackSquares.addAll(rook.getAttackSquares(board));

        return attackSquares;
    }
}
