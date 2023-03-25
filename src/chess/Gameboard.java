package chess;

import chess.Pieces.*;

public class GameBoard
{ 
    char[] columns = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };

    private Piece[][] board;

    /**
     * Initializes the chess game board by placing all the pieces in their
     * starting positions.
     */
    public GameBoard()
    {
        board = new Piece[8][8];
       
        // Black Pieces
        board[0][0] = new Rook(Color.BLACK, 0, 0, "a8");
        board[0][1] = new Knight(Color.BLACK, 0, 1, "b8");
        board[0][2] = new Bishop(Color.BLACK, 0, 2, "c8");
        board[0][3] = new Queen(Color.BLACK, 0, 3, "d8");
        board[0][4] = new King(Color.BLACK, 0, 4, "e8");
        board[0][5] = new Bishop(Color.BLACK, 0, 5, "f8");
        board[0][6] = new Knight(Color.BLACK, 0, 6, "g8");
        board[0][7] = new Rook(Color.BLACK, 0, 7, "h8");
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(Color.BLACK, 1, i, columns[i] + "7");
        }

        // White Pieces
        board[7][0] = new Rook(Color.WHITE, 7, 0, "a1");
        board[7][1] = new Knight(Color.WHITE, 7, 1, "b1");
        board[7][2] = new Bishop(Color.WHITE, 7, 2, "c1");
        board[7][3] = new Queen(Color.WHITE, 7, 3, "d1");
        board[7][4] = new King(Color.WHITE, 7, 4, "e1");
        board[7][5] = new Bishop(Color.WHITE, 7, 5, "f1");
        board[7][6] = new Knight(Color.WHITE, 7, 6, "g1");
        board[7][7] = new Rook(Color.WHITE, 7, 7, "h1");
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(Color.WHITE, 6, i, columns[i] + "2");
        }

        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = null;
            }
        }
    }

    /**
     * Moves a piece from the specified source square to the specified
     * destination square on the game board.
     * @param fromRow the row index of the source square
     * @param fromCol the column index of the source square
     * @param toRow   the row index of the destination square
     * @param toCol   the column index of the destination square
     */
    public void movePiece(int fromRow, int fromCol, int toRow, int toCol)
    {
        
    }

    /**
     * Get the Piece at a specified row and column index.
     * @param row row index
     * @param col col index
     * @return Piece at location, null if empty
     */
    public Piece getPieceAt(int row, int col)
    {
        return board[row][col];
    } 

    /**
     * Sets the specified piece at the given row and column on the chess board.
     * @param piece the piece to set on the board
     * @param row the row index where the piece is to be placed
     * @param col the column index where the piece is to be placed
     */
    public void setPieceAt(Piece piece, int row, int col)
    {
        board[row][col] = piece;
    }
    
    /**
     * Determines if the current player is in checkmate.
     * @param currentPlayer the current player
     * @return true if the current player is in checkmate, false otherwise
     */
    public boolean isCheckmate(Color currentPlayer)
    {
        return true;
    }

    /**
     * Prints the current state of the game board.
     */
    public void printBoard()
    {
        System.out.println();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    if ((i + j) % 2 == 0) {
                        System.out.print("   ");
                    } else {
                        System.out.print("## ");
                    }
                } else {
                    System.out.print(board[i][j].getName() + " ");
                }
            }
            System.out.print(8 - i);
            System.out.println();
        }

        System.out.print(" ");
        for (char column = 'a'; column <= 'h'; column++) {
            System.out.print(column + "  ");
        }
        System.out.println("\n");
    }
}