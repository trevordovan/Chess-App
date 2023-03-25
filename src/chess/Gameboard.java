package chess;

import java.util.Scanner;

import chess.enums.Color;
import chess.pieces.*;

/**
 * The GameBoard class represents a chess game 
 * board with pieces in their starting positions.
 */
public class GameBoard
{ 
    /**
     * The array of pieces on the game board.
     */
    private Piece[][] board;

    /**
     * Initializes the chess game board by placing 
     * all the pieces in their starting positions.
     */
    public GameBoard()
    {
        board = new Piece[8][8];
       
        // Black Pieces
        board[0][0] = new Rook(Color.BLACK, 0, 0);
        board[0][1] = new Knight(Color.BLACK, 0, 1);
        board[0][2] = new Bishop(Color.BLACK, 0, 2);
        board[0][3] = new Queen(Color.BLACK, 0, 3);
        board[0][4] = new King(Color.BLACK, 0, 4);
        board[0][5] = new Bishop(Color.BLACK, 0, 5);
        board[0][6] = new Knight(Color.BLACK, 0, 6);
        board[0][7] = new Rook(Color.BLACK, 0, 7);
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(Color.BLACK, 1, i);
        }

        // White Pieces
        board[7][0] = new Rook(Color.WHITE, 7, 0);
        board[7][1] = new Knight(Color.WHITE, 7, 1);
        board[7][2] = new Bishop(Color.WHITE, 7, 2);
        board[7][3] = new Queen(Color.WHITE, 7, 3);
        board[7][4] = new King(Color.WHITE, 7, 4);
        board[7][5] = new Bishop(Color.WHITE, 7, 5);
        board[7][6] = new Knight(Color.WHITE, 7, 6);
        board[7][7] = new Rook(Color.WHITE, 7, 7);
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(Color.WHITE, 6, i);
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
    public boolean movePiece(int fromRow, int fromCol, int toRow, int toCol)
    {
        Piece selectedPiece = getPieceAt(fromRow, fromCol);
        if (selectedPiece == null) {
            return false;
        }
        if (selectedPiece.canMoveTo(fromRow, fromCol, toRow, toCol, this)) {
            setPieceAt(null, fromRow, fromCol);
            setPieceAt(selectedPiece, toRow, toCol);

            // Check for promotion of a pawn
            if (selectedPiece instanceof Pawn && (toRow == 0 || toRow == 7)) {
                Piece promotedPiece = promotionDialog(selectedPiece.getColor(), toRow, toCol);
                setPieceAt(promotedPiece, toRow, toCol);
            }
            return true;
        }
        return false;
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
     * Determines if the current player is in check.
     * @param currentPlayer the current player
     * @return true if the current player is in checkmate, false otherwise
     */
    public boolean isCheck(Color currentPlayer)
    {
        return false;
    }
    
    /**
     * Determines if the current player is in checkmate.
     * @param currentPlayer the current player
     * @return true if the current player is in checkmate, false otherwise
     */
    public boolean isCheckmate(Color currentPlayer)
    {
        return false;
    }

    public Piece promotionDialog(Color color, int row, int col)
    {
        Piece promotedPiece = null;
        
        System.out.print("Choose your promotion (Q, R, B, N): ");
        Scanner scanner = new Scanner(System.in);
        boolean isValidInput = false;
        while (!isValidInput) {
            String input = scanner.nextLine();
            switch (input) {
                case "Q" : 
                    promotedPiece = new Queen(color, row, col);
                    isValidInput = true;
                    break;
                case "R" : 
                    promotedPiece = new Rook(color, row, col);
                    isValidInput = true;
                    break;
                case "B" :
                    promotedPiece = new Bishop(color, row, col);
                    isValidInput = true;
                    break;
                case "N" :
                    promotedPiece = new Knight(color, row, col);
                    isValidInput = true;
                    break;
                default : 
                    System.out.println("Invalid input.");
                    continue;
            }
        }
        return promotedPiece;
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