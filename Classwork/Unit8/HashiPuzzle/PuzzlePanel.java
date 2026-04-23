package HashiPuzzle;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Renders a Hashi puzzle grid using pixel-art tile images. Each cell is drawn
 * at TILE_SIZE x TILE_SIZE pixels using nearest-neighbour scaling to preserve
 * the crisp pixel-art style.
 *
 * <p>
 * Special island states:
 * <ul>
 * <li><b>Over-bridged</b> — connected bridge weight exceeds the island's value
 * → {@code wrong.png}.</li>
 * <li><b>Disconnected groups</b> — when islands form more than one connected
 * component, each group cycles through {@code captured_1.png} …
 * {@code captured_4.png}. Wrong takes priority over captured.</li>
 * </ul>
 */
public class PuzzlePanel extends JPanel {

    private static final int TILE_SIZE = 64;
    private static final int CAPTURED_COUNT = 4;

    private static final String FALLBACK_IMAGE_DIR
            = "Classwork/Unit8/HashiPuzzle/images/";

    private final Map map;

    // Cached, pre-scaled tile images
    private BufferedImage waterImg;
    private BufferedImage wrongImg;
    private BufferedImage[] islandImgs;   // indices 0-8
    private BufferedImage[] capturedImgs; // indices 0-3  (captured_1 … captured_4)
    private BufferedImage singleHImg;
    private BufferedImage doubleHImg;
    private BufferedImage singleVImg;
    private BufferedImage doubleVImg;

    // Per-island state computed once at construction
    private final Set<Island> overBridgedIslands;
    private final java.util.Map<Island, Integer> islandComponentId; // null = single component

    public PuzzlePanel(Map map) {
        this.map = map;
        loadImages();
        overBridgedIslands = computeOverBridged();
        islandComponentId = computeComponents();
        Navigable[][] grid = map.getMap();
        int rows = grid.length;
        int cols = rows > 0 ? grid[0].length : 0;
        setPreferredSize(new Dimension(cols * TILE_SIZE, rows * TILE_SIZE));
    }

    // -------------------------------------------------------------------------
    // Image loading
    // -------------------------------------------------------------------------
    private void loadImages() {
        waterImg = loadScaled("water.png");
        wrongImg = loadScaled("wrong.png");
        islandImgs = new BufferedImage[9];
        for (int i = 0; i <= 8; i++) {
            islandImgs[i] = loadScaled("island_" + i + ".png");
        }
        capturedImgs = new BufferedImage[CAPTURED_COUNT];
        for (int i = 0; i < CAPTURED_COUNT; i++) {
            capturedImgs[i] = loadScaled("captured_" + (i + 1) + ".png");
        }
        singleHImg = loadScaled("single_h.png");
        doubleHImg = loadScaled("double_h.png");
        singleVImg = loadScaled("single_v.png");
        doubleVImg = loadScaled("double_v.png");
    }

    private BufferedImage loadScaled(String filename) {
        BufferedImage src = readImage(filename);
        if (src == null) {
            return new BufferedImage(TILE_SIZE, TILE_SIZE, BufferedImage.TYPE_INT_ARGB);
        }
        return scaleNearestNeighbour(src, TILE_SIZE, TILE_SIZE);
    }

    private BufferedImage readImage(String filename) {
        URL url = getClass().getResource("images/" + filename);
        if (url != null) {
            try {
                return ImageIO.read(url);
            } catch (IOException ignored) {
            }
        }
        File file = new File(FALLBACK_IMAGE_DIR + filename);
        if (file.exists()) {
            try {
                return ImageIO.read(file);
            } catch (IOException ignored) {
            }
        }
        return null;
    }

    private static BufferedImage scaleNearestNeighbour(
            BufferedImage src, int targetW, int targetH) {
        BufferedImage result
                = new BufferedImage(targetW, targetH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = result.createGraphics();
        g2.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g2.drawImage(src, 0, 0, targetW, targetH, null);
        g2.dispose();
        return result;
    }

    // -------------------------------------------------------------------------
    // Island state analysis
    // -------------------------------------------------------------------------
    /**
     * Returns the total weight of bridges directly adjacent to {@code island}.
     * Only the single cell immediately beside the island in each direction is
     * examined; that cell must be a {@link Bridge} aligned with that axis.
     */
    private int bridgeWeightOf(Island island) {
        Navigable[][] grid = map.getMap();
        int x = island.x();
        int y = island.y();
        int total = 0;

        // {dx, dy, expected bridge direction char}
        int[][] directions = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        char[] bridgeDirs = {'|', '-', '|', '-'};

        for (int i = 0; i < directions.length; i++) {
            int nx = x + directions[i][0];
            int ny = y + directions[i][1];
            if (ny < 0 || ny >= grid.length || nx < 0 || nx >= grid[ny].length) {
                continue;
            }
            Navigable nav = grid[ny][nx];
            if (nav instanceof Bridge) {
                Bridge bridge = (Bridge) nav;
                if (bridge.getDir() == bridgeDirs[i]) {
                    total += bridge.getWeight();
                }
            }
        }
        return total;
    }

    /**
     * Builds the set of islands whose connected bridge weight exceeds their
     * value.
     */
    private Set<Island> computeOverBridged() {
        Set<Island> result = new HashSet<>();
        for (Island island : map.islandSet()) {
            int capacity = island.getChar() - '0';
            if (bridgeWeightOf(island) > capacity) {
                result.add(island);
            }
        }
        return result;
    }

    /**
     * Returns a map from each island to a 0-based component index, or
     * {@code null} if all islands belong to a single connected component
     * (meaning no captured images are needed).
     *
     * <p>
     * Two islands are considered connected when a straight run of
     * {@link Bridge} cells joins them along the same row or column.
     */
    private java.util.Map<Island, Integer> computeComponents() {
        Set<Island> allIslands = map.islandSet();
        if (allIslands.isEmpty()) {
            return null;
        }

        java.util.Map<Island, Integer> componentMap = new HashMap<>();
        int nextId = 0;

        for (Island start : allIslands) {
            if (componentMap.containsKey(start)) {
                continue;
            }
            int id = nextId++;
            Queue<Island> queue = new ArrayDeque<>();
            queue.add(start);
            componentMap.put(start, id);
            while (!queue.isEmpty()) {
                Island current = queue.poll();
                for (Island neighbor : bridgeNeighborsOf(current)) {
                    if (!componentMap.containsKey(neighbor)) {
                        componentMap.put(neighbor, id);
                        queue.add(neighbor);
                    }
                }
            }
        }

        return nextId > 1 ? componentMap : null;
    }

    /**
     * Returns all islands reachable from {@code island} via a single straight
     * bridge run (one step in each of the four cardinal directions until
     * hitting an island or a non-bridge cell).
     */
    private List<Island> bridgeNeighborsOf(Island island) {
        List<Island> neighbors = new ArrayList<>();
        Navigable[][] grid = map.getMap();
        int startX = island.x();
        int startY = island.y();

        int[][] directions = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        char[] bridgeDirs = {'|', '-', '|', '-'};

        for (int d = 0; d < directions.length; d++) {
            int dx = directions[d][0];
            int dy = directions[d][1];
            char expectedDir = bridgeDirs[d];
            int x = startX + dx;
            int y = startY + dy;

            while (y >= 0 && y < grid.length && x >= 0 && x < grid[y].length) {
                Navigable nav = grid[y][x];
                if (nav instanceof Bridge && ((Bridge) nav).getDir() == expectedDir) {
                    // continue walking along this bridge run
                } else if (nav instanceof Island) {
                    neighbors.add((Island) nav);
                    break;
                } else {
                    break; // water or null — no island on this ray
                }
                x += dx;
                y += dy;
            }
        }
        return neighbors;
    }

    // -------------------------------------------------------------------------
    // Painting
    // -------------------------------------------------------------------------
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Navigable[][] grid = map.getMap();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                int px = col * TILE_SIZE;
                int py = row * TILE_SIZE;
                g.drawImage(tileFor(grid[row][col]), px, py, null);
            }
        }
    }

    private BufferedImage tileFor(Navigable nav) {
        if (nav == null) {
            return waterImg;
        }

        if (nav instanceof Island) {
            Island island = (Island) nav;

            // Priority 1: over-bridged → wrong image
            if (overBridgedIslands.contains(island)) {
                return wrongImg;
            }

            // Priority 2: disconnected group → captured image (cycle 1-4)
            if (islandComponentId != null) {
                int id = islandComponentId.getOrDefault(island, 0);
                return capturedImgs[id % CAPTURED_COUNT];
            }

            // Default: normal island number image
            int value = island.getChar() - '0';
            if (value >= 0 && value <= 8) {
                return islandImgs[value];
            }
            return waterImg;
        }

        Bridge bridge = (Bridge) nav;
        boolean vertical = bridge.getDir() == '|';
        boolean doubled = bridge.getWeight() == 2;
        if (vertical) {
            return doubled ? doubleVImg : singleVImg;
        } else {
            return doubled ? doubleHImg : singleHImg;
        }
    }
}
