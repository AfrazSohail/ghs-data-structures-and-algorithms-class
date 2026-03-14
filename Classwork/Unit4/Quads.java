package Classwork.Unit4;

import java.util.Arrays;

public class Quads {
    public static void main(String args[]) {
        int[] arr = new int[4];
        for (int i = 0; i < arr.length; i++)
            arr[i] = (int) (Math.random() * arr.length);
        System.out.println(Arrays.toString(arr));
    }

    public static void recIns(int[] arr) {
        recInsH(arr, 1);
    }

    public static void recInsH(int[] arr, int i) {
        if (i == arr.length)
            return;
        moveUp(arr, i, arr[i]);
        recInsH(arr, i + 1);
    }

    public static void moveUp(int[] arr, int i, int elem) {
        if (i <= 0) {
            arr[0] = elem;
            return;
        }
        if (arr[i] < arr[i - 1]) {
            arr[i] = arr[i - 1];
            moveUp(arr, i - 1, elem);
        }
        arr[i] = elem;
    }

    public static void recBub(int[] arr) {
        recBubH(arr, arr.length - 1, true);
    }

    public static void recBubH(int[] arr, int bottom, boolean hasSwap) {
        if (bottom < 0 || !hasSwap)
            return;
        hasSwap = swapper(arr, 0, bottom, false);
        recBubH(arr, bottom, hasSwap);
    }

    public static boolean swapper(int[] arr, int i, int bottom, boolean hasSwap) {
        if (i >= bottom)
            return hasSwap;
        if (arr[i] > arr[i + 1]) {
            int temp = arr[i];
            arr[i] = arr[i + 1];
            arr[i + 1] = temp;
            hasSwap = true;
        }
        return swapper(arr, i + 1, bottom, hasSwap);
    }

    public static void recSel(int[] arr) {
        recSelH(arr, 0);
    }

    public static void recSelH(int[] arr, int i) {
        if (i >= arr.length)
            return;
        int minSpot = findSmallest(arr, i);
        int temp = arr[i];
        arr[i] = arr[minSpot];
        arr[minSpot] = temp;
        recSelH(arr, i + 1);
    }

    public static int findSmallest(int[] arr, int i) {
        if (i == arr.length)
            return i;
        int minIndex = findSmallest(arr, i + 1);
        return arr[minIndex] < arr[i] ? minIndex : i;
    }
}
