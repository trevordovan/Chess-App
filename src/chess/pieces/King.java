/**
 * @author Trevor Dovan
 * @author Kate Liu
 */ 

package chess.pieces;

import java.util.HashSet;
import java.util.Set;

import chess.Gameboard;
import chess.Piece;
import chess.enums.Color;
import chess.utils.Utils;

/**
 * Represents a king chess piece on the game board.
 */
public class King extends Piece
{
    /**
     * Constructs a new King Piece object with the given
     * color, row, and column.
     * @param color the color of the piece (either Color.WHITE or Color.BLACK)
     * @param row the row of the piece on the board (0-7)
     * @param col the column of the piece on the board (0-7)
     */
    public King(Color color, int row, int col)
    {
        super(color, row, col, (color == Color.WHITE) ? "wK" : "bK");
    }

    /**
     * Determines whether or not a king can move from the specified 
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
        Piece toPiece = board.getPieceAt(toRow, toCol);
        if (toPiece != null) {
            if (toPiece.getColor() == getColor()) {
                return false;
            }
        }
        if (Math.abs(toRow - fromRow) <= 1 && Math.abs(toCol - fromCol) <= 1) {
            // The destination square is adjacent to the king
            return true;
        }
        return false;
    }

    /**
     * Returns a set of integers representing the squares that this king is attacking on the game board.
     * This method should be overridden by subclasses to implement piece-specific attack rules.
     * @param board the game board on which the piece is attacking
     * @return a set of integers representing the squares that this piece is attacking
     */
    @Override
    public Set<Integer> getAttackSquares(Gameboard board)
    {
        Set<Integer> attacks = new HashSet<>();
        int[] kingPos = board.findKing(this.getColor());
        int row = kingPos[0];
        int col = kingPos[1];
    
        // Check all squares around the king for attack
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = col - 1; c <= col + 1; c++) {
                if ((r == row && c == col) || r < 0 || c < 0 || r > 7 || c > 7) {
                    // Skip the current square if it's the king's position or out of bounds
                    continue;
                }
                attacks.add(Utils.toIndex(r, c));
            }
        }
        return attacks;
    }
}
