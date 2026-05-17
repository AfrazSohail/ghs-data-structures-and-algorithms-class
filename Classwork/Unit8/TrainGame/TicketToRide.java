
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Main game logic class for the Ticket to Ride train game. Manages cities,
 * tracks, and graph operations including pathfinding algorithms. Provides
 * methods to load game data from CSV files and display game information.
 *
 * @author Afraz Sohail
 * @version 1.0
 * @note Documentation written by insert model
 */
public class TicketToRide {

    public static HashMap<String, City> cities = new HashMap<>();
    public static HashMap<City, ArrayList<Track>> graph = new HashMap<>();
    public static HashMap<Track.TrackColor, Integer> tracks = new HashMap<>();

    /**
     * Loads cities and tracks from a CSV file. Expected CSV format: city1,
     * city2, distance, color
     *
     * @param path the file path to the CSV file containing city and track data
     */
    public static void loadCities(String path) {
        try (Scanner sc = new Scanner(new File(path))) {
            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String parts[] = line.split(",");
                City city1 = addCity(parts[0].strip());
                City city2 = addCity(parts[1].strip());
                int distance = Integer.parseInt(parts[2].strip());
                Track.TrackColor color = Track.charToColor(parts[3].strip().charAt(0));
                Track track = new Track(city1, city2, distance, color);
                graph.putIfAbsent(city1, new ArrayList<>());
                graph.putIfAbsent(city2, new ArrayList<>());
                graph.get(city1).add(track);
                graph.get(city2).add(track);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + path);
        }
    }

    /**
     * Loads track color information and their point values from a CSV file.
     * Expected CSV format: color, points
     *
     * @param path the file path to the CSV file containing track color point
     * values
     */
    public static void loadTracks(String path) {
        // Ensure all colors are present with a default count of 0
        for (Track.TrackColor color : Track.TrackColor.values()) {
            tracks.put(color, 0);
        }
        try (Scanner sc = new Scanner(new File(path))) {
            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String parts[] = line.split(",");
                Track.TrackColor color = Track.charToColor(parts[0].strip().charAt(0));
                int points = Integer.parseInt(parts[1].strip());
                tracks.put(color, points);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + path);
        }
    }

    private static class CityDistance implements Comparable<CityDistance> {

        private final City city;
        private final int distance;

        private CityDistance(City city, int distance) {
            this.city = city;
            this.distance = distance;
        }

        @Override
        public int compareTo(CityDistance other) {
            return Integer.compare(distance, other.distance);
        }
    }

    /**
     * Returns a formatted string representation of the entire game graph.
     *
     * @return a string displaying all cities and their connected tracks
     */
    public static String displayGraph() {
        StringBuilder output = new StringBuilder();
        for (City city : graph.keySet()) {
            output.append(city).append("\n");
            for (Track track : graph.get(city)) {
                output.append(track);
            }
        }
        return output.toString();
    }

    /**
     * Adds a city to the game if it does not already exist.
     *
     * @param name the name of the city to add
     * @return the City object, either newly created or existing
     */
    private static City addCity(String name) {
        City city = cities.get(name);
        if (city == null) {
            city = new City(name, 0);
            cities.put(name, city);
        }
        return city;
    }

    /**
     * Finds and displays the shortest path between two cities using Dijkstra's
     * algorithm without track constraints.
     *
     * @param start the name of the starting city
     * @param end the name of the destination city
     */
    public static void dijkstraSimple(String start, String end) {
        if (!cities.containsKey(start) || !cities.containsKey(end)) {
            System.out.println("One or both cities not found: " + start + ", " + end);
            return;
        }
        City startCity = cities.get(start);
        City endCity = cities.get(end);
        HashMap<City, Integer> distances = new HashMap<>();
        HashMap<City, City> previous = new HashMap<>();
        setupDijkstra(startCity, distances, previous);
        PriorityQueue<CityDistance> toVisit = new PriorityQueue<>();
        HashSet<City> visited = new HashSet<>();
        toVisit.add(new CityDistance(startCity, 0));

        while (!toVisit.isEmpty()) {
            City current = toVisit.poll().city;
            if (!visited.add(current)) {
                continue;
            }
            for (Track track : graph.getOrDefault(current, new ArrayList<>())) {
                City neighbour = track.getCity1().equals(current) ? track.getCity2() : track.getCity1();
                int newDist = distances.get(current) + track.getDistance();
                if (newDist < distances.get(neighbour)) {
                    distances.put(neighbour, newDist);
                    previous.put(neighbour, current);
                    toVisit.add(new CityDistance(neighbour, newDist));
                }
            }
        }

        ArrayList<City> path = getCityPath(endCity, previous);
        System.out.println("Path without constraints: ");
        System.out.println(path.toString().trim());

        ArrayList<Track> trackPaths = getTrackPath(path);
        checkPath(trackPaths);
        DijkstraComplex(start, end);
    }

    /**
     * Reconstructs the path from start to end city using the previous map.
     *
     * @param endCity the destination city
     * @param previous the map of previous cities in the path
     * @return an ArrayList of cities representing the path
     */
    private static ArrayList<City> getCityPath(City endCity, HashMap<City, City> previous) {
        ArrayList<City> path = new ArrayList<>();
        City current = endCity;
        while (current != null) {
            path.add(0, current);
            current = previous.get(current);
        }
        return path;
    }

    /**
     * Converts a sequence of cities into the tracks connecting them.
     *
     * @param path the ArrayList of cities
     * @return an ArrayList of tracks connecting consecutive cities in the path
     */
    private static ArrayList<Track> getTrackPath(ArrayList<City> path) {
        ArrayList<Track> trackPaths = new ArrayList<>();
        for (int i = 0; i < path.size() - 1; i++) {
            City city1 = path.get(i);
            City city2 = path.get(i + 1);
            for (Track track : graph.get(city1)) {
                if ((track.getCity1().equals(city1) && track.getCity2().equals(city2))
                        || (track.getCity1().equals(city2) && track.getCity2().equals(city1))) {
                    trackPaths.add(track);
                    break;
                }
            }
        }
        return trackPaths;
    }

    /**
     * Initializes the Dijkstra algorithm data structures. Sets all distances to
     * infinity except the start city.
     *
     * @param start the starting city
     * @param distances map to store the distance to each city
     * @param previous map to store the previous city in the path
     */
    private static void setupDijkstra(City start, HashMap<City, Integer> distances, HashMap<City, City> previous) {
        for (City city : graph.keySet()) {
            distances.put(city, Integer.MAX_VALUE);
            previous.put(city, null);
        }
        distances.put(start, 0);
    }

    /**
     * Validates if a path can be constructed with available tracks. Checks
     * color availability and handles grey tracks as wildcards.
     *
     * @param path the ArrayList of tracks in the path
     */
    private static void checkPath(ArrayList<Track> path) {
        int greyCount = 0;
        HashMap<Track.TrackColor, Integer> colorCounts = new HashMap<>();
        for (Track track : path) {
            if (track.getColor() == Track.TrackColor.X) {
                greyCount++;
            } else {
                colorCounts.put(track.getColor(), colorCounts.getOrDefault(track.getColor(), 0) + 1);
            }
        }

        HashMap<Track.TrackColor, Integer> needed = new HashMap<>();
        int totalRemaining = 0;
        for (Track.TrackColor color : Track.TrackColor.values()) {
            if (color == Track.TrackColor.X) {
                continue;
            }
            int available = tracks.getOrDefault(color, 0);
            int required = colorCounts.getOrDefault(color, 0);
            if (required > available) {
                needed.put(color, required - available);
            }
            totalRemaining += Math.max(0, available - required);
        }

        if (greyCount > totalRemaining) {
            needed.put(Track.TrackColor.X, greyCount - totalRemaining);
        }

        if (needed.isEmpty()) {
            System.out.println("Path is valid with the available tracks.");
        } else {
            System.out.println("Path is not valid. You need " + needed + " more tracks.");
        }
    }

    /**
     * Finds and displays the shortest valid path between two cities using
     * Dijkstra's algorithm with track constraints.
     *
     * @param start the name of the starting city
     * @param end the name of the destination city
     */
    public static void DijkstraComplex(String start, String end) {
        if (!cities.containsKey(start) || !cities.containsKey(end)) {
            System.out.println("One or both cities not found: " + start + ", " + end);
            return;
        }
        City startCity = cities.get(start);
        City endCity = cities.get(end);
        HashMap<City, Integer> distances = new HashMap<>();
        HashMap<City, City> previous = new HashMap<>();
        setupDijkstra(startCity, distances, previous);
        PriorityQueue<CityDistance> toVisit = new PriorityQueue<>();
        HashSet<City> visited = new HashSet<>();
        toVisit.add(new CityDistance(startCity, 0));

        while (!toVisit.isEmpty()) {
            City current = toVisit.poll().city;
            if (!visited.add(current)) {
                continue;
            }
            for (Track track : graph.getOrDefault(current, new ArrayList<>())) {
                City neighbour = track.getCity1().equals(current) ? track.getCity2() : track.getCity1();
                int newDist = distances.get(current) + track.getDistance();
                ArrayList<City> cityPathToCurrent = getCityPath(current, previous);
                ArrayList<Track> trackPathToNeighbour = getTrackPath(cityPathToCurrent);
                trackPathToNeighbour.add(track);
                if (newDist < distances.get(neighbour) && isValidPath(trackPathToNeighbour)) {
                    distances.put(neighbour, newDist);
                    previous.put(neighbour, current);
                    toVisit.add(new CityDistance(neighbour, newDist));
                }
            }
        }

        System.out.print("Path with constraints: ");
        if (previous.get(endCity) == null) {
            System.out.println("No valid path found from " + start + " to " + end);
            return;
        }
        ArrayList<City> path = getCityPath(endCity, previous);
        System.out.println(path.toString().trim());
        ArrayList<Track> trackPaths = getTrackPath(path);
        checkPath(trackPaths);
    }

    /**
     * Validates if a path can be constructed with available tracks. Checks
     * color availability and handles grey tracks as wildcards.
     *
     * @param path the ArrayList of tracks in the path
     * @return true if the path is valid with available tracks, false otherwise
     */
    private static boolean isValidPath(ArrayList<Track> path) {
        int greyCount = 0;
        HashMap<Track.TrackColor, Integer> colorCounts = new HashMap<>();
        for (Track track : path) {
            if (track.getColor() == Track.TrackColor.X) {
                greyCount++;
            } else {
                colorCounts.put(track.getColor(), colorCounts.getOrDefault(track.getColor(), 0) + 1);
            }
        }

        int totalRemaining = 0;
        for (Track.TrackColor color : Track.TrackColor.values()) {
            if (color == Track.TrackColor.X) {
                continue;
            }
            int available = tracks.getOrDefault(color, 0);
            int required = colorCounts.getOrDefault(color, 0);
            if (required > available) {
                return false;
            }
            totalRemaining += available - required;
        }

        return totalRemaining >= greyCount;
    }
}
