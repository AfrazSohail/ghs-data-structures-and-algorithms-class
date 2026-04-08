package HashiPuzzle;

public class IslandValidation {
    private final Island island;
    private int check;
    private boolean isSolved = false;
    private char dir = ' ';

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

    public char getDir() {
        return dir;
    }

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
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        IslandValidation other = (IslandValidation) obj;
        return this.getIsland().equals(other.getIsland());
    }
}
