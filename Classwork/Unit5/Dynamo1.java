package Classwork.Unit5;

import java.util.HashMap;

public class Dynamo1 {
    static HashMap<Integer, Integer> memo = new HashMap<Integer, Integer>();

    public static void main(String args[]) {
        System.out.println(fibBad(6));
        System.out.println(fibBottom(6));
    }

    public static int fibMemo(int n) {
        if (n <= 2)
            return 1;
        if (memo.containsKey(n))
            return memo.get(n);

        int result = fibMemo(n - 1) + fibMemo(n - 2);
        memo.put(n, result);
        return result;
    }

    public static int fibBottom(int n) {
        int cache[] = new int[n + 1];
        cache[1] = 1;
        cache[2] = 1;

        for (int i = 3; i <= n; i++) {
            cache[i] = cache[i - 1] + cache[i - 2];
        }
        return cache[n];
    }

    public static int fibBad(int n) {
        if (n <= 2) {
            return 1;
        }
        return fibBad(n - 1) + fibBad(n - 2);
    }
}
