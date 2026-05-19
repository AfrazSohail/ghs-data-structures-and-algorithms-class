
import java.util.HashMap;
import java.util.HashSet;

public class TicketToRide {

    private static final HashSet<City> cities = new HashSet<>();
    private static final HashMap<City, HashSet<Route>> graph = new HashMap<>();
    private static final TrackBag bag = new TrackBag();
    private static int tracksAmount;

    public static City getCityByName(String name) {
        for (City city : cities) {
            if (city.getName().equalsIgnoreCase(name.trim())) {
                return city;
            }
        }
        return null;
    }

    public static void loadFiles() {
        FileLoader.loadRoutes(cities, graph);
        FileLoader.loadTracks(bag);
        int count = 0;
        for (Route.Color color : Route.Color.values()) {
            count += bag.getTrackCount(color);
        }
        tracksAmount = count;
    }

    public static void dijkstraCity(City start, City end) {
        DijkstraCity.find(bag, graph, cities, start, end);
    }

    public static void dijkstraRoute(City start, City end) {
        DijkstraRoute.find(bag, cities, graph, start, end);
    }

    public static void displayData() {
        System.out.println("Cities:");
        for (City city : cities) {
            System.out.println(city);
        }

        System.out.println("\nRoutes:");
        for (City city : graph.keySet()) {
            System.out.println(city + ":");
            for (Route route : graph.get(city)) {
                System.out.println("\t" + route);
            }
        }

        System.out.println("\nTracks:");
        for (Route.Color color : Route.Color.values()) {
            System.out.println(color + ": " + bag.getTrackCount(color));
        }
        System.out.println("Total tracks: " + tracksAmount);
    }
}
