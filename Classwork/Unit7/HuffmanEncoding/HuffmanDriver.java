package Classwork.Unit7.HuffmanEncoding;

import java.io.IOException;

/**
 * Entry point for the Huffman Encoding application.
 * <p>
 * Orchestrates the full encode/decode workflow:
 * <ol>
 *   <li>Prompts the user to open a plain-text file.</li>
 *   <li>Builds a {@link Huffman} instance to encode the file's contents.</li>
 *   <li>Displays the original and encoded text in a side-by-side GUI panel.</li>
 *   <li>Renders the Huffman binary tree in a separate scrollable panel.</li>
 *   <li>Saves the encoded bit-string to {@code encodedText.txt}.</li>
 *   <li>Prompts the user to open the saved encoded file and displays the
 *       decoded result alongside it.</li>
 * </ol>
 * </p>
 *
 * @author AfrazSohail
 * @apiNote Documentation generated with the assistance of GitHub Copilot (AI).
 */
public class HuffmanDriver {

    /**
     * Application entry point.
     * <p>
     * <strong>Note:</strong> The {@link Comparable} implementation in
     * {@link CharNode} must never consider two nodes equal; a tie-break on
     * character value is used to guarantee a consistent sort order.
     * </p>
     *
     * @param args command-line arguments (not used)
     * @throws IOException if a file cannot be read or written
     */
    public static void main(String args[]) throws IOException {
        //COMPARE TO CAN NEVER SAY THAT TWO NODES ARE EQUAL!
        String input = JPanels.openFile();
        String text = input;
        Huffman huffman = new Huffman(text);
        System.out.println("Huffman Tree:\n" + huffman);

        JPanels.CreateTextPanel(text, huffman.getEncodedText());
        JPanels.CreateTreePanel(huffman.getRoot());
        JPanels.saveFile("encodedText", huffman.getEncodedText());
        String encodedText = JPanels.openFile();
        JPanels.CreateTextPanel(encodedText, huffman.decodeText(encodedText));
    }
}
