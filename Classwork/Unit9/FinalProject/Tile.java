
public class Tile {

    private Position pos;
    private TILE_TYPE tileType;
    private Unit unit = null;

    public static enum TILE_TYPE {
        SIMPLE, COVER, OBSTACLE
    }

    public Tile(int x, int y, TILE_TYPE tileType) {
        pos = new Position(x, y);
        this.tileType = tileType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(pos.getX()).append(", ").append(pos.getY()).append(", ").append((tileType + "").charAt(0))
                .append(", ").append(unit).append("]");
        return sb.toString();
    }
}
