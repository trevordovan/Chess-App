package chess.Pieces;

import chess.Color;
import chess.GameBoard;
import chess.PieceType;

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
