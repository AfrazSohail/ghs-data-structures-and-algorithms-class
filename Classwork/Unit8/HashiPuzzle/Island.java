package HashiPuzzle;

/**
 * Represents an island cell in a Hashi puzzle.
 *
 * <p>
 * An island has a numeric value (0–8) indicating the number of bridges that
 * must connect to it. The value is stored as a character (e.g. '3') and can be
 * queried via {@link #getChar()}.
 *
 * @author AfrazSohail
 * @see Navigable
 * @see Bridge
 */
public class Island extends Navigable {

    private final char ch;

    /**
     * Constructs an island with the given numeric value at the specified
     * position.
     *
     * @param ch the island's numeric value as a character ('0' through '8')
     * @param x the column index
     * @param y the row index
     */
    public Island(char ch, int x, int y) {
        super(x, y);
        this.ch = ch;
    }

    @Override
    public char getChar() {
        return ch;
    }

    @Override
    public String toString() {
        return "Char: " + ch + " X:" + x + " Y:" + y;
    }
}
