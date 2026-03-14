package Classwork.Unit5;

import java.util.HashMap;

public class Dynamo2 {
    static HashMap<Integer, Integer> coinCount = new HashMap<>();

    public static void main(String args[]) {
        // int[] denominations = { 1, 5, 10 };
        // int amount = 30;
    }

    public static int makeChange(int[] coins, int amount) {
        if (amount == 0)
            return 1;
        if (amount < 0)
            return 0;

        if (coinCount.containsKey(amount))
            return coinCount.get(amount);

        int result = 0;

        for (int i = 0; i < coins.length; i++) {
            result += makeChange(coins, amount - coins[i]);
        }

        coinCount.put(amount, result);
        return result;
    }
}
