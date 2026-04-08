package HashiPuzzle;

import java.util.HashSet;

public class Map {
    private final Navigable[][] map;
    private HashSet<Island> islandSet = new HashSet<Island>();

    public int xMin() {
        return 0;
    }

    public int yMin() {
        return 0;
    }

    public int xMax(){
        return map.length > 0 ? map[0].length - 1 : -1;
    }

    public int yMax() {
        return map.length - 1;
    }

    public Map(String str) {
        int[] size = getSize(str);
        map = new Navigable[size[0]][size[1]];
        makeNavigable(str, size[0], size[1]);
    }

    public int[] getSize(String str) {
        int[] size = new int[2];
        String[] lines = str.split("\n");
        size[0] = lines.length;
        for (String row: lines)
            size[1] = Math.max(size[1], row.length());
        return size;
    }

    public void makeNavigable(String str, int rowLength, int colLength) {
        String[] lines = str.split("\n");
        for (int y = 0; y < lines.length; y++) {
            for (int x = 0; x < lines[y].length(); x++) {
                if (map[y][x] == null) {
                    map[y][x] = getNavigable(lines[y].toCharArray()[x], x, y);
                }
            }
        }
    }

    public Navigable getNavigable(int x, int y) {
        return map[y][x];
    }

    private Navigable getNavigable(char ch, int x, int y) {
        if (Character.isDigit(ch)) {
            Island island = new Island(ch, x, y);
            islandSet.add(island);
            return island;
        }
        Navigable navigable;
        switch (ch) {
            case '#':
                navigable = new Bridge('|', 2, x, y);
                break;
            case '|':
                navigable = new Bridge('|', 1, x, y);
                break;
            case '-':
                navigable = new Bridge('-', 1, x, y);
                break;
            case '=':
                navigable = new Bridge('-', 2, x, y);
                break;
            default:
                navigable = null;
        }
        return navigable;
    }

    public Navigable[][] getMap() {
        Navigable[][] clone = new Navigable[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                clone[i][j] = map[i][j];
            }
        }
        return clone;
    }

    public HashSet<Island> islandSet() {
        return islandSet;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Navigable[] row: map) {
            for (Navigable navigable : row) {
                if (navigable == null) {
                    sb.append("   ");
                } else {
                    if (navigable instanceof Island) {
                        sb.append("[").append(navigable.getChar()).append("]");
                    } else {
                        if (((Bridge) navigable).getDir() == '|') {
                            sb.append(" "+ navigable.getChar()+" ");
                        } else {
                            sb.append((navigable.getChar() + "").repeat(3));
                        }
                    }
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
