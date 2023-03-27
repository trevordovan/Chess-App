package chess.pieces;

import java.util.*;

import chess.GameBoard;
import chess.Piece;
import chess.enums.Color;
import chess.pieces.Rook;
import chess.pieces.Bishop;

/**
 * Represents a queen chess piece on the game board.
 */
public class Queen extends Piece {
    /**
     * Constructs a new Queen Piece object with the given
     * color, row, and column.
     * 
     * @param color the color of the piece (either Color.WHITE or Color.BLACK)
     * @param row   the row of the piece on the board (0-7)
     * @param col   the column of the piece on the board (0-7)
     */

    public Queen(Color color, int row, int col, Bishop bishop, Rook rook) {
        super(color, row, col, (color == Color.WHITE) ? "wQ" : "bQ");
        this.bishop = bishop;
        this.rook = rook;    
    }


    /**
     * Determines whether or not a queen can move from the specified
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
        // A Queen piece can move like a Bishop or a Rook piece:
        // It can move any number of squares diagonally in any direction, or any number
        // of squares horizontally or vertically.
        // We can reuse the canMoveTo method for Bishop and Rook to check if a move is
        // legal for the Queen.
        return new Bishop(getColor()).canMoveTo(fromRow, fromCol, toRow, toCol, board) ||
                new Rook(getColor()).canMoveTo(fromRow, fromCol, toRow, toCol, board);
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
        Set<Integer> attackSquares = new HashSet<Integer>();
        int row = getRow();
        int col = getCol();

        // Add all squares attacked by a Bishop with the same color as this Queen
        attackSquares.addAll(new Bishop(getColor()).getAttackSquares(board));

        // Add all squares attacked by a Rook with the same color as this Queen
        attackSquares.addAll(new Rook(getColor()).getAttackSquares(board));

        return attackSquares;
    }
}
