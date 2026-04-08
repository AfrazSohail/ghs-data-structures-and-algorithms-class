package HashiPuzzle;

import java.util.Stack;

public class Hashier {
    private boolean isSolution(Map map) {
        Stack<Island> islandStack = new Stack<Island>();
        islandStack.push(getFirstIsland(map));

        return true;
    }

    private static Island getFirstIsland(Map map) {
            for (Navigable[] row: map.getMap()) {
                for (Navigable navigable : row) {
                    if (navigable != null && navigable.isIsland()) {
                        return (Island) navigable;
                    }
                }
            }
            return null;
    }
}
