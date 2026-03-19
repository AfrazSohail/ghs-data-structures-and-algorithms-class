package Classwork.Unit7.HuffmanEncoding;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Implements Huffman encoding and decoding for a given input text.
 * <p>
 * On construction the class:
 * <ol>
 *   <li>Counts character frequencies in the input string.</li>
 *   <li>Builds a Huffman binary tree by repeatedly merging the two lowest-
 *       frequency nodes.</li>
 *   <li>Derives a variable-length binary code for each character by traversing
 *       the tree (left = {@code '0'}, right = {@code '1'}).</li>
 *   <li>Encodes the original text using those codes.</li>
 * </ol>
 * The resulting encoded bit-string and the tree root can be retrieved through
 * the public accessor methods.
 * </p>
 *
 * @author AfrazSohail
 * @apiNote Documentation generated with the assistance of GitHub Copilot (AI).
 */
public class Huffman {

    /** Working list used both to accumulate leaf nodes and to build the tree. */
    private final ArrayList<CharNode> charTree = new ArrayList<>();

    /**
     * Maps each binary code (e.g. {@code "010"}) to the character it represents.
     * Built during the tree-scan phase; used for both encoding and decoding.
     */
    private final HashMap<String, Character> encodings = new HashMap<>();

    /** The original (decoded) text supplied to the constructor. */
    private String text;

    /** The Huffman-encoded binary string of {@link #text}. */
    private String encodedText;

    /** Root of the completed Huffman binary tree. */
    private CharNode root;

    /**
     * Constructs a {@code Huffman} instance for the given text string.
     * <p>
     * The full encoding pipeline (frequency count → tree build → code scan →
     * text encode) is executed immediately inside the constructor.
     * </p>
     *
     * @param text the plain text to encode; leading/trailing whitespace is
     *             trimmed before frequency counting
     */
    public Huffman(String text) {
        this.text = text;
        buildArray(text.trim());
        buildTree();
        scanTree(charTree.get(0), "");
        encodeText();
    }

    /**
     * Convenience constructor that accepts a {@code char[]} instead of a
     * {@code String}.
     *
     * @param chars the character array to encode
     */
    public Huffman(char[] chars) {
        this(new String(chars));
    }

    /**
     * Counts the frequency of each character in the text and populates
     * {@link #charTree} with one leaf node per unique character, sorted in
     * ascending frequency order.
     *
     * @param text the text to analyse (already trimmed)
     */
    private void buildArray(String text) {
        for (char c : text.toCharArray()) {
            boolean found = false;
            for (CharNode node : charTree) {
                if (node.character == c) {
                    node.frequency++;
                    found = true;
                    break;
                }
            }
            if (!found) {
                charTree.add(new CharNode(c, 1));
            }
        }

        charTree.sort(null);
    }

    /**
     * Builds the Huffman binary tree from the sorted leaf nodes in
     * {@link #charTree}.
     * <p>
     * Repeatedly takes the two lowest-frequency nodes, creates an internal
     * parent node whose frequency is their sum, and re-inserts it into the
     * sorted list. Stops when only the root remains.
     * </p>
     */
    private void buildTree() {
        int currentIndex = 0;
        while (charTree.size() > 1) {
            CharNode left = charTree.get(currentIndex);
            CharNode right = charTree.get(currentIndex + 1);
            CharNode parent = new CharNode('\uFFFF', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;
            charTree.remove(currentIndex);
            charTree.remove(currentIndex);
            charTree.add(parent);
            charTree.sort(null);
        }
        root = charTree.get(0);
    }

    /**
     * Recursively traverses the Huffman tree and records the binary code path
     * for every leaf node into {@link #encodings}.
     * <p>
     * A left branch appends {@code '0'} and a right branch appends {@code '1'}
     * to the running {@code path}.
     * </p>
     *
     * @param node the current tree node being visited
     * @param path the binary string accumulated on the path from the root to
     *             {@code node}
     */
    private void scanTree(CharNode node, String path) {
        if (node.character != '\uFFFF') {
            encodings.put(path, node.character);
        } else {
            scanTree(node.left, path + "0");
            scanTree(node.right, path + "1");
        }
    }

    /**
     * Encodes {@link #text} using the codes stored in {@link #encodings} and
     * stores the result in {@link #encodedText}.
     */
    private void encodeText() {
        StringBuilder encoded = new StringBuilder();
        for (char c : text.toCharArray()) {
            for (String key : encodings.keySet()) {
                if (encodings.get(key) == c) {
                    encoded.append(key);
                    break;
                }
            }
        }
        encodedText = encoded.toString();
    }

    /**
     * Decodes a Huffman-encoded bit-string back to plain text using
     * {@link #encodings}.
     * <p>
     * Reads bits one at a time from {@code encodedText}, growing a candidate
     * code until it matches a known code in the encoding table, then appends
     * the corresponding character and resets the candidate.
     * </p>
     *
     * @param encodedText the binary string to decode
     * @return the decoded plain-text string
     */
    public String decodeText(String encodedText) {
        StringBuilder encoded = new StringBuilder(encodedText);
        StringBuilder decoded = new StringBuilder();

        StringBuilder code = new StringBuilder();
        int length = -1;

        while (!encoded.isEmpty()) {
            length++;
            code.append(encoded.charAt(length));
            if (encodings.containsKey(code.toString())) {
                char character = encodings.get(code.toString());
                decoded.append(character);
                encoded.delete(0, length+1);
                code = new StringBuilder();
                length = -1;
            }
        }
        return decoded.toString();
    }

    // private char getChar(String code) {
    //     char character = '\uFFFF';
    //     for (char c : code.toCharArray()){
    //         switch (c) {
    //             case '0':

    //                 break;

    //             default:
    //                 break;
    //         }
    //     }
    // }

    /**
     * Returns the string representation of the Huffman tree root node.
     *
     * @return a formatted multi-line string describing the root node
     */
    @Override
    public String toString() {
        return root.toString();
    }

    /**
     * Returns the Huffman-encoded binary string of the original input text.
     *
     * @return the encoded bit-string
     */
    public String getEncodedText() {
        return encodedText;
    }

    /**
     * Returns the root node of the Huffman binary tree.
     *
     * @return the root {@link CharNode} of the tree
     */
    public CharNode getRoot() {
        return root;
    }
}
