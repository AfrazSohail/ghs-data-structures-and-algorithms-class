import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class FileLoader {

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

    public static void loadTracks(HashMap<Route.Color, Integer> tracks) {
        String filePath = loadFileGUI("Select Tracks File");
        if (filePath == null) {
            return;
        }
        File file = new File(filePath);
        try (Scanner sc = new Scanner(file)) {
            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String data[] = line.split(",");
                char color = data[0].trim().charAt(0);
                int count = Integer.parseInt(data[1].trim());
                tracks.put(Route.Color.valueOf(String.valueOf(color)), count);
            }
        } catch (FileNotFoundException e) {
            System.out.println("The TRACKS path set in FileLoad.java is incorrect!");
        }
    }
}
