import java.util.ArrayList;
import java.util.List;

/**
 * Represents a sheep on an 8x8 board and finds escape paths.
 * Documentation done by AI.
 *
 * @author AfrazSohail
 */
public class SheepNode {
    private final static char minCol = 'A';
    private final static char maxCol = 'H';
    private final static char minRow = '1';
    private final static char maxRow = '8';

    /** Current position in chess-style coordinates (e.g., D8). */
    public String pos;

    /**
     * Creates a sheep at the given board position.
     *
     * @param pos board position in the form Letter+Digit (A1 to H8)
     */
    public SheepNode(String pos) {
        if (pos.length() > 2)
            throw new IllegalArgumentException("Position must be 2 characters or less");
        if (!(Character.isLetter(pos.charAt(0)) && Character.isDigit(pos.charAt(1))))
            throw new IllegalArgumentException("Column (Letter) then Row (Integer");
        char col = pos.charAt(0);
        char row = pos.charAt(1);
        if (col > maxCol || col < minCol || row > maxRow || row < minRow)
            throw new IllegalArgumentException("Cannot go beyond A-H and 1-8");

        this.pos = pos;
    }

    /**
     * Attempts to find a winning path to row 1 while avoiding the given wolves.
     *
     * @param w1 first wolf
     * @param w2 second wolf
     * @param w3 third wolf
     * @param w4 fourth wolf
     * @return string representation of the path, or null/empty if blocked
     */
    public String winPath(WolfNode w1, WolfNode w2, WolfNode w3, WolfNode w4) {
        List<String> avoidPos = new ArrayList<>();
        avoidPos.add(w1.pos);
        avoidPos.add(w2.pos);
        avoidPos.add(w3.pos);
        avoidPos.add(w4.pos);

        if (avoidPos.contains(pos))
            return null;

        List<String> path = new ArrayList<>();
        path.add(pos);
        List<String> str = winPath(avoidPos, path);
        return (str != null) ? str.toString() : "";
    }

    /**
     * Recursive backtracking search for a path to row 1.
     *
     * @param avoidPos positions occupied by wolves or blocked squares
     * @param path     current path from the start position
     * @return list of positions for a winning path, or null if none
     */
    private List<String> winPath(List<String> avoidPos, List<String> path) {
        if (path == null || path.isEmpty())
            return null;
        String curPos = path.get(path.size() - 1);
        String newPos = "";

        newPos = getNewPos(curPos, "NE");
        if (newPos != null && !avoidPos.contains(newPos) && !path.contains(newPos)) {
            path.add(newPos);
            if (newPos.charAt(1) == '1')
                return path;
            else
                return winPath(avoidPos, path);
        }
        newPos = getNewPos(curPos, "NW");
        if (newPos != null && !avoidPos.contains(newPos) && !path.contains(newPos)) {
            path.add(newPos);
            if (newPos.charAt(1) == '1')
                return path;
            else
                return winPath(avoidPos, path);
        }
        newPos = getNewPos(curPos, "SE");
        if (newPos != null && !avoidPos.contains(newPos) && !path.contains(newPos)) {
            path.add(newPos);
            if (newPos.charAt(1) == '1')
                return path;
            else
                return winPath(avoidPos, path);
        }
        newPos = getNewPos(curPos, "SW");
        if (newPos != null && !avoidPos.contains(newPos) && !path.contains(newPos)) {
            path.add(newPos);
            if (newPos.charAt(1) == '1')
                return path;
            else
                return winPath(avoidPos, path);
        }
        String last = path.get(path.size() - 1);
        path.remove(last);
        avoidPos.add(curPos);
        return winPath(avoidPos, path);
    }

    /**
     * Computes the new position after moving one step in the given direction.
     *
     * @param pos current position
     * @param dir direction: NE, NW, SE, or SW
     * @return new position or null if the move is off the board
     */
    private String getNewPos(String pos, String dir) {
        switch (dir) {
            case "NE":
                if (pos.charAt(0) >= maxCol || pos.charAt(1) <= minRow)
                    return null;
                return "" + (char) (pos.charAt(0) + 1) + (char) (pos.charAt(1) - 1);
            case "NW":
                if (pos.charAt(0) <= minCol || pos.charAt(1) <= minRow)
                    return null;
                return "" + (char) (pos.charAt(0) - 1) + (char) (pos.charAt(1) - 1);
            case "SE":
                if (pos.charAt(0) >= maxCol || pos.charAt(1) >= maxRow)
                    return null;
                return "" + (char) (pos.charAt(0) + 1) + (char) (pos.charAt(1) + 1);
            case "SW":
                if (pos.charAt(0) <= minCol || pos.charAt(1) >= maxRow)
                    return null;
                return "" + (char) (pos.charAt(0) - 1) + (char) (pos.charAt(1) + 1);
            default:
                return null;
        }
    }
}
