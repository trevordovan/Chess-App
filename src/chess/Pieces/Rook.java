package chess.pieces;

import java.util.*;

import chess.Gameboard;
import chess.Piece;
import chess.enums.Color;
import chess.utils.Utils;

/**
 * Represents a Rook chess piece on the game board.
 */
public class Rook extends Piece
{
    /**
     * Constructs a new Rook Piece object with the given
     * color, row, and column.
     * @param color the color of the piece (either Color.WHITE or Color.BLACK)
     * @param row   the row of the piece on the board (0-7)
     * @param col   the column of the piece on the board (0-7)
     */
    public Rook(Color color, int row, int col)
    {
        super(color, row, col, (color == Color.WHITE) ? "wR" : "bR");
    }

    /**
     * Determines whether or not a rook can move from the specified
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
    public boolean canMoveTo(int fromRow, int fromCol, int toRow, int toCol, Gameboard board)
    {
        Piece piece = board.getPieceAt(fromRow, fromCol);
        if (piece == null) {
            return false;
        }

        if (piece.getColor() != board.getCurrentPlayer()) {
            return false;
        }

        Set<Integer> attacks = getAttackSquares(board);

        // Convert the starting and ending positions to integer indices
        int toIndex = Utils.toIndex(toRow, toCol);

        if (!attacks.contains(toIndex)) {
            return false;
        }

        // Check if there are any pieces blocking the rook's path
        if (fromRow == toRow) {
            int start = Math.min(fromCol, toCol);
            int end = Math.max(fromCol, toCol);
            for (int c = start + 1; c < end; c++) {
                Piece tempPiece = board.getPieceAt(fromRow, c);
                if (tempPiece != null && tempPiece.getColor() == piece.getColor()) {
                    return false;
                }
            }
        } 
        else if (fromCol == toCol) {
            int start = Math.min(fromRow, toRow);
            int end = Math.max(fromRow, toRow);
            for (int r = start + 1; r < end; r++) {
                Piece tempPiece = board.getPieceAt(r, fromCol);
                if (tempPiece != null && tempPiece.getColor() == piece.getColor()) {
                    return false;
                }
            }
        } 
        else {
            return false;
        }

        return true;
    }

    /**
     * Returns a set of integers representing the squares that this piece is
     * attacking on the game board.
     * This method should be overridden by subclasses to implement piece-specific
     * attack rules.
     * @param board the game board on which the piece is attacking
     * @return a set of integers representing the squares that this piece is
     *         attacking
     */
    @Override
    public Set<Integer> getAttackSquares(Gameboard board)
    {
        Set<Integer> attacks = new HashSet<>();

        // Check squares above the rook
        for (int row = getRow() - 1; row >= 0; row--) {
            Piece piece = board.getPieceAt(row, getCol());
            if (piece == null) {
                attacks.add(Utils.toIndex(row,getCol()));
            } else {
                if (piece.getColor() != getColor()) {
                    attacks.add(Utils.toIndex(row, getCol()));
                }
                break;
            }
        }

        // Check squares below the rook
        for (int row = getRow() + 1; row < 8; row++) {
            Piece piece = board.getPieceAt(row, getCol());
            if (piece == null) {
                attacks.add(Utils.toIndex(row, getCol()));
            } else {
                if (piece.getColor() != getColor()) {
                    attacks.add(Utils.toIndex(row, getCol()));
                }
                break;
            }
        }

        // Check squares to the right of the rook
        for (int col = getCol() + 1; col < 8; col++) {
            Piece piece = board.getPieceAt(getRow(), col);
            if (piece == null) {
                attacks.add(Utils.toIndex(getRow(), col));
            } else {
                if (piece.getColor() != getColor()) {
                    attacks.add(Utils.toIndex(getRow(), col));
                }
                break;
            }
        }

        // Check squares to the left of the rook
        for (int col = getCol() - 1; col >= 0; col--) {
            Piece piece = board.getPieceAt(getRow(), col);
            if (piece == null) {
                attacks.add(Utils.toIndex(getRow(), col));
            } else {
                if (piece.getColor() != getColor()) {
                    attacks.add(Utils.toIndex(getRow(), col));
                }
                break;
            }
        }
        return attacks;
    }
}
