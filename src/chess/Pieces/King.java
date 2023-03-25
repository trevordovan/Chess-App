package chess.Pieces;

import chess.Color;
import chess.GameBoard;
import chess.PieceType;

public class King extends Piece
{
    public King(Color color, int row, int col, String location)
    {
        super(color, PieceType.KING, row, col, location, (color == Color.WHITE) ? "wK" : "bK");
    }

    @Override
    public boolean canMoveTo(int fromRow, int fromCol, int toRow, int toCol, GameBoard board)
    {
        return false;
    }
}
