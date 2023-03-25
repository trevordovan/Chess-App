package chess.Pieces;

import chess.Color;
import chess.GameBoard;
import chess.PieceType;

public class Pawn extends Piece
{
    public Pawn(Color color, int row, int col, String location)
    {
        super(color, PieceType.PAWN, row, col, location, (color == Color.WHITE) ? "wp" : "bp");
    }

    @Override
    public boolean canMoveTo(int fromRow, int fromCol, int toRow, int toCol, GameBoard board)
    {
        return false;
    }
}