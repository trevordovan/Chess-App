package chess;

public class Gameboard
{
    String[][] board = new String[8][8];
    
    String[] rows = { "8", "7", "6", "5", "4" , "3", "2", "1" };
    String[] columns = {"a" ," b" ,"c" ,"d" ,"e" ,"f" ,"g", "h" };

    public Gameboard()
    {
        init();
    }

    private void init()
    {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 != 0) {
                    board[i][j] = "##";
                }
            }
        }
    }

    public void printBoard()
    {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print("  " + " ");
                }
                else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.print(rows[i]);
            System.out.print("\n");
        }
        for (int i = 0; i < 8; i++) {
            if (i == 0) {
                System.out.print(" " + columns[i] + " ");
                continue;
            }
            System.out.print(columns[i] + "  ");
            
        }
    }
}
