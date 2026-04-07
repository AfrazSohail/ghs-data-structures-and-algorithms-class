package HashiPuzzle;

public class Island extends Navigable {
    private final char ch;

    public Island(char ch, int x, int y) {
        super(x, y);
        this.ch = ch;
    }

    public char getChar() {
        return ch;
    }
}
