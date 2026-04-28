package Weighted;

import java.util.Arrays;
import java.util.HashSet;

public class StarTrek {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Celest Earth = new Celest("Earth");
		Celest Moon = new Celest("Moon");
		Celest Mercury = new Celest("Mercury");
		Celest Venus = new Celest("Venus");
		Celest Mars = new Celest("Mars");
		Earth.neighbors.put(Moon, 1.0);
		Earth.neighbors.put(Mars, 10.0);
		Earth.neighbors.put(Mercury, 5.0);

		Moon.neighbors.put(Earth, 8.0);
		Moon.neighbors.put(Mercury, 3.0);
		Moon.neighbors.put(Mars, 5.0);

		Mercury.neighbors.put(Venus, 4.0);

		Venus.neighbors.put(Earth, 3.0);

		Mars.neighbors.put(Venus, 6.0);

		Celest Neptune = new Celest("Neptune");
		Celest Saturn = new Celest("Saturn");

		Neptune.neighbors.put(Saturn, 5.0);

		Saturn.neighbors.put(Neptune, 7.0);
		HashSet<Celest> celests = new HashSet<Celest>();
		celests.addAll(Arrays.asList(Earth, Moon, Mercury, Venus, Mars,Neptune,Saturn));
		SpaceAgency NASA = new SpaceAgency(celests, Earth);
		System.out.println(NASA);
		System.out.println(NASA.getCheapest());
		System.out.println(NASA.getRoute(Neptune));

		NASA.hub = Neptune;
		System.out.println(NASA.getCheapest());
        System.out.println("TESTING MST NOW");
        NASA.hub = Earth;
        System.out.println(NASA.prims());
        Mars.neighbors.put(Saturn, 20d);
        System.out.println(NASA.prims());

	}

}
