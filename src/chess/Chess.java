package chess;

/**
 * The main class that launches the chess game.
 */
public class Chess
{
    /**
     * The main method that launches the chess game by creating a new Game instance and calling its play method.
     * @param args the command line arguments
     * @throws Exception if an error occurs while playing the game
     */
    public static void main(String[] args) throws Exception
    {
        Game game = new Game();
        game.play();
    }
}
