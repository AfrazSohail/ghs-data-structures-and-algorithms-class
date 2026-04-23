package HashiPuzzle;

/**
 * Tracks the validation state of a single {@link Island} during puzzle solving.
 *
 * <p>
 * This class is used by {@link Hashier} to verify that the total bridge weight
 * connected to an island matches its numeric requirement. It maintains a
 * remaining count and a direction state that cycles through the four cardinal
 * directions (N, E, S, W) to explore bridges.
 *
 * @author AfrazSohail
 * @see Island
 * @see Hashier
 */
public class IslandValidation {

    private final Island island;
    private int check;
    private boolean isSolved = false;
    private char dir = ' ';

    /**
     * Constructs a validation tracker for the given island.
     *
     * <p>
     * The remaining bridge weight is initialized to the island's numeric value.
     *
     * @param island the {@link Island} to track
     */
    public IslandValidation(Island island) {
        this.island = island;
        check = island.getChar() - '0';  // Changed from '1' to '0'
    }

    /**
     * Returns whether all bridges to this island have been found.
     *
     * @return {@code true} if the remaining bridge weight is zero
     */
    public boolean isSolved() {
        return isSolved;
    }

    /**
     * Records that {@code i} units of bridge weight have been crossed.
     *
     * <p>
     * If the weight exceeds the remaining requirement, returns {@code false} to
     * indicate over-bridging. Once the remaining count reaches zero, marks the
     * island as solved.
     *
     * @param i the bridge weight to subtract
     * @return {@code true} if the crossing was valid, {@code false} if
     * over-bridged
     */
    public boolean crossed(int i) {
        if (i > check) {
            return false;
        }
        check -= i;
        if (check == 0) {
            isSolved = true;
        }
        return true;
    }

    /**
     * Returns the island being tracked.
     *
     * @return the {@link Island}
     */
    public Island getIsland() {
        return island;
    }

    /**
     * Returns the current direction being explored.
     *
     * @return 'N', 'E', 'S', 'W', or ' ' (if no direction is set)
     */
    public char getDir() {
        return dir;
    }

    /**
     * Advances to the next direction in the cycle: N → E → S → W → (back to
     * start).
     *
     * @return the new direction, or ' ' if the cycle is complete
     */
    public char nextDir() {
        switch (dir) {
            case ' ':
                dir = 'N';
                break;
            case 'N':
                dir = 'E';
                break;
            case 'E':
                dir = 'S';
                break;
            case 'S':
                dir = 'W';
                break;
            case 'W':
                dir = ' ';
                break;
            default:
                break;
        }
        return dir;
    }

    @Override
    public String toString() {
        return "Island: " + island + "/" + ((isSolved) ? "TRUE" : check);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        IslandValidation other = (IslandValidation) obj;
        return this.getIsland().equals(other.getIsland());
    }
}
