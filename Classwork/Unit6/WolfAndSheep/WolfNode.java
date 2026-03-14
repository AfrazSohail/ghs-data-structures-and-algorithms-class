import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a wolf on an 8x8 board and provides path and alignment utilities.
 * Documentation done by AI.
 *
 * @author AfrazSohail
 */
public class WolfNode {
    private final static char minCol = 'A';
    private final static char maxCol = 'H';
    private final static char minRow = '1';
    private final static char maxRow = '8';

    /** Current position in chess-style coordinates (e.g., A1). */
    public String pos;
    /** Path node one step to the southwest. */
    public WolfNode southWest;
    /** Path node one step to the southeast. */
    public WolfNode southEast;

    /**
     * Creates a wolf at the given board position.
     *
     * @param pos board position in the form Letter+Digit (A1 to H8)
     */
    public WolfNode(String pos) {
        if (pos.length() > 2)
            throw new IllegalArgumentException("Position must be 2 characters or less");
        if (!(Character.isLetter(pos.charAt(0)) && Character.isDigit(pos.charAt(1))))
            throw new IllegalArgumentException("Column (Letter) then Row (Integer");
        char col = pos.charAt(0);
        char row = pos.charAt(1);
        if (col > maxCol || col < minCol || row > maxRow || row < minRow)
            throw new IllegalArgumentException("Cannot go beyond A-H and 1-8");

        this.pos = pos;
        buildPath();
    }

    /** Builds the reachable path tree moving downward diagonally. */
    private void buildPath() {
        if (pos.charAt(1) < maxRow) {
            if (pos.charAt(0) > minCol)
                southWest = new WolfNode("" + (char) (pos.charAt(0) - 1) + (char) (pos.charAt(1) + 1));
            if (pos.charAt(0) < maxCol)
                southEast = new WolfNode("" + (char) (pos.charAt(0) + 1) + (char) (pos.charAt(1) + 1));
        }
    }

    /**
     * Returns all reachable positions from this wolf, including its current
     * position. The order is unspecified.
     *
     * @return set of reachable positions
     */
    public Set<String> getUnsortedPathSet() {
        Set<String> set = new HashSet<String>();
        set.add(pos);
        if (southWest != null)
            set.addAll(southWest.getUnsortedPathSet());
        if (southEast != null)
            set.addAll(southEast.getUnsortedPathSet());
        return set;
    }

    /**
     * Computes the intersection of reachable positions for two wolves.
     *
     * @param otherWolf the other wolf to compare against
     * @return sorted list of common reachable positions
     */
    public ArrayList<String> getIntersections(WolfNode otherWolf) {
        Set<String> thisPathSet = getUnsortedPathSet();
        Set<String> otherPathSet = otherWolf.getUnsortedPathSet();

        Set<String> intersectionSet = getUnsortedIntersectionSet(thisPathSet, otherPathSet);

        ArrayList<String> intersectionList = new ArrayList<String>(intersectionSet);
        MergeSort.sort(intersectionList);
        return intersectionList;
    }

    /**
     * Returns the intersection of two position sets without sorting.
     *
     * @param set1 first set
     * @param set2 second set
     * @return set of common positions
     */
    private Set<String> getUnsortedIntersectionSet(Set<String> set1, Set<String> set2) {
        int size1 = set1.size(), size2 = set2.size();
        Set<String> intersectionSet = new HashSet<String>();
        if (size1 < size2) {
            for (String str : set1)
                if (set2.contains(str))
                    intersectionSet.add(str);
        } else
            for (String str : set2)
                if (set1.contains(str))
                    intersectionSet.add(str);
        return intersectionSet;
    }

    /**
     * Finds the earliest row (moving downward) where all wolves can align on the
     * same row and color.
     *
     * @param w1 first wolf
     * @param w2 second wolf
     * @param w3 third wolf
     * @param w4 fourth wolf
     * @return concatenated positions (e.g., A3C3E3G3) or null if impossible
     */
    public static String canBlock(WolfNode w1, WolfNode w2, WolfNode w3, WolfNode w4) {
        List<WolfNode> wolfList = new ArrayList<>();
        wolfList.add(w1);
        wolfList.add(w2);
        wolfList.add(w3);
        wolfList.add(w4);

        if (!sameColour(wolfList))
            return null;

        sortWolfListByColThenRow(wolfList);
        char curRow = getBotMostRow(wolfList);

        for (; curRow <= maxRow; curRow++) {
            String str = "";
            for (int i = 0; i < wolfList.size(); i++) {
                char curChar = (char) ('A' + i * 2);
                WolfNode wolf = wolfList.get(i);
                if (wolf.hasPath("" + curChar + curRow))
                    str += "" + curChar + curRow;
                else if (wolf.hasPath("" + (char) (curChar + 1) + curRow))
                    str += "" + (char) (curChar + 1) + curRow;
            }
            if (str.length() == 8)
                return str;
        }
        return null;
    }

    /**
     * Checks whether the given position is reachable by this wolf.
     *
     * @param pos board position to check
     * @return true if reachable, false otherwise
     */
    public boolean hasPath(String pos) {
        Set<String> pathSet = getUnsortedPathSet();
        return pathSet.contains(pos);
    }

    /**
     * Returns true if all wolves are on the same color squares.
     *
     * @param wolfList list of wolves
     * @return true if same color or list is null
     */
    private static boolean sameColour(List<WolfNode> wolfList) {
        if (wolfList == null)
            return true;

        WolfNode firstWolf = wolfList.get(0);
        int colourCheck = (firstWolf.pos.charAt(0) + firstWolf.pos.charAt(1)) % 2;

        for (int i = 1; i < wolfList.size(); i++) {
            WolfNode wolf = wolfList.get(i);
            int colour = (wolf.pos.charAt(0) + wolf.pos.charAt(1)) % 2;
            if (colour != colourCheck)
                return false;
        }
        return true;
    }

    /**
     * Returns the bottom-most (largest) row among the wolves.
     *
     * @param wolfList list of wolves
     * @return bottom-most row character, or '0' if list is null/empty
     */
    private static char getBotMostRow(List<WolfNode> wolfList) {
        if (wolfList == null || wolfList.isEmpty())
            return '0';

        char botMostRow = wolfList.get(0).pos.charAt(1);
        for (int i = 1; i < wolfList.size(); i++) {
            char row = wolfList.get(i).pos.charAt(1);
            if (row > botMostRow)
                botMostRow = row;
        }
        return botMostRow;
    }

    /**
     * Sorts wolves by column then row using a simple bubble sort.
     *
     * @param wolfList list to sort in-place
     */
    private static void sortWolfListByColThenRow(List<WolfNode> wolfList) {
        for (int i = 0; i < wolfList.size(); i++) {
            for (int j = 0; j < wolfList.size() - i - 1; j++) {
                WolfNode wolf1 = wolfList.get(j);
                WolfNode wolf2 = wolfList.get(j + 1);

                int val1 = wolf1.pos.charAt(0) * 10 + wolf1.pos.charAt(1);
                int val2 = wolf2.pos.charAt(0) * 10 + wolf2.pos.charAt(1);

                if (val1 > val2)
                    swapWolf(j, j + 1, wolfList);
            }
        }
    }

    /**
     * Swaps two wolves in the list.
     *
     * @param i        first index
     * @param j        second index
     * @param wolfList list to mutate
     */
    private static void swapWolf(int i, int j, List<WolfNode> wolfList) {
        WolfNode temp = wolfList.get(i);
        wolfList.set(i, wolfList.get(j));
        wolfList.set(j, temp);
    }
}
