package chess;

/**
 * This class represents a move made in a game of chess.
 * It stores the starting row and column, as well as the ending row and column,
 * along with the piece that was moved.
 */
public class Move
{
    /** The starting row of the move. */
    private int fromRow;

    /** The starting column of the move. */
    private int fromCol;

    /** The ending row of the move. */
    private int toRow;

    /** The ending column of the move. */
    private int toCol;

    /** The piece that was moved. */
    private Piece piece;

    /**
     * Constructor for the Move class.
     * @param fromRow the starting row of the move
     * @param fromCol the starting column of the move
     * @param toRow the ending row of the move
     * @param toCol the ending column of the move
     * @param piece the piece that was moved
     */
    public Move(int fromRow, int fromCol, int toRow, int toCol, Piece piece)
    {
        this.fromRow = fromRow;
        this.fromCol = fromCol;
        this.toRow = toRow;
        this.toCol = toCol;
        this.piece = piece;
    }

    /**
     * Returns the starting row of the move.
     * @return the starting row of the move
     */
    public int getFromRow()
    {
        return fromRow;
    }

    /**
     * Returns the starting column of the move.
     * @return the starting column of the move
     */
    public int getFromCol()
    {
        return fromCol;
    }

    /**
     * Returns the ending row of the move.
     * @return the ending row of the move
     */
    public int getToRow()
    {
        return toRow;
    }

    /**
     * Returns the ending column of the move.
     * @return the ending column of the move
     */
    public int getToCol()
    {
        return toCol;
    }

    /**
     * Returns the piece that was moved.
     * @return the piece that was moved
     */
    public Piece getPiece()
    {
        return piece;
    }
}
