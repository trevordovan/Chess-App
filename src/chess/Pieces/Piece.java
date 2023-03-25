package chess.Pieces;

import chess.Color;
import chess.GameBoard;
import chess.PieceType;

public abstract class Piece
{
    private Color color;
    private PieceType type;
    private int row;
    private int col;
    private String location;
    private String name;

    public Piece(Color color, PieceType type, int row, int col, String location, String name)
    {
        this.color = color;
        this.type = type;
        this.row = row;
        this.col = col;
        this.location = location;
        this.name = name;
    }

    public abstract boolean canMoveTo(int fromRow, int fromCol, int toRow, int toCol, GameBoard board);

    public Color getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
