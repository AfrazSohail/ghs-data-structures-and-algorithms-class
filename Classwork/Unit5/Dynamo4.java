package Classwork.Unit5;

import java.util.Arrays;

public class Dynamo4 {
    public static void main(String args[]) {
        String str1 = "bananagram";
        String str2 = "ananalgram";
        System.out.println(banagram(str1, str2));
    }

    public static String banagram(String str1, String str2) {
        int[][] memo = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 1; i <= str1.length(); i++)
            for (int j = 1; j <= str2.length(); j++)
                if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    memo[i][j] = memo[i - 1][j - 1] + 1;
                else
                    memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
        String out = "";
        int left = memo[0].length - 1;
        int up = memo.length - 1;
        System.out.println(Arrays.deepToString(memo));
        while (left > 0 && up > 0) {
            if (str1.charAt(up - 1) == str2.charAt(left - 1)) {
                out = str1.charAt(up - 1) + out;
                left--;
                up--;
            } else if (memo[up - 1][left] > memo[up][left - 1])
                up--;
            else
                left--;

        }
        return out;
    }
}
