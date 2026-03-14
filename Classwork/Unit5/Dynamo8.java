package Classwork.Unit5;

public class Dynamo8 {
    public static void main(String[] args) {
        System.out.println(rollingBones(7, 18));
    }

    public static int rollingBones(int dice, int total) {
        int[][] table = new int[dice + 1][total + 1];
        table[0][0] = 1;
        for (int numOfDie = 1; numOfDie <= dice; numOfDie++) {
            for (int subSum = 0; subSum <= total; subSum++) {
                int sum = 0;
                for (int i = 1; i <= 6; i++)
                    sum += (subSum - i < 0) ? 0 : table[numOfDie - 1][subSum - i];
                table[numOfDie][subSum] = sum;
            }
        }
        return table[dice][total];

        // MAKE A MUSEAM OF ARTIFACTS/PROJECTS ON GITHUB, LINK TO RESUME
        // NETWORKING --- COLD REACH ON LINKED IN --- IF YOU REACH OUT TO PEOPLE WHO
        // WERE NOT ONLY IN YOUR COLLEGE BUT ALSO CLUB-THEN MORE RATE OF GETING BACK TO
        // YOU
        // INDEX FUND OR HIGH FUNDING ACCOUNT --- INSTEAD OF STOCKS!
    }
}
