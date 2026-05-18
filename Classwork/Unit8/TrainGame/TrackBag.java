
import java.util.HashMap;

public class TrackBag {

    private HashMap<Route.Color, Integer> tracks;

    public TrackBag() {
        tracks = new HashMap<>();
    }

    public void addTracks(Route.Color color, int amount) {
        tracks.put(color, tracks.getOrDefault(color, 0) + amount);
    }

    public void addTracks(char color, int amount) {
        addTracks(Route.Color.valueOf(String.valueOf(color)), amount);
    }

    public boolean useTracks(Route.Color color, int amount) {
        if (tracks.getOrDefault(color, 0) < amount) {
            return false;
        }
        tracks.put(color, tracks.get(color) - amount);
        return true;
    }

    public int getTrackCount(Route.Color color) {
        return tracks.getOrDefault(color, 0);
    }

    public int getTrackCount(char color) {
        return getTrackCount(Route.Color.valueOf(String.valueOf(color)));
    }

    public TrackBag copy() {
        TrackBag copy = new TrackBag();
        for (Route.Color color : tracks.keySet()) {
            copy.addTracks(color, tracks.get(color));
        }
        return copy;
    }
}
