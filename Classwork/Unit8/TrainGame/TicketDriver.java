
import java.util.Scanner;

public class TicketDriver {

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
