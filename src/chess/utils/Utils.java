/**
 * @author Trevor Dovan
 * @author Kate Liu
 */ 

package chess.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * This class provides utility methods for converting chess board
 * location string to row and column integers,and row and column
 * integers to corresponding chess board location string, checking
 * valid inputs, and formating strings with a captical letter at the start.
/
 */
public class Utils
{   
    /**
     * A map that contains the column letter as the key and the corresponding column index as the value.
     */
    private static final Map<String, Integer> COLUMN_MAP = new HashMap<>();
    
    static {
        COLUMN_MAP.put("a", 0);
        COLUMN_MAP.put("b", 1);
        COLUMN_MAP.put("c", 2);
        COLUMN_MAP.put("d", 3);
        COLUMN_MAP.put("e", 4);
        COLUMN_MAP.put("f", 5);
        COLUMN_MAP.put("g", 6);
        COLUMN_MAP.put("h", 7);
    }

    /**
     * Converts a chess board location string (e.g. "a3") to corresponding row and column integers.
     * @param input the chess board location string
     * @return an integer array containing the row and column integers respectively
     */
    public static int[] convertFileRankToRowCol(String input)
    {
        String columnStr = input.substring(0, 1);
        int row = 8 - Integer.parseInt(input.substring(1));
        int col = COLUMN_MAP.get(columnStr);
        return new int[] { row, col };
    }    

    /**
     * Converts row and column integers to the corresponding chess board location string (e.g. "a3").
     * @param row the row integer
     * @param col the column integer
     * @return the chess board location string
     */
    public static String convertRowColToFileRank(int row, char col)
    {
        String columnStr = "";
        switch (col) {
            case 0:
                columnStr = "a";
                break;
            case 1:
                columnStr = "b";
                break;
            case 2:
                columnStr = "c";
                break;
            case 3:
                columnStr = "d";
                break;
            case 4:
                columnStr = "e";
                break;
            case 5:
                columnStr = "f";
                break;
            case 6:
                columnStr = "g";
                break;
            case 7:
                columnStr = "h";
                break;
            default:
                break;
        }
        int rowNumber = 8 - row;
        return columnStr + rowNumber;
    } 

    /**
     * Validates the user input for a move in the format of "e2 e4"
     * @param input The user input to validate
     * @return true if the input is valid, false otherwise
     */
    public static boolean isValidInput(String input)
    {
        if (input.length() < 5 || input.length() > 8) {
            return false;
        }

        char fromFile = input.charAt(0);
        char fromRank = input.charAt(1);
        char toFile = input.charAt(3);
        char toRank = input.charAt(4);
        if (fromFile < 'a' || fromFile > 'h' || fromRank < '1' || fromRank > '8' ||
            toFile < 'a' || toFile > 'h' || toRank < '1' || toRank > '8') {
            return false;
        }

        if (input.length() == 8 && !input.substring(6).equals("e.p.")) {
            return false;
        }

        return true;
    }

    /**
     * Capitalizes the first letter of a given string and makes the rest lowercase.
     * @param str the string to be capitalized
     * @return the capitalized string
     */
    public static String capitalize(String str)
    {
        if (str == null || str.length() == 0) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    /**
     * Converts a row and column index in the range of [0, 0] to [7, 7]
     * to an index in the range of 1 to 64.
     * @param row the row index
     * @param col the column index
     * @return the index in the range of 1 to 64
     */
    public static int toIndex(int row, int col)
    {
        return row * 8 + col + 1;
    }

    /**
     * Converts an index in the range of 1 to 64 to a pair of row and column
     * indices in the range of [0, 0] to [7, 7].
     * @param index the index in the range of 1 to 64
     * @return an array of two integers representing the row and column indices
     */
    public static int[] toRowCol(int index)
    {
        int row = (index - 1) / 8;
        int col = (index - 1) % 8;
        return new int[] { row, col };
    }

    /**
     * Determines whether the specified row and column indices are within the bounds of the game board.
     * @param row the row index to check
     * @param col the column index to check
     * @return true if the indices are within bounds, false otherwise
     */
    public static boolean isInBounds(int row, int col)
    {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }
}
