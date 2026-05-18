
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
            System.out.println("Shortest path from " + start + " to " + end + ":");
            TicketToRide.dijkstraSimple(start, end);
            TicketToRide.dijkstraComplex(start, end);
        }
    }
}
