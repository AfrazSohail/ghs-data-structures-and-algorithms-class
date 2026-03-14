package Classwork.Unit7.HuffmanEncoding;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.*;

public class JPanels {
    // static

    public static String openFile() {
        String currentFolder = java.nio.file.Paths.get("").toAbsolutePath().toString();

        JFileChooser chooser = new JFileChooser(currentFolder);
        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                String contents = Files.readString(file.toPath());
                return contents;
            } catch (IOException e) {
            }
        }

        return null;
    }

    public static void CreateTextPanel(String text, String encodedText) {
        JFrame frame = new JFrame("Text Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText("Original Text:\n" + text + "\n\nEncoded Text:\n" + encodedText);
        frame.add(new JScrollPane(textArea));
        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    public static void CreateTreePanel(CharNode root) {
        JFrame frame = new JFrame("Huffman Tree");
        int height = root.getHeight();
        int width = (int) Math.pow(2, height - 1);
        frame.setSize(width * 100, height * 100);
        frame.setVisible(true);

        frame.add(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawNode(g, root, getWidth() / 2, 50, 1);
            }
        });
    }

    private static void drawNode(Graphics g, CharNode node, int x, int y, int level) {
        if (node == null) {
            return;
        }
        g.drawString(node.character + ":" + node.frequency, x, y);
        drawNode(g, node.left, x - 50 / level, y + 50, level + 1);
        drawNode(g, node.right, x + 50 / level, y + 50, level + 1);
    }
}
