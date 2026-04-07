package HashiPuzzle;

public class Hashier {
    public static Island getFirstIsland(Map map) {
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
