package HashiPuzzle;

import java.util.HashSet;
import java.util.Stack;

/**
 * Validates whether a Hashi puzzle is correctly solved.
 *
 * <p>
 * This class uses depth-first search with a stack of {@link IslandValidation}
 * objects to verify that:
 * <ul>
 * <li>Every island has exactly the correct number of connecting bridges
 * <li>All islands form a single connected component (reachable from one another
 * via bridges)
 * </ul>
 *
 * @author AfrazSohail
 * @see Map
 * @see Island
 * @see IslandValidation
 */
public class Hashier {

    /**
     * Validates whether the given puzzle map is a correct solution.
     *
     * <p>
     * The algorithm uses depth-first search to traverse the bridge network,
     * checking that:
     * <ol>
     * <li>Each island's total connected bridge weight matches its value
     * <li>All islands are reachable from each other (single connected
     * component)
     * </ol>
     *
     * @param map the Hashi puzzle map to validate
     * @return {@code true} if the puzzle is correctly solved, {@code false}
     * otherwise
     */
    public static boolean isSolution(Map map) {
        Stack<IslandValidation> islandStack = new Stack<IslandValidation>();
        HashSet<Island> islandSet = new HashSet<Island>();
        islandStack.push(new IslandValidation(getFirstIsland(map)));
        int i = 0;

        while (!islandStack.isEmpty()) {
            i++;
            // if (islandSet.size() > 10 || i > 15)
            //     System.exit(0);
            IslandValidation island = islandStack.peek();
            if (island.isSolved()) {
                islandSet.add(islandStack.pop().getIsland());
            } else {
                IslandValidation newIsland = march(island, map);
                if (newIsland == null) {
                    return false;
                }
                if (islandSet.contains(newIsland.getIsland()) || islandStack.contains(newIsland)) {
                    continue;
                }
                islandStack.add(newIsland);
            }
            // System.out.println("Stack: " + islandStack.toString());
            // System.out.println("Set: " + islandSet.toString());
        }
        // System.out.println("Islands: " + islandSet.toString()+ "\nMap Islands: " + map.islandSet().toString());
        return islandSet.equals(map.islandSet());
    }

    /**
     * Finds the first island in the grid (scanning left-to-right,
     * top-to-bottom).
     *
     * @param map the puzzle map
     * @return the first {@link Island}, or {@code null} if none exists
     */
    private static Island getFirstIsland(Map map) {
        for (Navigable[] row : map.getMap()) {
            for (Navigable navigable : row) {
                if (navigable != null && navigable.isIsland()) {
                    return (Island) navigable;
                }
            }
        }
        return null;
    }

    /**
     * Recursively marches from one island to an adjacent island via its current
     * direction, returning the destination island if found.
     *
     * <p>
     * If a bridge exists in the current direction, follows it to the end and
     * returns the connected island (after recording the bridge weight). If no
     * bridge exists, recursively tries the next direction. Returns {@code null}
     * if no valid path is found.
     *
     * @param island the source island validation state
     * @param map the puzzle map
     * @return an {@link IslandValidation} for the destination island, or
     * {@code null} if no valid path exists
     */
    private static IslandValidation march(IslandValidation island, Map map) {
        char dir = island.nextDir();
        if (dir == ' ') {
            return null;
        }
        int weight = -1;
        if (isBridge(island, map)) {
            Navigable navigable = map.getMap()[island.getIsland().y][island.getIsland().x];
            while (true) {
                int x = navigable.x;
                int y = navigable.y;

                switch (dir) {
                    case 'N':
                        y--;
                        break;
                    case 'E':
                        x++;
                        break;
                    case 'S':
                        y++;
                        break;
                    case 'W':
                        x--;
                        break;
                    default:
                        break;
                }
                if (y > map.yMax() || y < map.yMin() || x > map.xMax() || x < map.xMin()) {
                    return null;
                }
                navigable = map.getNavigable(x, y);
                if (navigable instanceof Island) {
                    if (weight < 0) {
                        return null;
                    }
                    island.crossed(weight);
                    IslandValidation newIsland = new IslandValidation((Island) navigable);
                    return newIsland;
                } else {
                    weight = ((Bridge) navigable).getWeight();
                }
            }
        } else {
            return march(island, map);
        }
    }

    /**
     * Tests whether a bridge exists in the current direction from the given
     * island.
     *
     * <p>
     * Checks the immediate adjacent cell in the current direction. If it is a
     * {@link Bridge} aligned with the direction, returns {@code true}.
     *
     * @param island the island to check from
     * @param map the puzzle map
     * @return {@code true} if a bridge exists in the current direction
     */
    private static boolean isBridge(IslandValidation island, Map map) {
        char dir = island.getDir();
        int x = island.getIsland().x;
        int y = island.getIsland().y;

        switch (dir) {
            case 'N':
                y--;
                break;
            case 'E':
                x++;
                break;
            case 'S':
                y++;
                break;
            case 'W':
                x--;
                break;
            default:
                return false;
        }

        if (y > map.yMax() || y < map.yMin() || x > map.xMax() || x < map.xMin()) {
            return false;
        }

        Navigable navigable = map.getMap()[y][x];
        if (navigable instanceof Bridge) {
            Bridge bridge = (Bridge) navigable;
            return bridge.matchDir(dir);
        }
        return false;
    }
}
