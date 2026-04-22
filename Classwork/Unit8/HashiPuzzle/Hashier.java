package HashiPuzzle;

import java.util.HashSet;
import java.util.Stack;

public class Hashier {
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
                if (newIsland == null)
                    return false;
                if (islandSet.contains(newIsland.getIsland())||islandStack.contains(newIsland)) {
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

    private static IslandValidation march(IslandValidation island, Map map) {
        char dir = island.nextDir();
        if (dir == ' ')
            return null;
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
                    if (weight < 0)
                        return null;
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
