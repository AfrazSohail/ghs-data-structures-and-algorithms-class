package Classwork.Unit7.HuffmanEncoding;

public class CharNode implements Comparable<CharNode> {

    public char character;
    public CharNode left = null;
    public CharNode right = null;
    public int frequency;

    public CharNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    @Override
    public int compareTo(CharNode other) {
        if (this.frequency == other.frequency) {
            return Character.compare(this.character, other.character);
        }
        return this.frequency - other.frequency;
    }

    @Override
    public String toString() {
        return "\n" + "-".repeat(12) + "\nChar: " + character + "\nFreq: " + frequency + "\nLeft: " + left + "\nRight: " + right + "\n" + "-".repeat(12);
    }
}
