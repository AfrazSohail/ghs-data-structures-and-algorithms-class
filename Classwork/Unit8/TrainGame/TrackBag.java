
import java.util.HashMap;

public class TrackBag {

    private HashMap<Route.Color, Integer> tracks;
    private int totalTracks;

    public int getTotalTracks() {
        return totalTracks;
    }

    public TrackBag() {
        tracks = new HashMap<>();
        totalTracks = 0;
    }

    public TrackBag(TrackBag bag) {
        this.tracks = new HashMap<>(bag.tracks);
        this.totalTracks = bag.totalTracks;
    }

    public void addTracks(Route.Color color, int amount) {
        tracks.put(color, tracks.getOrDefault(color, 0) + amount);
        totalTracks += amount;
    }

    public void addTracks(char color, int amount) {
        addTracks(Route.Color.valueOf(String.valueOf(color)), amount);
    }

    public boolean useTracks(Route.Color color, int amount) {
        if (tracks.getOrDefault(color, 0) < amount) {
            return false;
        }
        tracks.put(color, tracks.get(color) - amount);
        totalTracks -= amount;
        return true;
    }

    public int getTrackCount(Route.Color color) {
        return tracks.getOrDefault(color, 0);
    }

    public int getTrackCount(char color) {
        return getTrackCount(Route.Color.valueOf(String.valueOf(color)));
    }

    public TrackBag copy() {
        return new TrackBag(this);
    }
}
