package Classwork.Unit7.HuffmanEncoding;

import java.io.IOException;

public class HuffmanDriver {

    public static void main(String args[]) throws IOException {
        //COMPARE TO CAN NEVER SAY THAT TWO NODES ARE EQUAL!
        String[] input = JPanels.openFile();
        String text = input[1];
        String fileName = input[0];
        Huffman huffman = new Huffman(text);
        System.out.println("Huffman Tree:\n" + huffman);

        JPanels.CreateTextPanel(text, huffman.getEncodedText());
        JPanels.CreateTreePanel(huffman.getRoot());
        JPanels.saveFile(fileName, huffman.getEncodedText());
    }
}
