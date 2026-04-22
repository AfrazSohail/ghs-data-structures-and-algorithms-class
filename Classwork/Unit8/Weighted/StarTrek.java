package Weighted;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StarTrek {
    public static void main(String args[]) {
        Celeste earth = new Celeste("Earth");
        Celeste moon = new Celeste("Moon");
        Celeste mars = new Celeste("Mars");
        Celeste venus = new Celeste("Venus");
        Celeste mercury = new Celeste("Mercury");

        earth.neighbors.put(moon, 1.0);
        earth.neighbors.put(mars, 10.0);
        earth.neighbors.put(mercury, 5.0);

        moon.neighbors.put(earth, 8.0);
        moon.neighbors.put(mercury, 3.0);

        mercury.neighbors.put(venus, 4.0);

        venus.neighbors.put(earth, 3.0);

        mars.neighbors.put(venus, 6.0);

        HashSet<Celeste> celestes = new HashSet<>(Set.of(earth, moon, mars, venus, mercury));

        SpaceAgency NASA = new SpaceAgency(celestes, earth);

        System.out.println(NASA);
    }
}
