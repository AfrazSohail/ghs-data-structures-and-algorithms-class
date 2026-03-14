package Classwork.Unit4;

import java.util.Arrays;

public class Chocula {
    public static void main(String args[]) {
        int[] numbers = new int[20];
        for (int i = 0; i < numbers.length; i++)
            numbers[i] = (int) (Math.random() * 100);
        System.out.println(Arrays.toString(numbers));
        countSortDigi(numbers, 1);
        System.out.println(Arrays.toString(numbers));
        countSortDigi(numbers, 2);
        System.out.println(Arrays.toString(numbers));
    }

    public static void countSort(int[] arr) {
        int[] counts = new int[10];
        for (int elem : arr)
            counts[elem]++;
        // Make it cumulative
        for (int i = 1; i < counts.length; i++)
            counts[i] += counts[i - 1];

        int aux[] = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--)
            aux[--counts[arr[i]]] = arr[i];

        for (int i = 0; i < arr.length; i++) // copies from aux to og
            arr[i] = aux[i];
    }

    public static void countSortDigi(int[] arr, int digit) {
        int[] counts = new int[10];

        for (int elem : arr) {
            int d = ((int) (elem / Math.pow(10, digit - 1))) % 10;
            counts[d]++;
        }

        // Make it cumulative
        for (int i = 1; i < counts.length; i++)
            counts[i] += counts[i - 1];

        int aux[] = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int d = ((int) (arr[i] / Math.pow(10, digit - 1))) % 10;
            aux[--counts[d]] = arr[i];
        }

        for (int i = 0; i < arr.length; i++) // copies from aux to og
            arr[i] = aux[i];
    }
}
