package Weighted;

import java.util.HashSet;

public class SpaceAgency {
    HashSet<Celeste> celestes;
    Celeste hub;

    public SpaceAgency(HashSet<Celeste> celestes, Celeste hub) {
        this.celestes = celestes;
        this.hub = hub;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("Hub: " + hub.name + "\n");
        for (Celeste celeste : celestes) {
            output.append(celeste.name + "->" + celeste.price + "\n");
            for (Celeste neighbor : celeste.neighbors.keySet()) {
                output.append("\t" + neighbor.name + " " + celeste.neighbors.get(neighbor) + "\n");
            }
        }
        return output.toString();
    }

    public void resetPrices() {
        for (Celeste celeste : celestes) {
            if (celeste != hub) {
                celeste.price = Double.POSITIVE_INFINITY;
            } else {
                celeste.price = 0.0;
            }
        }
    }

    public String getCheapest() {
        //dijkstra's algorithm
        StringBuilder output = new StringBuilder();
        for (Celeste celeste : celestes) {
            output.append(celeste.name + " " + celeste.price + "\n");
        }
    }
}
