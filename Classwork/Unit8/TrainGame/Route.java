
import java.util.ArrayList;

/**
 * Represents a connection between two cities in the TrainGame map.
 * Written by AI for Afraz Sohail.
 *
 * @author Afraz Sohail
 */
public class Route {

    /** Stores the two cities connected by this route. */
    private ArrayList<City> cities = new ArrayList<>();
    /** Stores the number of track segments required for the route. */
    private int distance;

    /** Defines the available route colors used by the game data. */
    public static enum Color {
        /** Wild or flexible track color. */
        X, P, B, G, Y, O, R, W, K
    }

    /** Stores the color requirement for this route. */
    private Color color;

    /**
     * Builds a route between two cities.
     *
     * @param city1 the first city
     * @param city2 the second city
     * @param distance the route distance
     * @param color the character code for the route color
     */
    public Route(City city1, City city2, int distance, char color) {
        this.cities.add(city1);
        this.cities.add(city2);
        cities.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
        this.distance = distance;
        this.color = Color.valueOf(String.valueOf(color));
    }

    /**
     * Returns the cities connected by this route.
     *
     * @return the city list for this route
     */
    public ArrayList<City> getCities() {
        return cities;
    }

    /**
     * Returns the city on the opposite end of the route.
     *
     * @param city one city on the route
     * @return the other city on the route
     * @throws IllegalArgumentException if the provided city is not on the route
     */
    public City getNeighbour(City city) {
        if (cities.get(0).equals(city)) {
            return cities.get(1);
        } else if (cities.get(1).equals(city)) {
            return cities.get(0);
        } else {
            throw new IllegalArgumentException("City not found in route");
        }
    }

    /**
     * Checks whether the route contains a given city.
     *
     * @param city1 the city to test
     * @return {@code true} if the route contains the city
     */
    public boolean containsCity(City city1) {
        return cities.contains(city1);
    }

    /**
     * Returns the route distance.
     *
     * @return the number of track segments required
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Returns the route color.
     *
     * @return the route color enum value
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns a formatted description of the route.
     *
     * @return the route as text
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(cities.get(0)).append("--").append(distance).append(color).append("->").append(cities.get(1));
        return sb.toString();
    }

    /**
     * Computes a hash code for the route.
     *
     * @return the route hash code
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + cities.hashCode();
        hash = 31 * hash + distance;
        hash = 31 * hash + color.hashCode();
        return hash;
    }

    /**
     * Compares this route to another object.
     *
     * @param obj the object to compare against
     * @return {@code true} if both routes contain the same data
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Route other = (Route) obj;
        return distance == other.distance && color == other.color && cities.equals(other.cities);
    }
}
