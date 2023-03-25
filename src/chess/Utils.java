package chess;

import java.util.HashMap;
import java.util.Map;

public class Utils
{   
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
        int row = Integer.parseInt(input.substring(1)) - 1;
        int col = COLUMN_MAP.get(columnStr);
        return new int[] { row, col };
    }

    /**
     * Converts row and column integers to the corresponding chess board location string (e.g. "a3").
     * @param row the row integer
     * @param col the column integer
     * @return the chess board location string
     */
    public static String convertRowColToFileRank(int row, int col)
    {
        if (col < 'a' || col > 'h' || row < '1' || row > '8') {
            System.out.println("Invalid input format");
            return null;
        }

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
}
