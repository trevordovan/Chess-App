package chess.pieces;

import chess.GameBoard;
import chess.Piece;
import chess.enums.Color;
import chess.enums.PieceType;

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
