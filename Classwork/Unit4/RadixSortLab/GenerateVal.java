/**
 * Utility class for generating random AnimalVal objects within specified weight ranges.
 * Reads animal weight data from CSV and matches animals to generated weights.
 *
 * @author Afraz Sohail
 * @version 1.0
 * Written by AI
 */
package Classwork.Unit4.RadixSortLab;

import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class GenerateVal {

    /** Path to the CSV file containing animal weight ranges */
    static String PATH = "Classwork/Unit4/RadixSortLab/Data/weights.csv";

    /**
     * Generates an array of AnimalVal objects with random weights in the specified
     * range.
     *
     * @param lo    the minimum weight (inclusive)
     * @param hi    the maximum weight (exclusive)
     * @param count the number of animals to generate (clamped between 1-24)
     * @return array of randomly generated AnimalVal objects
     * @throws FileNotFoundException if the weights CSV file is not found
     */
    public static AnimalVal[] generateVals(int lo, int hi, int count) throws FileNotFoundException {
        int range = hi - lo;
        if (count < 0)
            count = 1;
        if (count > 24)
            count = 24;

        int[] arr = new int[count];
        for (int i = 0; i < arr.length; i++)
            arr[i] = (int) (Math.random() * range + lo);

        return getAnimalVals(arr);
    }

    /**
     * Converts an array of weights into AnimalVal objects by matching each weight
     * to a random animal from the CSV data that falls within that weight range.
     *
     * @param arr array of weight values
     * @return array of AnimalVal objects with matched animal names
     * @throws FileNotFoundException if the weights CSV file is not found
     */
    private static AnimalVal[] getAnimalVals(int[] arr) throws FileNotFoundException {
        AnimalVal[] animalVals = new AnimalVal[arr.length];

        for (int i = 0; i < arr.length; i++) {
            String matches[] = getAnimals(arr[i]).toArray(new String[0]);
            String animal = matches[(int) (Math.random() * matches.length)];

            animalVals[i] = new AnimalVal(animal, arr[i]);
        }

        return animalVals;
    }

    /**
     * Finds all animals from the CSV file whose weight range includes the given
     * weight.
     *
     * @param weight the weight to match
     * @return list of animal names that match the weight
     * @throws FileNotFoundException if the weights CSV file is not found
     */
    private static ArrayList<String> getAnimals(int weight) throws FileNotFoundException {
        ArrayList<String> matches = new ArrayList<String>();

        Scanner scanner = new Scanner(new File(PATH));
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");

            String animal = parts[0];
            int min = Integer.parseInt(parts[1]);
            int max = Integer.parseInt(parts[2]);

            if (weight >= min && weight <= max)
                matches.add(animal);
        }

        scanner.close();
        return matches;
    }
}
