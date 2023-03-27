/**
 * @author Trevor Dovan
 * @author Kate Liu
 */

package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

import chess.enums.Color;
import chess.pieces.*;
import chess.utils.Utils;

/**
 * The GameBoard class represents a chess game
 * board with pieces in their starting positions.
 */
public class Gameboard {
    /**
     * The array of pieces on the game board.
     */
    private Piece[][] board;

    /**
     * The color of the current player
     */
    private Color currentPlayer;

    /**
     * The boolean matrix to track whether a piece has moved or not
     */
    private boolean[][] hasMoved;

    /**
     * Initializes the chess game board by placing
     * all the pieces in their starting positions.
     */
    public Gameboard() {
        board = new Piece[8][8];
        hasMoved = new boolean[8][8];

        // Black Pieces
        board[0][0] = new Rook(Color.BLACK, 0, 0, Color.WHITE);
        board[0][1] = new Knight(Color.BLACK, 0, 1);
        board[0][2] = new Bishop(Color.BLACK, 0, 2);
        board[0][3] = new Queen(Color.BLACK, 0, 3);
        board[0][4] = new King(Color.BLACK, 0, 4);
        board[0][5] = new Bishop(Color.BLACK, 0, 5);
        board[0][6] = new Knight(Color.BLACK, 0, 6);
        board[0][7] = new Rook(Color.BLACK, 0, 7, Color.WHITE);
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(Color.BLACK, 1, i);
        }

        // White Pieces
        board[7][0] = new Rook(Color.WHITE, 7, 0, Color.BLACK);
        board[7][1] = new Knight(Color.WHITE, 7, 1);
        board[7][2] = new Bishop(Color.WHITE, 7, 2);
        board[7][3] = new Queen(Color.WHITE, 7, 3);
        board[7][4] = new King(Color.WHITE, 7, 4);
        board[7][5] = new Bishop(Color.WHITE, 7, 5);
        board[7][6] = new Knight(Color.WHITE, 7, 6);
        board[7][7] = new Rook(Color.WHITE, 7, 7, Color.BLACK);
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
     * 
     * @param fromRow the row index of the source square
     * @param fromCol the column index of the source square
     * @param toRow   the row index of the destination square
     * @param toCol   the column index of the destination square
     * @return true if the move is successfully executed, false otherwise
     */
    public boolean movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        Piece selectedPiece = getPieceAt(fromRow, fromCol);
        if (selectedPiece == null) {
            return false;
        }

        if (selectedPiece.getColor() != currentPlayer) {
            return false;
        }

        if (selectedPiece.canMoveTo(fromRow, fromCol, toRow, toCol, this)) {
            setPieceAt(null, fromRow, fromCol);
            setPieceAt(selectedPiece, toRow, toCol);
            selectedPiece.setRow(toRow);
            selectedPiece.setCol(toCol);
            selectedPiece.setMoved(true); // set hasMoved flag to true

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
     * Determines whether the specified color is in check on the game board.
     * 
     * @param currentPlayer the color to check for check
     * @return true if the specified color is in check, false otherwise
     */
    public boolean isCheck(Color currentPlayer) {
        // Find the position of the king of the specified color
        int[] kingPos = findKing(currentPlayer);
        if (kingPos == null) {
            // King not found, cannot be in check
            return false;
        }

        // Check if any opposing pieces can attack the king
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col];
                if (piece != null && piece.getColor() != currentPlayer) {
                    // Check if the piece can attack the king
                    if (piece.canMoveTo(row, col, kingPos[0], kingPos[1], this)) {
                        // King is in check
                        return true;
                    }
                }
            }
        }

        // King is not in check
        return false;
    }

    /**
     * Determines whether the specified color is in checkmate on the game board.
     * 
     * @param currentPlayer the color to check for checkmate
     * @return true if the specified color is in checkmate, false otherwise
     */
    public boolean isCheckmate(Color currentPlayer) {
        if (!isCheck(currentPlayer)) {
            return false;
        }

        int[] kingPos = findKing(currentPlayer);
        if (kingPos == null) {
            return false;
        }

        // Check if the king can move to a safe square
        for (int row = kingPos[0] - 1; row <= kingPos[0] + 1; row++) {
            for (int col = kingPos[1] - 1; col <= kingPos[1] + 1; col++) {
                if (row == kingPos[0] && col == kingPos[1]) {
                    continue;
                }
                if (Utils.isInBounds(row, col)
                        && (board[row][col] == null || board[row][col].getColor() != currentPlayer)) {
                    Piece tempFromPiece = getPieceAt(kingPos[0], kingPos[1]);
                    Piece tempToPiece = getPieceAt(row, col);
                    if (movePiece(kingPos[0], kingPos[1], row, col)) {
                        if (!isCheck(currentPlayer)) {
                            // undo move
                            setPieceAt(tempFromPiece, kingPos[0], kingPos[1]);
                            setPieceAt(tempToPiece, row, col);
                            return false;
                        }
                        // undo move
                        setPieceAt(tempFromPiece, kingPos[0], kingPos[1]);
                        setPieceAt(tempToPiece, row, col);
                    }
                }
            }
        }

        // Check if any piece can block or capture the attacking piece
        List<Piece> pieces = getPieces(currentPlayer);
        for (Piece piece : pieces) {
            Piece tempPiece = getPieceAt(piece.getRow(), piece.getCol());
            int tempRow = tempPiece.getRow();
            int tempCol = tempPiece.getCol();
            Set<Integer> attackSquares = piece.getAttackSquares(this);
            for (int attackSquareIndex : attackSquares) {
                int[] attackRowCol = Utils.toRowCol(attackSquareIndex);
                Piece tempAttackSquarePiece = getPieceAt(attackRowCol[0], attackRowCol[1]);
                if (movePiece(piece.getRow(), piece.getCol(), attackRowCol[0], attackRowCol[1])) {
                    if (!isCheck(currentPlayer)) {
                        // undo move
                        setPieceAt(tempPiece, tempRow, tempCol);
                        setPieceAt(tempAttackSquarePiece, attackRowCol[0], attackRowCol[1]);
                        return false;
                    }
                    // undo move
                    setPieceAt(tempPiece, tempRow, tempCol);
                    setPieceAt(tempAttackSquarePiece, attackRowCol[0], attackRowCol[1]);
                }
            }
        }

        // King cannot escape check and no other piece can block or capture the
        // attacking piece
        return true;
    }

    /**
     * Prompts the user to choose a piece to promote a pawn to and returns the promoted piece.
     * @param color the color of the pawn being promoted
     * @param row the row of the pawn being promoted
     * @param col the column of the pawn being promoted
     * @return the piece that the pawn is being promoted to
     */
    public Piece promotionDialog(Color color, int row, int col)
    {
        Piece promotedPiece = null;
        
        System.out.print("Choose your promotion (Q, R, B, N): ");
        Scanner scanner = new Scanner(System.in); // not closed because we are still scanning for moves after
        boolean isValidInput = false;
        while (!isValidInput) {
            String input = scanner.nextLine();
            switch (input) {
                case "Q" : 
                    promotedPiece = new Queen(color, row, col);
                    isValidInput = true;
                    break;
                case "R" : 
                    promotedPiece = new Rook(color, row, col, color.opposite());
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
    public void printBoard() {
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

    /**
     * Finds the position of the king of the specified color on the game board.
     * 
     * @param color the color of the king to find
     * @return the position of the king of the specified color, or null if not found
     */
    public int[] findKing(Color color) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = getPieceAt(row, col);
                if (piece != null && piece instanceof King && piece.getColor() == color) {
                    return new int[] { row, col };
                }
            }
        }
        return null;
    }

    /**
     * Checks if a piece at the specified row and column has been moved before.
     * @param row the row index of the piece
     * @param col the column index of the piece
     * @return true if the piece has been moved before, false otherwise
     */
    public boolean hasMoved(int row, int col)
    {
        return hasMoved[row][col];
    }

    /**
     * Sets the hasMoved flag for the piece at the specified row and column to true.
     * @param row the row index of the piece
     * @param col the column index of the piece
     */
    public void setMoved(int row, int col)
    {
        hasMoved[row][col] = true;
    }

    /**
     * Get the Piece at a specified row and column index.
     * 
     * @param row row index
     * @param col col index
     * @return Piece at location, null if empty
     */
    public Piece getPieceAt(int row, int col) {
        return board[row][col];
    }

    /**
     * Sets the specified piece at the given row and column on the chess board.
     * 
     * @param piece the piece to set on the board
     * @param row   the row index where the piece is to be placed
     * @param col   the column index where the piece is to be placed
     */
    public void setPieceAt(Piece piece, int row, int col) {
        if (piece == null) {
            board[row][col] = piece;
            return;
        }
        piece.setRow(row);
        piece.setCol(col);
        board[row][col] = piece;
    }

    /**
     * Returns a list of all pieces for the given color on the board.
     * 
     * @param color the color of the pieces to retrieve
     * @return a list of all pieces for the given color
     */
    private List<Piece> getPieces(Color color) {
        List<Piece> pieces = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = getPieceAt(row, col);
                if (piece != null && piece.getColor() == color) {
                    pieces.add(piece);
                }
            }
        }
        return pieces;
    }

    /**
     * Returns the current player of the game.
     * 
     * @return the color of the current player
     */
    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Sets the current player of the game to the specified color.
     * 
     * @param color the color to set as the current player
     */
    public void setCurrentPlayer(Color color) {
        currentPlayer = color;
    }

    /**
     * Returns a set of all squares on the board that are currently being attacked by the enemy pieces
     * of the specified player color.
     * @param playerColor the color of the player whose enemy attacks are being checked
     * @return a set of integers representing the squares being attacked by the enemy pieces
     */
    public Set<Integer> getEnemyAttackSquares(Color playerColor) {
        Set<Integer> enemyAttacks = new HashSet<>();
        Color enemyColor = playerColor.opposite();

        // Loop through all pieces on the board
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = getPieceAt(row, col);
                if (piece != null && piece.getColor() == enemyColor) {
                    // If the piece belongs to the opposing player, add its attack squares
                    enemyAttacks.addAll(piece.getAttackSquares(this));
                }
            }
        }

        return enemyAttacks;
    }

    /**
     * Returns true if the given square on the board is being attacked by a piece of
     * the given color.
     * @param row   the row of the square to check
     * @param col   the column of the square to check
     * @param color the color of the attacking pieces to look for
     * @return true if the square is being attacked, false otherwise
     */
    public boolean isSquareAttacked(int row, int col, Color color) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Piece piece = board[i][j];
                if (piece != null && piece.getColor() == color && piece.canMoveTo(i, j, row, col, this)) {
                    return true;
                }
            }
        }
        return false;
    }    

}