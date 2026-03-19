package Classwork.Unit7.HuffmanEncoding;

/**
 * Represents a single node in a Huffman binary tree.
 * <p>
 * Each node holds a character and its frequency count. Internal (non-leaf)
 * nodes use the sentinel character {@code '\uFFFF'} to indicate that they do
 * not represent a real symbol. The tree is ordered by frequency so that lower-
 * frequency nodes are processed first during tree construction.
 * </p>
 *
 * @author AfrazSohail
 * @apiNote Documentation generated with the assistance of GitHub Copilot (AI).
 */
public class CharNode implements Comparable<CharNode> {

    /** The character stored in this node ({@code '\uFFFF'} for internal nodes). */
    public char character;

    /** Left child of this node; {@code null} if this is a leaf. */
    public CharNode left = null;

    /** Right child of this node; {@code null} if this is a leaf. */
    public CharNode right = null;

    /** Frequency (count) of the character represented by this node. */
    public int frequency;

    /**
     * Constructs a new {@code CharNode} with the given character and frequency.
     *
     * @param character the character this node represents
     * @param frequency the frequency (occurrence count) of the character
     */
    public CharNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    /**
     * Returns the height of the subtree rooted at this node.
     * <p>
     * A lone leaf has height {@code 1}. The height of an internal node is
     * {@code 1 + max(leftHeight, rightHeight)}.
     * </p>
     *
     * @return the height of this subtree
     */
    public int getHeight() {
        if (left == null && right == null) {
            return 1;
        }
        int leftHeight = left != null ? left.getHeight() : 0;
        int rightHeight = right != null ? right.getHeight() : 0;
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * Compares this node to another {@code CharNode} for ordering.
     * <p>
     * Nodes are ordered primarily by ascending frequency. When two nodes share
     * the same frequency they are ordered by their character value, ensuring a
     * deterministic sort order needed for consistent tree construction.
     * </p>
     *
     * @param other the node to compare against
     * @return a negative integer, zero, or a positive integer as this node is
     *         less than, equal to, or greater than {@code other}
     */
    @Override
    public int compareTo(CharNode other) {
        if (this.frequency == other.frequency) {
            return Character.compare(this.character, other.character);
        }
        return this.frequency - other.frequency;
    }

    /**
     * Returns a multi-line string representation of this node and its children.
     *
     * @return a formatted string showing the character, frequency, and child nodes
     */
    @Override
    public String toString() {
        return "\n" + "-".repeat(12) + "\nChar: " + character + "\nFreq: " + frequency + "\nLeft: " + left + "\nRight: " + right + "\n" + "-".repeat(12);
    }
}
