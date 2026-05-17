
/**
 * Represents a city in the train game.
 * This class encapsulates city information including its name and provides
 * utility methods for comparison and hashing.
 *
 * @author Afraz Sohail
 * @version 1.0
 * @note Documentation written by insert model
 */
public class City {

    private final String name;

    /**
     * Constructs a City with the given name.
     *
     * @param name the name of the city
     * @param cost the cost parameter (currently unused)
     */
    public City(String name, int cost) {
        this.name = name;
    }

    /**
     * Returns the name of this city.
     *
     * @return the city name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the string representation of this city (its name).
     *
     * @return the city name as a string
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Returns the hash code of this city based on its name.
     *
     * @return the hash code of the city name
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Compares this city to another object for equality based on city name.
     *
     * @param obj the object to compare with
     * @return true if the cities have the same name, false otherwise
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
}
