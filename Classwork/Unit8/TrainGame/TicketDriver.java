
import java.util.Scanner;

/**
 * Driver class for the Ticket to Ride train game. Initializes the game, loads
 * game data, and provides user interface for finding shortest paths between
 * cities using the Dijkstra algorithm.
 *
 * @author Afraz Sohail
 * @version 1.0
 * @note Documentation written by insert model
 */
public class TicketDriver {

    /**
     * Main entry point for the Ticket to Ride game. Loads game data from CSV
     * files and prompts the user for start and end cities, then finds the
     * shortest path using Dijkstra's algorithm.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        TicketToRide.loadCities("Classwork\\Unit8\\TrainGame\\Routes.csv");
        TicketToRide.loadTracks("Classwork\\Unit8\\TrainGame\\Tracks.csv");
        System.out.println(TicketToRide.displayGraph());

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter start city: ");
            String start = sc.nextLine();
            System.out.print("Enter end city: ");
            String end = sc.nextLine();
            TicketToRide.dijkstraSimple(start, end);
        }
    }
}
