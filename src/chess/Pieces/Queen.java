package chess.Pieces;

import chess.Color;
import chess.GameBoard;
import chess.PieceType;

public class Queen extends Piece
{
    public Queen(Color color, int row, int col, String location)
    {
        super(color, PieceType.QUEEN, row, col, location, (color == Color.WHITE) ? "wQ" : "bQ");
    }

    @Override
    public boolean canMoveTo(int fromRow, int fromCol, int toRow, int toCol, GameBoard board)
    {
        return false;
    }
}
