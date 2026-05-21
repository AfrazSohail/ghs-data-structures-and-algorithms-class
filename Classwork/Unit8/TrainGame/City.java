/**
 * Represents a city node used in the TrainGame graph.
 * Written by AI for Afraz Sohail.
 *
 * @author Afraz Sohail
 */
public class City {

    /** Stores the display name of the city. */
    private String name;

    /**
     * Creates a city with a trimmed name.
     *
     * @param name the raw city name
     */
    public City(String name) {
        this.name = name.trim();
    }

    /**
     * Returns the city's name.
     *
     * @return the city name
     */
    public String getName() {
        return name;
    }

    /**
     * Computes a hash code based on the city name.
     *
     * @return the hash code for this city
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Compares this city to another object.
     *
     * @param obj the object to compare against
     * @return {@code true} when both objects represent the same city name
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        City other = (City) obj;
        return name.equals(other.name);
    }

    /**
     * Returns the city name as the string form of the object.
     *
     * @return the city name
     */
    @Override
    public String toString() {
        return name;
    }
}
