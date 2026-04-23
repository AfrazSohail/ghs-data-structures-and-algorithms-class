package HashiPuzzle;

/**
 * Base class for all grid cells in a Hashi puzzle.
 *
 * <p>
 * A {@code Navigable} represents a single cell at position {@code (x, y)} in
 * the puzzle grid. Each cell is identified by a unique ID for equality testing.
 * Subclasses include {@link Island} (numbered island) and {@link Bridge}
 * (horizontal or vertical connection).
 *
 * @author AfrazSohail
 * @see Island
 * @see Bridge
 */
public class Navigable {

    private static int id;
    private final int curId;
    protected final int x;
    protected final int y;

    /**
     * Constructs a {@code Navigable} cell at the given position.
     *
     * @param x the zero-based column index
     * @param y the zero-based row index
     */
    public Navigable(int x, int y) {
        this.curId = id++;
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the column (x-coordinate) of this cell.
     *
     * @return the zero-based column index
     */
    public int x() {
        return x;
    }

    /**
     * Returns the row (y-coordinate) of this cell.
     *
     * @return the zero-based row index
     */
    public int y() {
        return y;
    }

    /**
     * Returns whether this cell represents an island.
     *
     * @return {@code true} if this is an {@link Island}, {@code false}
     * otherwise
     */
    public boolean isIsland() {
        return this instanceof Island;
    }

    /**
     * Returns a character representation of this cell.
     *
     * <p>
     * Overridden by subclasses to return their specific character. The base
     * implementation returns a space.
     *
     * @return a character representing this cell
     */
    public char getChar() {
        return ' ';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Navigable other = (Navigable) obj;
        return this.curId == other.curId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(curId);
    }
}
