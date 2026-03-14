package Classwork.Unit7.HuffmanEncoding;

public class HuffmanDriver {

    public static void main(String args[]) {
        char[] chars = new HuffmanDriver().getCharArray();
        Huffman huffman = new Huffman(chars);
        System.out.println("Huffman Tree:\n" + huffman);
    }

    private char[] getCharArray() {
        String text = JPanels.openFile();
        return text.toCharArray();
    }
}
