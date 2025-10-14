package WeBareBears;

/**
 * Represents a Bear with a name, weight, and species identifier.
 * <p>
 * This class provides a constructor to initialize the bear's attributes and
 * overrides the {@code toString()} method for a readable representation.
 * </p>
 *
 * @author sohaila2
 */

public class Bear {
    String name;
    double weight;
    int species;

    public Bear(String name, double weight, int species) {
        this.name = name;
        this.weight = weight;
        this.species = species;
    }

    @Override
    public String toString() {
        return "Bear [name=" + name + ", weight=" + weight + ", species=" + species + "]";
    }

}
