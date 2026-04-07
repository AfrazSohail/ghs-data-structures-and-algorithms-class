package HashiPuzzle;

public class Island extends Navigable {
    private final char ch;

    public Island(char ch, int x, int y) {
        super(x, y);
        this.ch = ch;
    }

    @Override
    public char getChar() {
        // return (char) (ch - '1' + '❶');
        return ch;
    }
}
