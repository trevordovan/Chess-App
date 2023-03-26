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
        // Get the attack squares for the rook
        Set<Integer> attacks = getAttackSquares(board);

        // Convert the starting and ending positions to integer indices
        int fromIndex = fromRow * 8 + fromCol;
        int toIndex = toRow * 8 + toCol;

        // Check if the ending position is one of the attack squares
        if (!attacks.contains(toIndex)) {
            return false;
        }

        // Check if there are any pieces blocking the rook's path
        if (fromRow == toRow) {
            int start = Math.min(fromCol, toCol);
            int end = Math.max(fromCol, toCol);
            for (int c = start + 1; c < end; c++) {
                Piece piece = board.getPieceAt(fromRow, c);
                if (piece != null) {
                    return false;
                }
            }
        } else if (fromCol == toCol) {
            int start = Math.min(fromRow, toRow);
            int end = Math.max(fromRow, toRow);
            for (int r = start + 1; r < end; r++) {
                Piece piece = board.getPieceAt(r, fromCol);
                if (piece != null) {
                    return false;
                }
            }
        } else {
            // Invalid move for rook
            return false;
        }

        // Check if the piece at the starting position is the same color as the current
        // turn
        if (board.getPieceAt(fromRow, fromCol).getColor() != board.getCurrentPlayer()) {
            return false;
        }

        // Move is valid
        return true;
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

        // Check squares above the rook
        for (int row = getRow() - 1; row >= 0; row--) {
            Piece piece = board.getPieceAt(row, getCol());
            if (piece == null) {
                attacks.add(row * 8 + getCol());
            } else {
                if (piece.getColor() != getColor()) {
                    attacks.add(row * 8 + getCol());
                }
                break;
            }
        }

        // Check squares below the rook
        for (int row = getRow() + 1; row < 8; row++) {
            Piece piece = board.getPieceAt(row, getCol());
            if (piece == null) {
                attacks.add(row * 8 + getCol());
            } else {
                if (piece.getColor() != getColor()) {
                    attacks.add(row * 8 + getCol());
                }
                break;
            }
        }

        // Check squares to the right of the rook
        for (int col = getCol() + 1; col < 8; col++) {
            Piece piece = board.getPieceAt(getRow(), col);
            if (piece == null) {
                attacks.add(getRow() * 8 + col);
            } else {
                if (piece.getColor() != getColor()) {
                    attacks.add(getRow() * 8 + col);
                }
                break;
            }
        }

        // Check squares to the left of the rook
        for (int col = getCol() - 1; col >= 0; col--) {
            Piece piece = board.getPieceAt(getRow(), col);
            if (piece == null) {
                attacks.add(getRow() * 8 + col);
            } else {
                if (piece.getColor() != getColor()) {
                    attacks.add(getRow() * 8 + col);
                }
                break;
            }
        }

        return attacks;
    }
}
