package chess.pieces;

import chess.GameBoard;
import chess.Piece;
import chess.enums.Color;
import chess.enums.PieceType;

public class Bishop extends Piece
{
    public Bishop(Color color, int row, int col, String location)
    {
        super(color, PieceType.BISHOP, row, col, location, (color == Color.WHITE) ? "wB" : "bB");
    }

    @Override
    public boolean canMoveTo(int fromRow, int fromCol, int toRow, int toCol, GameBoard board)
    {
        return false;
    }
}
