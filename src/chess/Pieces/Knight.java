package chess.Pieces;

import chess.Color;
import chess.GameBoard;
import chess.PieceType;

public class Knight extends Piece
{

    public Knight(Color color, int row, int col, String location)
    {
        super(color, PieceType.KNIGHT, row, col, location, (color == Color.WHITE) ? "wN" : "bN");
    }

    @Override
    public boolean canMoveTo(int fromRow, int fromCol, int toRow, int toCol, GameBoard board)
    {
        return false;
    }
}
