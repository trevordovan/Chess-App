package chess.pieces;

import java.util.*;

import chess.GameBoard;
import chess.Piece;
import chess.enums.Color;

/**
 * Represents a Rook chess piece on the game board.
 */
public class Rook extends Piece {
    /**
     * Constructs a new Rook Piece object with the given
     * color, row, and column.
     * 
     * @param color the color of the piece (either Color.WHITE or Color.BLACK)
     * @param row   the row of the piece on the board (0-7)
     * @param col   the column of the piece on the board (0-7)
     */
    public Rook(Color color, int row, int col) {
        super(color, row, col, (color == Color.WHITE) ? "wR" : "bR");
    }

    /**
     * Determines whether or not a rook can move from the specified
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
    public boolean canMoveTo(int fromRow, int fromCol, int toRow, int toCol, GameBoard board) {
        return false;
    }

    /**
     * Returns a set of integers representing the squares that this piece is
     * attacking on the game board.
     * This method should be overridden by subclasses to implement piece-specific
     * attack rules.
     * 
     * @param board the game board on which the piece is attacking
     * @return a set of integers representing the squares that this piece is
     *         attacking
     */
    @Override
    public Set<Integer> getAttackSquares(GameBoard board) {
        Set<Integer> attacks = new HashSet<>();

        // Check for attacks in all four directions
        int[][] directions = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
        for (int[] direction : directions) {
            int rowOffset = direction[0];
            int colOffset = direction[1];

            // Check each square in the current direction until we hit an obstacle or the
            // edge of the board
            int currentRow = getRow() + rowOffset;
            int currentCol = getCol() + colOffset;
            while (currentRow >= 0 && currentRow < 8 && currentCol >= 0 && currentCol < 8) {
                Piece piece = board.getPieceAt(currentRow, currentCol);
                if (piece == null) {
                    // Square is empty, so add it to the attack set and continue checking in this
                    // direction
                    attacks.add(currentRow * 8 + currentCol);
                } else if (piece.getColor() != getColor()) {
                    // Square is occupied by an enemy piece, so add it to the attack set but stop
                    // checking in this direction
                    attacks.add(currentRow * 8 + currentCol);
                    break;
                } else {
                    // Square is occupied by a friendly piece, so stop checking in this direction
                    break;
                }

                currentRow += rowOffset;
                currentCol += colOffset;
            }
        }

        return attacks;
    }
}
