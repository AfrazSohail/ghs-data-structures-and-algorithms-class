package Classwork.Unit5;

public class Dynamo7 {
    public static void main(String args[]) {

    }

    public static double knapsack(int capacity, int[] itemWeights, double[] itemValues) {
        double[][] table = new double[itemWeights.length][capacity + 1];

        for (int i = 1; i <= itemWeights.length; i++)
            for (int w = 1; w <= capacity; w++) {
                if (itemWeights[i - 1] <= w) {
                    table[i][w] = Math.max(table[i - 1][w], itemValues[i - 1] + table[i - 1][w - itemWeights[i - 1]]);
                }
            }
        return table[itemWeights.length][capacity];
    }
}
