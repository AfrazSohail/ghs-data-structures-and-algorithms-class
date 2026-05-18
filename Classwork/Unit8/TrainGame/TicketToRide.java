
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class TicketToRide {

    private static class FeasibilityResult {

        private final boolean canFinish;
        private final HashMap<Route.Color, Integer> missingTracks;

        private FeasibilityResult(boolean canFinish, HashMap<Route.Color, Integer> missingTracks) {
            this.canFinish = canFinish;
            this.missingTracks = missingTracks;
        }
    }

    private static final HashSet<City> cities = new HashSet<>();
    private static final HashMap<City, HashSet<Route>> graph = new HashMap<>();
    private static final TrackBag tracks = new TrackBag();
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
        FileLoader.loadTracks(tracks);
        int count = 0;
        for (Route.Color color : Route.Color.values()) {
            count += tracks.getTrackCount(color);
        }
        tracksAmount = count;
    }

    public static void dijkstraComplex(City start, City end) {
        HashMap<City, Integer> distances = new HashMap<>();
        HashMap<City, City> prevCities = new HashMap<>();

        for (City city : cities) {
            distances.put(city, Integer.MAX_VALUE);
        }

        distances.put(start, 0);
        prevCities.put(start, null);

        PriorityQueue<City> pq = new PriorityQueue<>((c1, c2) -> Integer.compare(distances.get(c1), distances.get(c2)));
        pq.add(start);

        while (!pq.isEmpty()) {
            City current = pq.poll();
            int currentDist = distances.get(current);
            if (currentDist == Integer.MAX_VALUE) {
                continue;
            }

            HashSet<Route> neighbours = graph.get(current);
            if (neighbours == null) {
                continue;
            }

            for (Route route : neighbours) {
                if (!canAffordRoute(route)) {
                    continue;
                }
                City neighbour = route.getNeighbour(current);
                int candidate = currentDist + route.getDistance();
                if (candidate < distances.get(neighbour)) {
                    distances.put(neighbour, candidate);
                    prevCities.put(neighbour, current);
                    pq.add(neighbour);
                }
            }
        }

        if (distances.get(end) == Integer.MAX_VALUE) {
            System.out.println("No path found.");
            return;
        }
        printPath(end, prevCities);
        System.out.println("Shortest feasible path distance: " + distances.get(end));
        ArrayList<ArrayList<Route>> shortestPath = getPath(end, prevCities);
        FeasibilityResult result = analyzePathFeasibility(shortestPath);
        System.out.println("Can finish shortest path with available tracks: " + result.canFinish);
        if (!result.canFinish) {
            System.out.println("More tracks needed: " + formatMissingTracks(result.missingTracks));
        }
        System.out.println();
    }

    private static boolean canAffordRoute(Route route) {
        int distance = route.getDistance();
        if (route.getColor() == Route.Color.X) {
            for (Route.Color color : Route.Color.values()) {
                if (color != Route.Color.X && tracks.getTrackCount(color) >= distance) {
                    return true;
                }
            }
            return false;
        }
        return tracks.getTrackCount(route.getColor()) >= distance;
    }

    public static void dijkstraSimple(City start, City end) {
        HashMap<City, Integer> distances = new HashMap<>();
        HashMap<City, City> previous = new HashMap<>();

        for (City city : cities) {
            distances.put(city, Integer.MAX_VALUE);
        }

        distances.put(start, 0);
        previous.put(start, null);

        PriorityQueue<City> pq = new PriorityQueue<>((c1, c2) -> Integer.compare(distances.get(c1), distances.get(c2)));
        pq.add(start);

        while (!pq.isEmpty()) {
            City current = pq.poll();
            int currentDist = distances.get(current);
            if (currentDist == Integer.MAX_VALUE) {
                continue;
            }

            HashSet<Route> neighbors = graph.get(current);
            if (neighbors == null) {
                continue;
            }

            for (Route route : neighbors) {
                City neighbor = route.getNeighbour(current);
                int candidate = currentDist + route.getDistance();
                if (candidate < distances.get(neighbor)) {
                    distances.put(neighbor, candidate);
                    previous.put(neighbor, current);
                    pq.add(neighbor);
                }
            }
        }

        if (distances.get(end) == Integer.MAX_VALUE) {
            System.out.println("No path found.");
            return;
        }

        printPath(end, previous);
        ArrayList<ArrayList<Route>> shortestPath = getPath(end, previous);
        FeasibilityResult result = analyzePathFeasibility(shortestPath);

        System.out.println("Can finish shortest path with available tracks: " + result.canFinish);
        if (!result.canFinish) {
            System.out.println("More tracks needed: " + formatMissingTracks(result.missingTracks));
        }
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

    private static FeasibilityResult analyzePathFeasibility(ArrayList<ArrayList<Route>> path) {
        if (path.isEmpty()) {
            return new FeasibilityResult(true, new HashMap<>());
        }

        HashMap<Route.Color, Integer> remaining = new HashMap<>();
        for (Route.Color color : Route.Color.values()) {
            remaining.put(color, tracks.getTrackCount(color));
        }

        HashMap<Route.Color, Integer> missingTracks = minMissingTracks(path, 0, remaining);
        boolean canFinish = totalMissingCount(missingTracks) == 0;
        return new FeasibilityResult(canFinish, missingTracks);
    }

    private static HashMap<Route.Color, Integer> minMissingTracks(ArrayList<ArrayList<Route>> path, int index,
            HashMap<Route.Color, Integer> remaining) {
        if (index == path.size()) {
            return new HashMap<>();
        }

        ArrayList<Route> options = path.get(index);
        if (options.isEmpty()) {
            HashMap<Route.Color, Integer> impossible = new HashMap<>();
            impossible.put(Route.Color.X, Integer.MAX_VALUE / 4);
            return impossible;
        }

        HashMap<Route.Color, Integer> bestMissing = null;
        for (Route option : options) {
            int distance = option.getDistance();
            Route.Color routeColor = option.getColor();

            if (routeColor == Route.Color.X) {
                for (Route.Color spendColor : Route.Color.values()) {
                    if (spendColor == Route.Color.X) {
                        continue;
                    }
                    HashMap<Route.Color, Integer> candidate = evaluateChoice(path, index, remaining, spendColor,
                            distance);
                    if (isBetterMissing(candidate, bestMissing)) {
                        bestMissing = candidate;
                    }
                }
            } else {
                HashMap<Route.Color, Integer> candidate = evaluateChoice(path, index, remaining, routeColor, distance);
                if (isBetterMissing(candidate, bestMissing)) {
                    bestMissing = candidate;
                }
            }
        }

        return bestMissing == null ? new HashMap<>() : bestMissing;
    }

    private static HashMap<Route.Color, Integer> evaluateChoice(ArrayList<ArrayList<Route>> path, int index,
            HashMap<Route.Color, Integer> remaining, Route.Color spendColor, int distance) {
        int available = remaining.getOrDefault(spendColor, 0);
        int used = Math.min(available, distance);
        int shortBy = distance - used;

        remaining.put(spendColor, available - used);
        HashMap<Route.Color, Integer> downstream = minMissingTracks(path, index + 1, remaining);
        remaining.put(spendColor, available);

        HashMap<Route.Color, Integer> result = new HashMap<>(downstream);
        if (shortBy > 0) {
            result.put(spendColor, result.getOrDefault(spendColor, 0) + shortBy);
        }
        return result;
    }

    private static boolean isBetterMissing(HashMap<Route.Color, Integer> candidate, HashMap<Route.Color, Integer> current) {
        if (current == null) {
            return true;
        }

        int candidateTotal = totalMissingCount(candidate);
        int currentTotal = totalMissingCount(current);
        if (candidateTotal != currentTotal) {
            return candidateTotal < currentTotal;
        }

        for (Route.Color color : Route.Color.values()) {
            int left = candidate.getOrDefault(color, 0);
            int right = current.getOrDefault(color, 0);
            if (left != right) {
                return left < right;
            }
        }

        return false;
    }

    private static int totalMissingCount(HashMap<Route.Color, Integer> missingTracks) {
        int sum = 0;
        for (int count : missingTracks.values()) {
            if (count > 0) {
                sum += count;
            }
        }
        return sum;
    }

    private static String formatMissingTracks(HashMap<Route.Color, Integer> missingTracks) {
        StringBuilder sb = new StringBuilder();
        for (Route.Color color : Route.Color.values()) {
            if (color == Route.Color.X) {
                continue;
            }

            int need = missingTracks.getOrDefault(color, 0);
            if (need > 0) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(color).append(": ").append(need);
            }
        }

        return sb.length() == 0 ? "none" : sb.toString();
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
        for (Route.Color color : Route.Color.values()) {
            System.out.println(color + ": " + tracks.getTrackCount(color));
        }
        System.out.println("Total tracks: " + tracksAmount);
    }
}
