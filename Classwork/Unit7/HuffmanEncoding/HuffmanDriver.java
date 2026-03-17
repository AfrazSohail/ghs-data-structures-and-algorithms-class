package Classwork.Unit7.HuffmanEncoding;

public class HuffmanDriver {

    public static void main(String args[]) {
        //COMPARE TO CAN NEVER SAY THAT TWO NODES ARE EQUAL!
        char[] chars = new HuffmanDriver().getCharArray();
        Huffman huffman = new Huffman(chars);
        System.out.println("Huffman Tree:\n" + huffman);

        JPanels.CreateTextPanel(new String(chars), huffman.getEncodedText());
        JPanels.CreateTreePanel(huffman.getRoot());
    }

    private char[] getCharArray() {
        String text = JPanels.openFile();
        return text.toCharArray();
    }
}
