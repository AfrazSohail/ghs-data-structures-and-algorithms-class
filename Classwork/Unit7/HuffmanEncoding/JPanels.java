package Classwork.Unit7.HuffmanEncoding;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class JPanels {

    private static final int NODE_RADIUS = 28;
    private static final int LEVEL_GAP = 90;
    private static final int HORIZONTAL_PAD = 40;
    private static final int TOP_PAD = 50;

    // ─── File chooser ───────────────────────────────────────────────

    public static String[] openFile() {
        String currentFolder = java.nio.file.Paths.get("").toAbsolutePath().toString();
        JFileChooser chooser = new JFileChooser(currentFolder);
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                return new String[] {file.getName(),Files.readString(file.toPath())};
            } catch (IOException ignored) {
            }
        }
        return null;
    }

    // ─── Text panel (original + encoded) ────────────────────────────

    public static void CreateTextPanel(String text, String encodedText) {
        JFrame frame = new JFrame("Huffman – Text");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel content = new JPanel(new GridLayout(1, 2, 10, 0));
        content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextArea origArea = buildTextArea("Original Text", text);
        JTextArea encArea = buildTextArea("Encoded Text", encodedText);

        content.add(wrapInTitledScroll("Original", origArea));
        content.add(wrapInTitledScroll("Encoded", encArea));

        frame.setContentPane(content);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JTextArea buildTextArea(String header, String body) {
        JTextArea ta = new JTextArea(header + "\n\n" + body);
        ta.setEditable(false);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        ta.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        return ta;
    }

    private static JScrollPane wrapInTitledScroll(String title, JComponent comp) {
        JScrollPane sp = new JScrollPane(comp);
        sp.setBorder(BorderFactory.createTitledBorder(title));
        return sp;
    }

    // ─── Tree panel ─────────────────────────────────────────────────

    public static void CreateTreePanel(CharNode root) {
        // Pre-calculate every node's (x, y) with an in-order walk
        // so that no two nodes overlap horizontally.
        Map<CharNode, int[]> positions = new HashMap<>();
        int[] xCounter = {0};                       // mutable counter
        int treeHeight = root.getHeight();
        assignPositions(root, positions, xCounter, 0);

        int totalLeaves = xCounter[0];
        int panelW = totalLeaves * (NODE_RADIUS * 2 + HORIZONTAL_PAD) + HORIZONTAL_PAD;
        int panelH = treeHeight * LEVEL_GAP + TOP_PAD * 2;

        JPanel treePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
                drawTree(g2, root, positions);
            }
        };
        treePanel.setBackground(Color.WHITE);
        treePanel.setPreferredSize(new Dimension(panelW, panelH));

        JScrollPane scroll = new JScrollPane(treePanel);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.getHorizontalScrollBar().setUnitIncrement(16);

        JFrame frame = new JFrame("Huffman – Tree");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(scroll);
        frame.setSize(Math.min(panelW + 40, 1200), Math.min(panelH + 60, 750));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * In-order traversal assigns an increasing x-index to each node
     * and stores [xIndex, depth] in the map.
     */
    private static void assignPositions(CharNode node,
            Map<CharNode, int[]> positions, int[] xCounter, int depth) {
        if (node == null) return;
        assignPositions(node.left, positions, xCounter, depth + 1);
        positions.put(node, new int[]{xCounter[0]++, depth});
        assignPositions(node.right, positions, xCounter, depth + 1);
    }

    /** Convert stored index/depth to pixel coordinates. */
    private static int pixelX(int[] pos) {
        return HORIZONTAL_PAD + pos[0] * (NODE_RADIUS * 2 + HORIZONTAL_PAD) + NODE_RADIUS;
    }

    private static int pixelY(int[] pos) {
        return TOP_PAD + pos[1] * LEVEL_GAP;
    }

    // ─── Drawing helpers ────────────────────────────────────────────

    private static void drawTree(Graphics2D g2, CharNode node,
            Map<CharNode, int[]> positions) {
        if (node == null) return;

        int[] pos = positions.get(node);
        int cx = pixelX(pos);
        int cy = pixelY(pos);

        // Draw edges first (so circles paint over the line ends)
        if (node.left != null) drawEdge(g2, cx, cy, positions.get(node.left), "0");
        if (node.right != null) drawEdge(g2, cx, cy, positions.get(node.right), "1");

        // Circle
        boolean isLeaf = (node.character != '\uFFFF');
        g2.setColor(isLeaf ? new Color(0xD0E8FF) : new Color(0xF0F0F0));
        g2.fillOval(cx - NODE_RADIUS, cy - NODE_RADIUS,
                NODE_RADIUS * 2, NODE_RADIUS * 2);
        g2.setColor(isLeaf ? new Color(0x3070B0) : Color.DARK_GRAY);
        g2.setStroke(new BasicStroke(1.8f));
        g2.drawOval(cx - NODE_RADIUS, cy - NODE_RADIUS,
                NODE_RADIUS * 2, NODE_RADIUS * 2);

        // Text inside the circle — centred
        g2.setColor(Color.BLACK);
        if (isLeaf) {
            // Two lines: character on top, frequency below
            String charLabel = displayChar(node.character);
            String freqLabel = String.valueOf(node.frequency);

            Font charFont = new Font(Font.SANS_SERIF, Font.BOLD, 14);
            Font freqFont = new Font(Font.SANS_SERIF, Font.PLAIN, 11);

            drawCentred(g2, charLabel, cx, cy - 6, charFont);
            drawCentred(g2, freqLabel, cx, cy + 10, freqFont);
        } else {
            // Internal node (\uFFFF) — only frequency
            String freqLabel = String.valueOf(node.frequency);
            Font freqFont = new Font(Font.SANS_SERIF, Font.BOLD, 13);
            drawCentred(g2, freqLabel, cx, cy + 4, freqFont);
        }

        // Recurse
        drawTree(g2, node.left, positions);
        drawTree(g2, node.right, positions);
    }

    /** Draw edge from parent centre to child circle, with a 0/1 label. */
    private static void drawEdge(Graphics2D g2, int px, int py,
            int[] childPos, String bit) {
        int cx = pixelX(childPos);
        int cy = pixelY(childPos);

        // Shorten line so it starts/ends at circle perimeter
        double angle = Math.atan2(cy - py, cx - px);
        int x1 = px + (int) (NODE_RADIUS * Math.cos(angle));
        int y1 = py + (int) (NODE_RADIUS * Math.sin(angle));
        int x2 = cx - (int) (NODE_RADIUS * Math.cos(angle));
        int y2 = cy - (int) (NODE_RADIUS * Math.sin(angle));

        g2.setColor(Color.DARK_GRAY);
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawLine(x1, y1, x2, y2);

        // Bit label near the midpoint, slightly offset
        int mx = (x1 + x2) / 2 + (bit.equals("0") ? -10 : 8);
        int my = (y1 + y2) / 2;
        g2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        g2.setColor(new Color(0xCC3333));
        g2.drawString(bit, mx, my);
    }

    /** Draw a string centred at (cx, cy) with the given font. */
    private static void drawCentred(Graphics2D g2, String text,
            int cx, int cy, Font font) {
        g2.setFont(font);
        FontMetrics fm = g2.getFontMetrics();
        int tw = fm.stringWidth(text);
        int th = fm.getAscent();
        g2.drawString(text, cx - tw / 2, cy + th / 2 - 1);
    }

    /** Readable display for a character (handles space, newline, etc.) */
    private static String displayChar(char c) {
        return switch (c) {
            case ' ' -> "SP";
            case '\n' -> "LF";
            case '\r' -> "CR";
            case '\t' -> "TAB";
            default -> String.valueOf(c);
        };
    }

    // MY OWN METHODS
    public static void saveFile(String fileName, String encodedText) throws IOException {
        FileWriter writer = new FileWriter(fileName + "Output.txt");
        writer.write(encodedText);
        writer.close();
    }
}
