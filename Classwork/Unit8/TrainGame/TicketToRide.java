import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class TicketToRide {

    private static HashSet<City> cities = new HashSet<>();
    private static HashMap<City, HashSet<Route>> graph = new HashMap<>();
    private static HashMap<Route.Color, Integer> tracks = new HashMap<>();

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
        FileLoader.loadTracks(tracks);
    }

    public static void dijkstraSimple(City start, City end) {
        HashMap<City, Integer> distances = new HashMap<>();
        for (City city : cities) {
            distances.put(city, Integer.MAX_VALUE);
        }
        distances.put(start, 0);
        HashMap<City, City> previous = new HashMap<>();
        PriorityQueue<City> pq = new PriorityQueue<>((c1, c2) -> distances.get(c1) - distances.get(c2));

        pq.add(start);
        distances.put(start, 0);
        previous.put(start, null);

        while (!pq.isEmpty()) {
            City current = pq.poll();
            for (Route route : graph.get(current)) {
                City neighbour = route.getNeighbour(current);
                int dist = distances.get(current) + route.getDistance();
                if (dist < distances.get(neighbour)) {
                    distances.put(neighbour, dist);
                    previous.put(neighbour, current);
                    pq.add(neighbour);
                }
            }
        }
        printPath(end, previous);
        System.out.println();
    }

    private static void printPath(City end, HashMap<City, City> previous) {
        StringBuilder sb = new StringBuilder();
        City current = end;
        while (current != null) {
            sb.insert(0, current + " -> ");
            current = previous.get(current);
        }
        if (sb.length() > 4) {
            sb.setLength(sb.length() - 4);
        }
        System.out.println(sb.toString());
    }

    private static boolean isValidPath(ArrayList<ArrayList<Routes>> path) {

    }

    private static ArrayList<ArrayList<Route>> getPath(City end, HashMap<City, City> previous) {
        ArrayList<ArrayList<Route>> path = new ArrayList<>();
        City current = end;
        while (current != null) {
            City prev = previous.get(current);
            if (prev != null) {
                ArrayList<Route> routes = new ArrayList<>();
                for (Route route : graph.get(current)) {
                    if (route.containsCities(current, prev)) {
                        routes.add(route);
                    }
                }
                path.add(0, routes);
            }
            current = prev;
        }
        return path;
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
        for (Route.Color color : tracks.keySet()) {
            System.out.println(color + ": " + tracks.get(color));
        }
    }
}
