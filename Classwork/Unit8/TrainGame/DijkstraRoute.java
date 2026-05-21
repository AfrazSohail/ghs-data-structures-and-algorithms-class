
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Finds the shortest route sequence that can be paid for with the available tracks.
 * Written by AI for Afraz Sohail.
 *
 * @author Afraz Sohail
 */
public class DijkstraRoute {

    /**
     * Runs a constrained Dijkstra search across route states and prints the result.
     *
     * @param bag the available track bag
     * @param cities the set of all cities
     * @param graph the adjacency map of routes by city
     * @param start the starting city
     * @param end the destination city
     */
    public static void find(TrackBag bag, HashSet<City> cities, HashMap<City, HashSet<Route>> graph, City start,
            City end) {
        if (start.equals(end)) {
            System.out.println("Start and end cities are the same: " + start);
            return;
        }
        HashMap<Route, Integer> distances = new HashMap<>();
        for (City city : cities) {
            HashSet<Route> routes = graph.get(city);
            if (routes != null) {
                for (Route route : routes) {
                    distances.put(route, Integer.MAX_VALUE);
                }
            }
        }

        HashMap<Route, TrackBag> costs = new HashMap<>();
        for (Route route : graph.get(start)) {
            TrackBag cost = new TrackBag();
            cost.addTracks(route.getColor(), route.getDistance());
            boolean affordable = canAfford(cost, bag);
            if (affordable) {
                costs.put(route, cost);
            }
        }

        HashMap<Route, Route> previous = new HashMap<>();
        for (Route route : graph.get(start)) {
            if (costs.containsKey(route)) {
                previous.put(route, null);
            }
        }

        HashMap<Route, City> originCities = new HashMap<>();
        for (Route route : graph.get(start)) {
            if (costs.containsKey(route)) {
                originCities.put(route, start);
            }
        }

        for (Route route : graph.get(start)) {
            if (costs.containsKey(route)) {
                distances.put(route, route.getDistance());
            }
        }

        HashSet<Route> visited = new HashSet<>();
        PriorityQueue<Route> pq = new PriorityQueue<>(
                (r1, r2) -> Integer.compare(distances.get(r1), distances.get(r2)));
        for (Route route : graph.get(start)) {
            if (costs.containsKey(route)) {
                pq.add(route);
            }
        }

        Route endRoute = null;

        while (!pq.isEmpty()) {
            if (endRoute != null) {
                break;
            }
            Route current = pq.poll();
            if (visited.contains(current)) {
                continue;
            }
            visited.add(current);

            int currentDist = distances.get(current);
            if (currentDist == Integer.MAX_VALUE) {
                continue;
            }

            City currentCity = originCities.get(current);
            City destinationCity = current.getNeighbour(currentCity);
            HashSet<Route> neighbors = graph.get(destinationCity);
            if (neighbors == null) {
                continue;
            }

            for (Route route : neighbors) {
                if (endRoute != null) {
                    break;
                }
                if (!route.containsCity(destinationCity)) {
                    continue;
                }

                int candidate = currentDist + route.getDistance();
                TrackBag currentCost = costs.get(current);
                if (currentCost == null) {
                    currentCost = new TrackBag();
                }
                TrackBag candidateCost = new TrackBag(currentCost);
                candidateCost.addTracks(route.getColor(), route.getDistance());
                boolean better = candidate < distances.get(route);

                boolean affordable = canAfford(candidateCost, bag);
                if (better && affordable) {
                    City neighbourCity = route.getNeighbour(destinationCity);
                    if (neighbourCity.equals(end)) {
                        endRoute = route;
                    }
                    distances.put(route, candidate);
                    previous.put(route, current);
                    originCities.put(route, destinationCity);
                    costs.put(route, candidateCost);
                    pq.add(route);
                }
            }
        }

        boolean pathFound = false;
        if (endRoute != null && originCities.get(endRoute) != null) {
            System.out
                    .println("Path found with distance " + distances.get(endRoute) + " and cost " + costs.get(endRoute));
            System.out.println("Path: " + getPath(endRoute, previous));
            pathFound = true;
        }
        if (!pathFound) {
            System.out.println("No path found.");
        }
    }

    /**
     * Reconstructs the route sequence from the previous-route map.
     *
     * @param route the final route in the discovered path
     * @param previous the map of each route to its predecessor route
     * @return the formatted route sequence
     */
    private static String getPath(Route route, HashMap<Route, Route> previous) {
        StringBuilder sb = new StringBuilder();
        while (route != null) {
            sb.insert(0, route + ", then ");
            route = previous.get(route);
        }
        return sb.substring(0, sb.length() - 7);
    }

    /**
     * Checks whether a candidate track cost can be paid by the available bag.
     *
     * @param cost the candidate route cost
     * @param bag the available track bag
     * @return {@code true} if the bag can afford the cost
     */
    private static boolean canAfford(TrackBag cost, TrackBag bag) {
        for (Route.Color color : Route.Color.values()) {
            if (color == Route.Color.X) {
                if (bag.getTotalTracks() >= cost.getTotalTracks()) {
                    continue;
                } else {
                    return false;
                }
            }
            if (cost.getTrackCount(color) > bag.getTrackCount(color)) {
                return false;
            }
        }
        return true;
    }
}
