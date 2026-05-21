
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Loads route and track data files for the TrainGame program.
 * Written by AI for Afraz Sohail.
 *
 * @author Afraz Sohail
 */
public class FileLoader {

    /**
     * Opens a file chooser and returns the selected file path.
     *
     * @param title the chooser dialog title
     * @return the absolute file path, or {@code null} if no file is selected
     */
    private static String loadFileGUI(String title) {
        String userDir = System.getProperty("user.dir");
        javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser(userDir);
        fileChooser.setDialogTitle(title);
        int result = fileChooser.showOpenDialog(null);
        if (result == javax.swing.JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile.getAbsolutePath();
        } else {
            System.out.println("Please choose a file!");
            return null;
        }
    }

    /**
     * Loads route records from a CSV file into the city set and adjacency graph.
     *
     * @param cities the set of known cities to populate
     * @param graph the adjacency map that stores routes by city
     */
    public static void loadRoutes(HashSet<City> cities, HashMap<City, HashSet<Route>> graph) {
        String filePath = loadFileGUI("Select Routes File");
        if (filePath == null) {
            return;
        }
        File file = new File(filePath);
        try (Scanner sc = new Scanner(file)) {
            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String data[] = line.split(",");
                String city1 = data[0].trim();
                City c1 = new City(city1);
                String city2 = data[1].trim();
                City c2 = new City(city2);
                int distance = Integer.parseInt(data[2].trim());
                char color = data[3].trim().charAt(0);
                Route route = new Route(c1, c2, distance, color);
                cities.add(c1);
                cities.add(c2);
                if (!graph.containsKey(c1)) {
                    graph.put(c1, new HashSet<>());
                }
                graph.get(c1).add(route);
                if (!graph.containsKey(c2)) {
                    graph.put(c2, new HashSet<>());
                }
                graph.get(c2).add(route);
            }
        } catch (FileNotFoundException e) {
            System.out.println("The ROUTES path set in FileLoad.java is incorrect!");
        }
    }

    /**
     * Loads track counts from a CSV file into the supplied track bag.
     *
     * @param tracks the bag to populate with track counts
     */
    public static void loadTracks(TrackBag tracks) {
        String filePath = loadFileGUI("Select Tracks File");
        if (filePath == null) {
            return;
        }
        File file = new File(filePath);
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String data[] = line.split(",");
                char color = data[0].trim().charAt(0);
                int count = Integer.parseInt(data[1].trim());
                tracks.addTracks(color, count);
            }
        } catch (FileNotFoundException e) {
            System.out.println("The TRACKS path set in FileLoad.java is incorrect!");
        }
    }
}
