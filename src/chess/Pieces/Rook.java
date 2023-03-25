package chess.Pieces;

import chess.Color;
import chess.GameBoard;
import chess.PieceType;

public class Rook extends Piece
{
    public Rook(Color color, int row, int col, String location)
    {
        super(color, PieceType.ROOK, row, col, location, (color == Color.WHITE) ? "wR" : "bR");
    }

    @Override
    public boolean canMoveTo(int fromRow, int fromCol, int toRow, int toCol, GameBoard board)
    {
        return false;
    }
}
