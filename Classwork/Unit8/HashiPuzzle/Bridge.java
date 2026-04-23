package HashiPuzzle;

/**
 * Represents a bridge connection between islands in a Hashi puzzle.
 *
 * <p>
 * A bridge has two properties: {@code direction} (horizontal or vertical) and
 * {@code weight} (1 or 2, indicating single or double bridge). Bridges connect
 * adjacent islands along a single row or column.
 *
 * @author AfrazSohail
 * @see Navigable
 * @see Island
 */
public class Bridge extends Navigable {

    private final char dir;
    private final int weight;

    /**
     * Constructs a bridge with the given direction and weight at the specified
     * position.
     *
     * @param dir the bridge direction: '|' for vertical, '-' for horizontal
     * @param weight the bridge strength: 1 for single, 2 for double
     * @param x the column index
     * @param y the row index
     */
    public Bridge(char dir, int weight, int x, int y) {
        super(x, y);
        this.dir = dir;
        this.weight = weight;
    }

    /**
     * Returns the direction of this bridge.
     *
     * @return '|' for vertical, '-' for horizontal
     */
    public char getDir() {
        return dir;
    }

    /**
     * Tests whether this bridge aligns with the given direction.
     *
     * <p>
     * Vertical bridges match 'N' (north) and 'S' (south); horizontal bridges
     * match 'E' (east) and 'W' (west).
     *
     * @param dir the direction to test: 'N', 'S', 'E', or 'W'
     * @return {@code true} if the bridge aligns with this direction
     */
    public boolean matchDir(char dir) {
        if (dir == this.dir) {
            return true;
        }

        switch (this.dir) {
            case '|':
                return (dir == 'N' || dir == 'S');
            case '-':
                return (dir == 'E' || dir == 'W');
            default:
                return false;
        }
    }

    /**
     * Returns the weight (strength) of this bridge.
     *
     * @return 1 for single bridge, 2 for double bridge
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Returns a character representation of this bridge for display.
     *
     * @return '|' or '║' for vertical (single or double), '-' or '═' for
     * horizontal
     */
    @Override
    public char getChar() {
        if (dir == '|') {
            return weight == 1 ? '|' : '║';
        }
        return weight == 1 ? '-' : '═';
    }
}
