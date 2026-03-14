package Classwork.Unit5;

import java.util.Arrays;

public class Problem_8_1 {
    public static void main(String args[]) {
        System.out.println(minPerfectSquares(69));
    }

    public static int minPerfectSquares(int target) {
        int numOfSquares = (int) Math.floor(Math.sqrt(target));
        int[] perfectSquares = new int[numOfSquares];
        for (int i = 1; i <= numOfSquares; i++)
            perfectSquares[i - 1] = i * i;

        int[] arr = new int[target + 1];
        Arrays.fill(arr, target + 1);
        arr[0] = 0;

        for (int perfSquare : perfectSquares)
            for (int i = perfSquare; i <= target; i++)
                arr[i] = Math.min(arr[i - perfSquare] + 1, arr[i]);
        return arr[arr.length - 1];
    }
}
