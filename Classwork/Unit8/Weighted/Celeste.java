package Weighted;

import java.util.HashMap;

public class Celeste {
    String name;
    double price;
    HashMap<Celeste, Double> neighbors;

    public Celeste(String name) {
        this.name = name;
        neighbors = new HashMap<Celeste, Double>();
    }
}
