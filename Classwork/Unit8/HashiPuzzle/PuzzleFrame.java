package HashiPuzzle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 * Top-level window that displays a Hashi puzzle. The grid is rendered by
 * {@link PuzzlePanel} inside a scroll pane, and a status bar at the bottom
 * reports whether the current state is a valid solution.
 */
public class PuzzleFrame extends JFrame {

    public PuzzleFrame(Map map, boolean isSolution) {
        super("Hashi Puzzle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(new JScrollPane(new PuzzlePanel(map)), BorderLayout.CENTER);
        add(buildStatusBar(isSolution), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JLabel buildStatusBar(boolean isSolution) {
        String text = isSolution ? "Valid solution!" : "Not a valid solution.";
        Color color = isSolution ? new Color(0, 140, 0) : new Color(190, 0, 0);

        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        label.setForeground(color);
        label.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        return label;
    }
}
