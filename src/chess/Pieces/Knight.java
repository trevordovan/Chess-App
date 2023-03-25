package chess.pieces;

import chess.GameBoard;
import chess.Piece;
import chess.enums.Color;
import chess.enums.PieceType;

public class Knight extends Piece
{

    public Knight(Color color, int row, int col)
    {
        super(color, PieceType.KNIGHT, row, col, (color == Color.WHITE) ? "wN" : "bN");
    }

    @Override
    public boolean canMoveTo(int fromRow, int fromCol, int toRow, int toCol, GameBoard board)
    {
        return false;
    }
}
