/**
 * @author Trevor Dovan
 * @author Kate Liu
 */ 

package chess;

import java.util.Set;

import chess.enums.Color;

/**
 * Represents a chess piece on the game board.
 */
public abstract class Piece
{
    /** The color of the piece. */
    private Color color;

    /** The row on the game board where the piece is located. */
    private int row;

    /** The column on the game board where the piece is located. */
    private int col;
    
    /** The name of the piece, typically a two-character string like "wK" or "bQ". */
    private String name;

    /** The boolean representing whether or not the piece has moved. */
    private Boolean hasMoved;

    /** The color of the piece attacking, black or white */
    protected Color attackingColor;

    /**
     * Constructs a new Piece object with the given
     * color, type, row, column, location, and name.
     * @param color the color of the piece (either Color.WHITE or Color.BLACK)
     * @param row the row of the piece on the board (0-7)
     * @param col the column of the piece on the board (0-7)
     * @param name the name of the piece (e.g. "wK" for a white king, "bP" for a black pawn, etc.)
     */
    public Piece(Color color, int row, int col, String name)
    {
        this.color = color;
        this.row = row;
        this.col = col;
        this.name = name;
        this.hasMoved = false;
    }

    /**
     * Determines whether or not the piece can move from the specified 
     * starting position to the specified ending positionon the given
     * game board. This method should be overridden by subclasses to 
     * implement piece-specific move rules.
     * @param fromRow the starting row of the piece
     * @param fromCol the starting column of the piece
     * @param toRow the ending row of the piece
     * @param toCol the ending column of the piece
     * @param board the game board on which the move is being made
     * @return true if the move is valid, false otherwise
     */
    public abstract boolean canMoveTo(int fromRow, int fromCol, int toRow, int toCol, Gameboard board);

    /**
     * Returns a set of integers representing the squares that this piece is attacking on the game board.
     * This method should be overridden by subclasses to implement piece-specific attack rules.
     * @param board the game board on which the piece is attacking
     * @return a set of integers representing the squares that this piece is attacking
     */
    public abstract Set<Integer> getAttackSquares(Gameboard board);

    /**
     * Sets the attacking color of the game to the specified color.
     * The attacking color is the color of the player who is currently attacking.
     * @param color the color to set as the attacking color
     */
    public void setAttackingColor(Color color)
    {
        this.attackingColor = color;
    }

    /**
     * Gets the color of the piece.
     * @return the color of the piece
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * Gets the row of the piece on the game board.
     * @return the row of the piece
     */
    public int getRow()
    {
        return row;
    }

    /**
     * Gets the column of the piece on the game board.
     * @return the column of the piece
     */
    public int getCol()
    {
        return col;
    }

    /**
     * Sets the row of the piece on the game board.
     * @param row the new row of the piece
     */
    public void setRow(int row)
    {
        this.row = row;
    }

    /**
     * Sets the column of the piece on the game board.
     * @param col the new column of the piece
     */
    public void setCol(int col)
    {
        this.col = col;
    }

    /**
     * Gets the name of the piece, as a string
     * that includes its color and type (e.g., "wKnight").
     * @return the name of the piece
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name of the piece, as a string
     * that includes its color and type (e.g., "wKnight").
     * @param name the new name of the piece
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * Sets the moved status of the piece.
     * @param hasMoved true if the piece has been moved, false otherwise
     */
    public void setMoved(boolean hasMoved)
    {
        this.hasMoved = hasMoved;
    }
}
