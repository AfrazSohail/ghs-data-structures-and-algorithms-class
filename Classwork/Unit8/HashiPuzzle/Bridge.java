package HashiPuzzle;

public class Bridge extends Navigable {
    private final char dir;
    private final int weight;

    public Bridge(char dir, int weight, int x, int y) {
        super(x, y);
        this.dir = dir;
        this.weight = weight;
    }

    public char getDir() {
        return dir;
    }

    public boolean matchDir(char dir) {
        if (dir == this.dir)
            return true;

        switch (this.dir) {
            case '|':
                return (dir == 'N' || dir == 'S');
            case '-':
                return (dir == 'E' || dir == 'W');
            default:
                return false;
        }
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public char getChar() {
        if (dir == '|') {
            return weight == 1 ? '|' : '║';
        }
        return weight == 1 ? '-' : '═';
    }
}
