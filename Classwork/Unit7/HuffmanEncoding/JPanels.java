package Classwork.Unit7.HuffmanEncoding;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

/**
 * Utility class that provides all GUI panels and file I/O operations for the
 * Huffman Encoding application.
 * <p>
 * All methods are static; this class is not intended to be instantiated.
 * It exposes three kinds of functionality:
 * <ul>
 *   <li><strong>File I/O</strong> – {@link #openFile()} and
 *       {@link #saveFile(String, String)}</li>
 *   <li><strong>Text panel</strong> – {@link #CreateTextPanel(String, String)}
 *       shows the original and encoded text side by side.</li>
 *   <li><strong>Tree panel</strong> – {@link #CreateTreePanel(CharNode)}
 *       renders the Huffman binary tree using Java2D.</li>
 * </ul>
 * </p>
 *
 * @author AfrazSohail
 * @apiNote Documentation generated with the assistance of GitHub Copilot (AI).
 */
public class JPanels {

    /** Radius (in pixels) of each node circle drawn on the tree panel. */
    private static final int NODE_RADIUS = 28;

    /** Vertical distance (in pixels) between successive tree levels. */
    private static final int LEVEL_GAP = 40;

    /** Horizontal padding (in pixels) between adjacent node circles. */
    private static final int HORIZONTAL_PAD = 40;

    /** Top padding (in pixels) before the first tree level. */
    private static final int TOP_PAD = 50;

    // ─── File chooser ───────────────────────────────────────────────

    /**
     * Opens a {@link JFileChooser} dialog rooted at the current working
     * directory and returns the full text content of the chosen file.
     *
     * @return the file contents as a {@code String}, or {@code null} if the
     *         user cancelled or an I/O error occurred
     */
    public static String openFile() {
        String currentFolder = java.nio.file.Paths.get("").toAbsolutePath().toString();
        JFileChooser chooser = new JFileChooser(currentFolder);
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                return Files.readString(file.toPath());
            } catch (IOException ignored) {
            }
        }
        return null;
    }

    // ─── Text panel (original + encoded) ────────────────────────────

    /**
     * Creates and displays a {@link JFrame} containing two side-by-side
     * scrollable text areas: one showing the original plain text and one
     * showing the Huffman-encoded (or decoded) text.
     *
     * @param text        the left-pane text (original or encoded input)
     * @param encodedText the right-pane text (encoded or decoded result)
     */
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

    /**
     * Builds a non-editable, word-wrapping {@link JTextArea} prefixed with a
     * header line.
     *
     * @param header a label shown at the top of the text area
     * @param body   the main content to display
     * @return the configured {@code JTextArea}
     */
    private static JTextArea buildTextArea(String header, String body) {
        JTextArea ta = new JTextArea(header + "\n\n" + body);
        ta.setEditable(false);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        ta.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        return ta;
    }

    /**
     * Wraps a component in a {@link JScrollPane} decorated with a titled border.
     *
     * @param title the border title
     * @param comp  the component to wrap
     * @return the scroll pane with the titled border applied
     */
    private static JScrollPane wrapInTitledScroll(String title, JComponent comp) {
        JScrollPane sp = new JScrollPane(comp);
        sp.setBorder(BorderFactory.createTitledBorder(title));
        return sp;
    }

    // ─── Tree panel ─────────────────────────────────────────────────

    /**
     * Creates and displays a scrollable {@link JFrame} that renders the
     * Huffman binary tree rooted at {@code root} using Java2D.
     * <p>
     * Node positions are assigned via an in-order traversal so that no two
     * nodes overlap horizontally. Leaf nodes are drawn with a blue tint;
     * internal nodes are drawn in light grey. Edge labels ({@code 0}/{@code 1})
     * indicate the bit added when traversing that branch.
     * </p>
     *
     * @param root the root {@link CharNode} of the Huffman tree to visualise
     */
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
     * and stores {@code [xIndex, depth]} in the map.
     *
     * @param node      the current node being visited (may be {@code null})
     * @param positions map from node to its {@code [xIndex, depth]} array
     * @param xCounter  single-element array used as a mutable x-index counter
     * @param depth     the current depth in the tree (root = 0)
     */
    private static void assignPositions(CharNode node,
            Map<CharNode, int[]> positions, int[] xCounter, int depth) {
        if (node == null) return;
        assignPositions(node.left, positions, xCounter, depth + 1);
        positions.put(node, new int[]{xCounter[0]++, depth});
        assignPositions(node.right, positions, xCounter, depth + 1);
    }

    /**
     * Converts a node's stored {@code [xIndex, depth]} array to a pixel
     * x-coordinate on the panel.
     *
     * @param pos the {@code [xIndex, depth]} array from the position map
     * @return the pixel x-coordinate of the node's centre
     */
    private static int pixelX(int[] pos) {
        return HORIZONTAL_PAD + pos[0] * (NODE_RADIUS * 2 + HORIZONTAL_PAD) + NODE_RADIUS;
    }

    /**
     * Converts a node's stored {@code [xIndex, depth]} array to a pixel
     * y-coordinate on the panel.
     *
     * @param pos the {@code [xIndex, depth]} array from the position map
     * @return the pixel y-coordinate of the node's centre
     */
    private static int pixelY(int[] pos) {
        return TOP_PAD + pos[1] * LEVEL_GAP;
    }

    // ─── Drawing helpers ────────────────────────────────────────────

    /**
     * Recursively draws the subtree rooted at {@code node} onto {@code g2}.
     * Edges are drawn before circles so that circle borders paint over line ends.
     *
     * @param g2        the {@link Graphics2D} context to draw on
     * @param node      the current node to draw (may be {@code null})
     * @param positions pre-computed {@code [xIndex, depth]} positions for every node
     */
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

    /**
     * Draws a labelled edge from a parent node centre to a child node circle.
     * The line is shortened at both ends so it does not overlap the node circles.
     *
     * @param g2       the {@link Graphics2D} context
     * @param px       pixel x-coordinate of the parent node centre
     * @param py       pixel y-coordinate of the parent node centre
     * @param childPos the {@code [xIndex, depth]} array of the child node
     * @param bit      the edge label to display ({@code "0"} for left,
     *                 {@code "1"} for right)
     */
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

    /**
     * Draws a string horizontally centred at the given pixel coordinate.
     *
     * @param g2   the {@link Graphics2D} context
     * @param text the string to draw
     * @param cx   the desired centre x-coordinate
     * @param cy   the desired centre y-coordinate
     * @param font the font to use for rendering
     */
    private static void drawCentred(Graphics2D g2, String text,
            int cx, int cy, Font font) {
        g2.setFont(font);
        FontMetrics fm = g2.getFontMetrics();
        int tw = fm.stringWidth(text);
        int th = fm.getAscent();
        g2.drawString(text, cx - tw / 2, cy + th / 2 - 1);
    }

    /**
     * Returns a human-readable display label for a character, replacing
     * whitespace control characters with descriptive abbreviations.
     *
     * @param c the character to convert
     * @return {@code "SP"} for space, {@code "LF"} for newline,
     *         {@code "CR"} for carriage-return, {@code "TAB"} for tab,
     *         or the character itself as a one-character string
     */
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
    /**
     * Saves {@code encodedText} to a file named {@code <fileName>.txt} in the
     * current working directory, overwriting any existing file with that name.
     *
     * @param fileName    the base file name (without extension) to write to
     * @param encodedText the content to write
     * @throws IOException if the file cannot be created or written
     */
    public static void saveFile(String fileName, String encodedText) throws IOException {
        FileWriter writer = new FileWriter(fileName + ".txt");
        writer.write(encodedText);
        writer.close();
    }
}
