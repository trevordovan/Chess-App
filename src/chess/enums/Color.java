/**
 * @author Trevor Dovan
 * @author Kate Liu
 */ 

package chess.enums;

/**
 *The Color enum represents the available colors in a two-player game, such as chess.
 *The two possible colors are WHITE and BLACK.
 */
public enum Color
{
    WHITE,
    BLACK;

    public Color opposite() {
        return this == WHITE ? BLACK : WHITE;
    }
}
