package Classwork.Unit7.HuffmanEncoding;

import java.util.ArrayList;
import java.util.HashMap;

public class Huffman {

    // own freq, encodings, lists, etc.
    private final ArrayList<CharNode> charTree = new ArrayList<>();

    private final HashMap<String, Character> encodings = new HashMap<>();

    private String text;
    private String encodedText;
    private CharNode root;

    public Huffman(String text) {
        this.text = text;
        buildArray(text.trim());
        buildTree();
        scanTree(charTree.get(0), "");
        encodeText();
    }

    public Huffman(char[] chars) {
        this(new String(chars));
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
        root = charTree.get(0);
    }

    private void scanTree(CharNode node, String path) {
        if (node.character != '\uFFFF') {
            encodings.put(path, node.character);
        } else {
            scanTree(node.left, path + "0");
            scanTree(node.right, path + "1");
        }
    }

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

    @Override
    public String toString() {
        return root.toString();
    }

    public String getEncodedText() {
        return encodedText;
    }

    public CharNode getRoot() {
        return root;
    }
}
