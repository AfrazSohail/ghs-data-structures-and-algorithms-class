import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Welcome to the War card game!");
        System.out.print("Enter number of cards per suit: ");
        int numCards = sc.nextInt();

        War game = new War(numCards);
        boolean gameOver = false;

        while (!gameOver) {
            gameOver = !game.playRound();
        }
    }

    // Add text saying that there is a second war.

}
