package chess.pieces;

import java.util.*;

import chess.Gameboard;
import chess.Piece;
import chess.enums.Color;

/**
 * Represents a bishop chess piece on the game board.
 */
public class Bishop extends Piece
{
    /**
     * Constructs a new Bishop Piece object with the given
     * color, row, and column.
     * @param color the color of the piece (either Color.WHITE or Color.BLACK)
     * @param row the row of the piece on the board (0-7)
     * @param col the column of the piece on the board (0-7)
     */
    public Bishop(Color color, int row, int col)
    {
        super(color, row, col, (color == Color.WHITE) ? "wB" : "bB");
    }

    /**
     * Determines whether or not a bishop can move from the specified 
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
    public boolean canMoveTo(int fromRow, int fromCol, int toRow, int toCol, Gameboard board)
    {
        return false;
    }

    /**
     * Returns a set of integers representing the squares that this piece is attacking on the game board.
     * This method should be overridden by subclasses to implement piece-specific attack rules.
     * @param board the game board on which the piece is attacking
     * @return a set of integers representing the squares that this piece is attacking
     */
    @Override
    public Set<Integer> getAttackSquares(Gameboard board) {
       return null;
    }
}
