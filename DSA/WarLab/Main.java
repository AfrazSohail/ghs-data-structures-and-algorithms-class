import java.util.Scanner;

/**
 * Main entry point for the War card game application.
 * This class handles user interaction for game initialization and manages
 * the main game loop until the game concludes.
 *
 * DOCUMENTATION DONE BY AI---Claude Sonnet 4
 *
 * @author Afraz Sohail
 */
public class Main {

    /** Scanner instance for reading user input */
    static Scanner sc = new Scanner(System.in);

    /**
     * Main method that starts the War card game.
     * Prompts the user for the number of cards per suit, creates a new game,
     * and runs the game loop until completion.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {

        System.out.println("Welcome to the War card game!");
        System.out.print("Enter number of cards per suit: ");
        int numCards = sc.nextInt();

        System.out.println("Do you want to play an open game? (y/n): ");
        if (sc.next().equalsIgnoreCase("y")) {
            Dealer.setOpenGame();
        }

        War game = new War(numCards);
        boolean gameOver = false;

        while (!gameOver) {
            gameOver = !game.playRound();
        }

        System.out.println("Game over!");
    }

    // Add text saying that there is a second war.

}
