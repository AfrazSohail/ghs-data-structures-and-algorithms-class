public class Unit {
    public static enum SOLD_CLASS {
        INFANTRY, RECON, ENGINEER, TANK
    }

    private SOLD_CLASS soldierClass;
    private Tile tile;

    public Unit(SOLD_CLASS soldClass, Tile tile) {
        this.soldierClass = soldClass;
        this.tile = tile;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append((soldierClass + "").charAt(0));
        return (sb.toString());
    }
}
