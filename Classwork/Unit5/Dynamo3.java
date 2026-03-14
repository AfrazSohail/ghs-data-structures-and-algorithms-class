package Classwork.Unit5;

import java.util.Arrays;

public class Dynamo3 {
    public static void main(String args[]) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++)
            arr[i] = (int) (Math.random() * arr.length);

        System.out.print("Array: " + Arrays.toString(arr) + "\n");
        System.out.println(longestIncSub(arr));
    }

    public static int longestIncSub(int[] arr) {
        int[] memo = new int[arr.length];
        int max = -1;
        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j < i; j++)
                if (arr[j] < arr[i]) {
                    memo[i] = Math.max(memo[i], memo[j] + 1);
                    max = Math.max(max, memo[i]);
                }
        return max + 1;
    }
}
