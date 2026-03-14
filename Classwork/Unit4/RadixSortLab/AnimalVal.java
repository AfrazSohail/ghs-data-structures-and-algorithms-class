/**
 * Represents an animal with its name and weight for radix sort visualization.
 *
 * @author Afraz Sohail
 * @version 1.0
 * Written by AI
 */
package Classwork.Unit4.RadixSortLab;

public class AnimalVal {
    private String animal;

    /**
     * Gets the name of the animal.
     *
     * @return the animal's name
     */
    public String getName() {
        return this.animal;
    }

    private int weight;

    /**
     * Gets the weight of the animal.
     *
     * @return the animal's weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Constructs an AnimalVal with the specified name and weight.
     *
     * @param animal the name of the animal
     * @param weight the weight of the animal
     */
    public AnimalVal(String animal, int weight) {
        this.animal = animal;
        this.weight = weight;
    }
}
