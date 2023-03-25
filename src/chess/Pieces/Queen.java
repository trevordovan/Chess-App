package chess.pieces;

import chess.GameBoard;
import chess.Piece;
import chess.enums.Color;
import chess.enums.PieceType;

/**
 * Represents a queen chess piece on the game board.
 */
public class Queen extends Piece
{
    /**
     * Constructs a new Queen Piece object with the given
     * color, row, and column.
     * @param color the color of the piece (either Color.WHITE or Color.BLACK)
     * @param row the row of the piece on the board (0-7)
     * @param col the column of the piece on the board (0-7)
     */
    public Queen(Color color, int row, int col)
    {
        super(color, PieceType.QUEEN, row, col, (color == Color.WHITE) ? "wQ" : "bQ");
    }

    /**
     * Determines whether or not a queen can move from the specified 
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
        return false;
    }
}
