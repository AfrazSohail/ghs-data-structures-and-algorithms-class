package HashiPuzzle;

import java.util.HashSet;

/**
 * Parses and stores a Hashi puzzle as a 2D grid of {@link Navigable} cells.
 *
 * <p>
 * This class converts a text representation of a puzzle (using digits for
 * islands and {@code |}, {@code -}, {@code =}, {@code #} for bridges) into a
 * structured {@code Navigable[][]} grid. It also maintains a set of all islands
 * for quick lookup and validation.
 *
 * <h3>Character Encoding</h3>
 * <ul>
 * <li>{@code '0'–'8'} → {@link Island} with numeric value
 * <li>{@code '|'} → {@link Bridge} (vertical, weight 1)
 * <li>{@code '#'} → {@link Bridge} (vertical, weight 2)
 * <li>{@code '-'} → {@link Bridge} (horizontal, weight 1)
 * <li>{@code '='} → {@link Bridge} (horizontal, weight 2)
 * <li>anything else → null (water/empty)
 * </ul>
 *
 * @author AfrazSohail
 * @see Navigable
 * @see Island
 * @see Bridge
 */
public class Map {

    private final Navigable[][] map;
    private HashSet<Island> islandSet = new HashSet<Island>();

    /**
     * Returns the minimum column index (always 0).
     *
     * @return 0
     */
    public int xMin() {
        return 0;
    }

    /**
     * Returns the minimum row index (always 0).
     *
     * @return 0
     */
    public int yMin() {
        return 0;
    }

    /**
     * Returns the maximum column index (width − 1).
     *
     * @return the rightmost column index, or −1 if the grid is empty
     */
    public int xMax() {
        return map.length > 0 ? map[0].length - 1 : -1;
    }

    /**
     * Returns the maximum row index (height − 1).
     *
     * @return the bottommost row index
     */
    public int yMax() {
        return map.length - 1;
    }

    /**
     * Constructs a Map by parsing the given text representation.
     *
     * <p>
     * The string is split into lines, and each character is mapped to a
     * {@link Navigable} cell (island, bridge, or null for water).
     *
     * @param str the puzzle text, with lines separated by newlines
     */
    public Map(String str) {
        int[] size = getSize(str);
        map = new Navigable[size[0]][size[1]];
        makeNavigable(str, size[0], size[1]);
    }

    /**
     * Parses the grid dimensions from a text representation.
     *
     * @param str the puzzle text
     * @return a 2-element array: {@code [rowCount, maxColumnCount]}
     */
    public int[] getSize(String str) {
        int[] size = new int[2];
        String[] lines = str.split("\n");
        size[0] = lines.length;
        for (String row : lines) {
            size[1] = Math.max(size[1], row.length());
        }
        return size;
    }

    /**
     * Populates the map grid with {@link Navigable} cells by parsing the text
     * representation.
     *
     * @param str the puzzle text
     * @param rowLength the number of rows
     * @param colLength the number of columns
     */
    public void makeNavigable(String str, int rowLength, int colLength) {
        String[] lines = str.split("\n");
        for (int y = 0; y < lines.length; y++) {
            for (int x = 0; x < lines[y].length(); x++) {
                if (map[y][x] == null) {
                    map[y][x] = getNavigable(lines[y].toCharArray()[x], x, y);
                }
            }
        }
    }

    /**
     * Retrieves the {@link Navigable} cell at the given position.
     *
     * @param x the column index
     * @param y the row index
     * @return the {@link Navigable} at {@code (x, y)}, or null for water
     */
    public Navigable getNavigable(int x, int y) {
        return map[y][x];
    }

    private Navigable getNavigable(char ch, int x, int y) {
        if (Character.isDigit(ch)) {
            Island island = new Island(ch, x, y);
            islandSet.add(island);
            return island;
        }
        Navigable navigable;
        switch (ch) {
            case '#':
                navigable = new Bridge('|', 2, x, y);
                break;
            case '|':
                navigable = new Bridge('|', 1, x, y);
                break;
            case '-':
                navigable = new Bridge('-', 1, x, y);
                break;
            case '=':
                navigable = new Bridge('-', 2, x, y);
                break;
            default:
                navigable = null;
        }
        return navigable;
    }

    /**
     * Returns a shallow copy of the grid.
     *
     * @return a copy of the {@code Navigable[][]} grid
     */
    public Navigable[][] getMap() {
        Navigable[][] clone = new Navigable[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                clone[i][j] = map[i][j];
            }
        }
        return clone;
    }

    /**
     * Returns the set of all islands in the puzzle.
     *
     * @return a {@link HashSet} containing every {@link Island} in the grid
     */
    public HashSet<Island> islandSet() {
        return islandSet;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Navigable[] row : map) {
            for (Navigable navigable : row) {
                if (navigable == null) {
                    sb.append("   ");
                } else {
                    if (navigable instanceof Island) {
                        sb.append("[").append(navigable.getChar()).append("]");
                    } else {
                        if (((Bridge) navigable).getDir() == '|') {
                            sb.append(" " + navigable.getChar() + " ");
                        } else {
                            sb.append((navigable.getChar() + "").repeat(3));
                        }
                    }
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
