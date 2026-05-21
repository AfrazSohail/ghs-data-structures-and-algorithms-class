
import java.util.HashMap;
import java.util.HashSet;

/**
 * Stores TrainGame data and coordinates file loading and pathfinding calls.
 * Written by AI for Afraz Sohail.
 *
 * @author Afraz Sohail
 */
public class TicketToRide {

    /** Stores all unique cities loaded from the route file. */
    private static final HashSet<City> cities = new HashSet<>();
    /** Stores the graph of routes connected to each city. */
    private static final HashMap<City, HashSet<Route>> graph = new HashMap<>();
    /** Stores the available player tracks used for constrained searches. */
    private static final TrackBag bag = new TrackBag();
    /** Stores the total number of tracks loaded from the track file. */
    private static int tracksAmount;

    /**
     * Finds a city object by name, ignoring case and surrounding whitespace.
     *
     * @param name the city name to search for
     * @return the matching city, or {@code null} if none exists
     */
    public static City getCityByName(String name) {
        for (City city : cities) {
            if (city.getName().equalsIgnoreCase(name.trim())) {
                return city;
            }
        }
        return null;
    }

    /**
     * Loads the route graph and track counts from user-selected files.
     */
    public static void loadFiles() {
        FileLoader.loadRoutes(cities, graph);
        FileLoader.loadTracks(bag);
        int count = 0;
        for (Route.Color color : Route.Color.values()) {
            count += bag.getTrackCount(color);
        }
        tracksAmount = count;
    }

    /**
     * Runs the city-based shortest path search.
     *
     * @param start the starting city
     * @param end the destination city
     */
    public static void dijkstraCity(City start, City end) {
        DijkstraCity.find(bag, graph, cities, start, end);
    }

    /**
     * Runs the constrained route-based shortest path search.
     *
     * @param start the starting city
     * @param end the destination city
     */
    public static void dijkstraRoute(City start, City end) {
        DijkstraRoute.find(bag, cities, graph, start, end);
    }

    /**
     * Prints all loaded cities, routes, and track counts.
     */
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
