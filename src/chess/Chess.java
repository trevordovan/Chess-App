package chess;

import java.util.Scanner;

public class Chess {
    public static void main(String[] args) throws Exception {

        Gameboard board = new Gameboard();

        String[] player = { "White's", "Black's" };
        int turn = 0;

        Scanner scanner = new Scanner(System.in);
        while (true) {
            
            board.printBoard();
            System.out.print(player[turn] + " move: ");
            String input = scanner.nextLine();

            switch (input) {
                case "q": {
                    return;
                }
                default : {
                    //move(input);
                    break;
                }
            }
            if (turn == 0) { turn = 1; } else { turn = 0 ; }
        }
    }
}
