
import java.util.Scanner;

/**
 * Provides the console entry point for running the TrainGame program.
 * Written by AI for Afraz Sohail.
 *
 * @author Afraz Sohail
 */
public class TicketDriver {

    /**
     * Starts the program, loads data, and prompts the user for route requests.
     *
     * @param args command-line arguments, unused by this program
     */
    public static void main(String[] args) {
        TicketToRide.loadFiles();
        TicketToRide.displayData();

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter start city: ");
            String startCity = sc.nextLine().trim();
            System.out.print("Enter end city: ");
            String endCity = sc.nextLine().trim();
            City start = TicketToRide.getCityByName(startCity);
            City end = TicketToRide.getCityByName(endCity);
            if (start == null || end == null) {
                System.out.println("One or both cities not found!");
                return;
            }
            System.out.println("Shortest route from " + start + " to " + end + " without constraints:");
            TicketToRide.dijkstraCity(start, end);
            System.out.println("\nShortest route from " + start + " to " + end + " with constraints:");
            TicketToRide.dijkstraRoute(start, end);
        }
    }
}
