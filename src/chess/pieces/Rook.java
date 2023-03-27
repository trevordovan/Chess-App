/**
 * @author Trevor Dovan
 * @author Kate Liu
 */

package chess.pieces;

import java.util.*;

import chess.Gameboard;
import chess.Piece;
import chess.enums.Color;
import chess.utils.Utils;

/**
 * Represents a Rook chess piece on the game board.
 */
public class Rook extends Piece {

    private Color enemyColor;

    /**
     * Constructs a new Rook Piece object with the given
     * color, row, and column.
     * @param color the color of the piece (either Color.WHITE or Color.BLACK)
     * @param row the row of the piece on the board (0-7)
     * @param col the column of the piece on the board (0-7)
     * @param enemyColor the color of the enemy pieces (either Color.WHITE or Color.BLACK)
     */
    public Rook(Color color, int row, int col, Color enemyColor) {
        super(color, row, col, (color == Color.WHITE) ? "wR" : "bR");
        this.enemyColor = enemyColor;
        setAttackingColor(color.opposite());
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
    public boolean canMoveTo(int fromRow, int fromCol, int toRow, int toCol, Gameboard board) {
        if (fromRow != toRow && fromCol != toCol) {
            return false; // Rook can only move horizontally or vertically
        }
        if (fromRow == toRow) {
            // Check for obstacles to the right of the rook
            int step = fromCol < toCol ? 1 : -1;
            for (int col = fromCol + step; col != toCol; col += step) {
                if (board.getPieceAt(fromRow, col) != null) {
                    return false; // There is an obstacle in the way
                }
            }
        } else {
            // Check for obstacles below the rook
            int step = fromRow < toRow ? 1 : -1;
            for (int row = fromRow + step; row != toRow; row += step) {
                if (board.getPieceAt(row, fromCol) != null) {
                    return false; // There is an obstacle in the way
                }
            }
        }
        // Check if castling is possible
        if (board.isCheck(attackingColor)) {
            return false; // Cannot castle out of check
        }
        if (board.isSquareAttacked(getRow(), getCol(), enemyColor)) {
            return false; // Cannot castle through an attacked square
        }
        if (toCol == 0 && board.getPieceAt(getRow(), 0) instanceof Rook && !board.hasMoved(getRow(), 0)
                && !board.hasMoved(getRow(), getCol())) {
            // Queenside castling
            for (int col = fromCol - 1; col > 0; col--) {
                if (board.getPieceAt(getRow(), col) != null) {
                    return false; // Cannot castle if there are pieces in the way
                }
                if (board.isSquareAttacked(getRow(), col, enemyColor)) {
                    return false; // Cannot castle through an attacked square
                }
            }
            return true; // Castling is possible
        }
        if (toCol == 7 && board.getPieceAt(getRow(), 7) instanceof Rook && !board.hasMoved(getRow(), 7)
                && !board.hasMoved(getRow(), getCol())) {
            // Kingside castling
            for (int col = fromCol + 1; col < 7; col++) {
                if (board.getPieceAt(getRow(), col) != null) {
                    return false; // Cannot castle if there are pieces in the way
                }
                if (board.isSquareAttacked(getRow(), col, enemyColor)) {
                    return false; // Cannot castle through an attacked square
                }
            }
            return true; // Castling is possible
        }
        return true; // All conditions are satisfied
    }

    /**
     * Returns a set of integers representing the squares that this rook is
     * attacking on the game board.
     * This method should be overridden by subclasses to implement piece-specific
     * attack rules.
     * @param board the game board on which the piece is attacking
     * @return a set of integers representing the squares that this piece is attacking
     */
    @Override
    public Set<Integer> getAttackSquares(Gameboard board) {
        Set<Integer> attacks = new HashSet<>();

        // Check squares above the rook
        for (int row = getRow() - 1; row >= 0; row--) {
            Piece piece = board.getPieceAt(row, getCol());
            if (piece == null) {
                attacks.add(Utils.toIndex(row, getCol()));
            } else {
                if (piece.getColor() != attackingColor) {
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
                if (piece.getColor() != attackingColor) {
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
                if (piece.getColor() != attackingColor) {
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
                if (piece.getColor() != attackingColor) {
                    attacks.add(Utils.toIndex(getRow(), col));
                }
                break;
            }
        }
        return attacks;
    }
}
