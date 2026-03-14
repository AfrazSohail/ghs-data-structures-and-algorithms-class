package Classwork.Unit5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SohailAfrazMidterm {
    public static void main(String args[]) {
        System.out.println(primeN(100000));

        System.out.println(longestCollatz(100000));
    }

    public static long primeN(int n) {
        List<Long> primesFound = new ArrayList<Long>();
        boolean isPrime = false;
        long potentialPrime = 1;
        for (int num = 1; num <= n;) {
            potentialPrime++;
            isPrime = true;
            for (long primeNum : primesFound) {
                if (potentialPrime % primeNum == 0) {
                    isPrime = false;
                    break;
                }
                if (primeNum > (long) Math.sqrt(potentialPrime)) {
                    // System.out.println("BREAK!");
                    break;
                }
            }
            // System.out.println("num: " + num + " isPrime: " + isPrime + " potPrime: " +
            // potentialPrime);
            if (isPrime) {
                primesFound.add((potentialPrime));
                num++;
            }
            // for (long prime : primesFound)
            // System.out.print(" " + prime);
            // System.out.println();
        }
        return potentialPrime;
    }

    public static int longestCollatz(int n) {
        HashMap<Integer, Integer> cols = new HashMap<>();
        cols.put(1, 1);
        int max = 1;
        int maxNum = 1;
        for (int i = 2; i <= n; i++) {
            int iters = 0;
            int num = i;
            while (true) {
                if (cols.containsKey(num)) {
                    iters += cols.get(num);
                    break;
                }
                if (num % 2 == 0) {
                    num = num / 2;
                } else {
                    num = 3 * num + 1;
                }
                iters++;
            }
            if (iters > max) {
                max = iters;
                maxNum = i;
            }
        }
        return maxNum;
    }
}
