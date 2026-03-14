package Classwork.Unit7.HuffmanEncoding;

import java.util.ArrayList;

public class Huffman {

    // own freq, encodings, lists, etc.
    private final ArrayList<CharNode> charTree = new ArrayList<>();

    public Huffman(String text) {
        buildArray(text.trim());
    }

    public Huffman(char[] chars) {
        buildArray(new String(chars).trim());
        buildTree();
    }

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
    }

    @Override
    public String toString() {
        return charTree.get(0).toString();
    }
}
