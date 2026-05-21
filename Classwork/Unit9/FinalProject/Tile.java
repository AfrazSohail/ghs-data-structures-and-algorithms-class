package FinalProject;

import javax.swing.text.Position;

public class Tile {

    private Position pos;
    private TILE_TYPE tileType;

    private static enum TILE_TYPE {
        SIMPLE, SHORT, TALL
    }

    public Tile(int x, int y, String type) {
        pos = new Position(x, y);
        tileType = TILE_TYPE.valueOf(type.toUpperCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(pos.getX()).append(", ").append(pos.getY()).append(", ").append(tileType).append("]");
        return sb.toString();
    }
}
