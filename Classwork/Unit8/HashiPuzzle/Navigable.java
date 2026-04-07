package HashiPuzzle;

public class Navigable {
    private static int id;
    private final int curId;
    protected final int x;
    protected final int y;

    public Navigable(int x, int y) {
        this.curId = id++;
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        Navigable other = (Navigable) obj;
        return this.curId == other.curId;
    }
}
