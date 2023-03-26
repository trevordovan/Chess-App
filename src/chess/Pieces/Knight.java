package chess.pieces;

import chess.GameBoard;
import chess.Piece;
import chess.enums.Color;
import chess.utils.Utils;

import java.util.*;

/**
 * Represents a Knight chess piece on the game board.
 */
public class Knight extends Piece {

    /**
     * Constructs a new Knight Piece object with the given
     * color, row, and column.
     * @param color the color of the piece (either Color.WHITE or Color.BLACK)
     * @param row   the row of the piece on the board (0-7)
     * @param col   the column of the piece on the board (0-7)
     */
    public Knight(Color color, int row, int col) {
        super(color, row, col, (color == Color.WHITE) ? "wN" : "bN");
    }

    /**
     * Determines whether or not a knight can move from the specified
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
    public boolean canMoveTo(int fromRow, int fromCol, int toRow, int toCol, GameBoard board) {
        Set<Integer> attacks = getAttackSquares(board);

        // Convert the starting and ending positions to integer indices
        int fromIndex = Utils.toIndex(fromRow, fromCol);
        int toIndex = Utils.toIndex(toRow, toCol);

        // Check if the ending position is one of the attack squares
        return attacks.contains(toIndex);
    }

    /**
     * Returns a set of integers representing the squares that this piece is attacking on the game board.
     * This method should be overridden by subclasses to implement piece-specific attack rules.
     * @param board the game board on which the piece is attacking
     * @return a set of integers representing the squares that this piece is attacking
     */
    @Override
    public Set<Integer> getAttackSquares(GameBoard board) {
        Set<Integer> attacks = new HashSet<>();

        // Generate all possible moves for the knight
        int[][] possibleMoves = {
                { -2, -1 }, { -2, 1 },
                { -1, -2 }, { -1, 2 },
                { 1, -2 }, { 1, 2 },
                { 2, -1 }, { 2, 1 }
        };
    
        // Check each possible move for validity
        for (int[] move : possibleMoves) {
            int destRow = getRow() + move[0];
            int destCol = getCol() + move[1];
    
            if (destRow >= 0 && destRow < 8 && destCol >= 0 && destCol < 8) {
                Piece piece = board.getPieceAt(destRow, destCol);
                if (piece == null || piece.getColor() != getColor()) {
                    attacks.add(Utils.toIndex(destRow, destCol));
                }
            }
        }
    
        return attacks;
    }

}
