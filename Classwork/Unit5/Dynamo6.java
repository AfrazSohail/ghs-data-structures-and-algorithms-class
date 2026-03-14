package Classwork.Unit5;

import java.util.Arrays;

public class Dynamo6 {
    public static void main(String args[]) {
        int[] denoms = { 8, 11 };
        System.out.println(cointCount(denoms, 15));
    }

    public static int cointCount(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, amount + 1);
        memo[0] = 0;
        return coinH(coins, amount, memo);
    }

    public static int coinH(int[] coins, int amount, int[] memo) {
        for (int coin : coins) {
            for (int i = coin; i < memo.length; i++) {
                memo[i] = Math.min(memo[i], memo[i - coin] + 1);
            }
        }
        return memo[amount] > amount ? -1 : memo[amount];
    }
}
