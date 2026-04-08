package HashiPuzzle;

public class IslandValidation {
    private final Island island;
    private int check;
    private boolean isSolved = false;

    public IslandValidation(Island island) {
        this.island = island;
        check = island.getChar() - '0';  // Changed from '1' to '0'
    }

    public boolean isSolved() {
        return isSolved;
    }

    public boolean crossed(int i) {
        if (i > check)
            return false;
        check -= i;
        if (check == 0)
            isSolved = true;
        return true;
    }

    public Island getIsland() {
        return island;
    }
}
