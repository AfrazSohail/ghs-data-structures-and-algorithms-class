package Classwork.Unit5;

import java.util.HashMap;

public class Dynamo5 {
    public static HashMap<Integer, Long> memo = new HashMap<Integer, Long>();
    public static int dynC = 0;
    public static int recC = 0;

    public static void main(String args[]) {
        for (int i = 0; i <= 3; i++)
            System.out.print((i > 0 ? ", " : "") + catalanRec(i));
        System.out.println();
        memo.put(0, 1L);
        for (int i = 0; i <= 10; i++)
            System.out.print((i > 0 ? ", " : "") + catalanDyn(i));
        System.out.println();
        System.out.println("Dynamic: " + dynC + "\tRecursive: " + recC);
    }

    public static long catalanDyn(int n) {
        dynC++;
        if (memo.containsKey(n))
            return memo.get(n);
        long cat = 0;
        for (int i = 1; i <= n; i++)
            cat += catalanDyn(i - 1) * catalanDyn(n - i);
        memo.put(n, cat);
        return cat;
    }

    public static long catalanRec(int n) {
        recC++;
        if (n == 0)
            return 1;
        long cat = 0;
        for (int i = 1; i <= n; i++)
            cat += catalanRec(i - 1) * catalanRec(n - i);
        return cat;
    }
}
