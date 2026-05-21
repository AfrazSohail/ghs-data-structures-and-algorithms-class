
import java.util.HashMap;

/**
 * Stores the player's available colored tracks for path calculations.
 * Written by AI for Afraz Sohail.
 *
 * @author Afraz Sohail
 */
public class TrackBag {

    /** Maps each route color to the number of tracks available. */
    private HashMap<Route.Color, Integer> tracks;
    /** Tracks the total number of pieces currently stored. */
    private int totalTracks;

    /**
     * Returns the total number of tracks in the bag.
     *
     * @return the total track count
     */
    public int getTotalTracks() {
        return totalTracks;
    }

    /** Creates an empty track bag. */
    public TrackBag() {
        tracks = new HashMap<>();
        totalTracks = 0;
    }

    /**
     * Creates a copy of another track bag.
     *
     * @param bag the bag to copy
     */
    public TrackBag(TrackBag bag) {
        this.tracks = new HashMap<>(bag.tracks);
        this.totalTracks = bag.totalTracks;
    }

    /**
     * Adds or removes tracks of a given color.
     *
     * @param color the track color to update
     * @param amount the amount to add, or negative amount to remove
     */
    public void addTracks(Route.Color color, int amount) {
        tracks.put(color, tracks.getOrDefault(color, 0) + amount);
        totalTracks += amount;
    }

    /**
     * Adds or removes tracks using a character color code.
     *
     * @param color the color character to update
     * @param amount the amount to add, or negative amount to remove
     */
    public void addTracks(char color, int amount) {
        addTracks(Route.Color.valueOf(String.valueOf(color)), amount);
    }

    /**
     * Attempts to spend tracks of a given color.
     *
     * @param color the color to use
     * @param amount the number of tracks to spend
     * @return {@code true} if enough tracks were available
     */
    public boolean useTracks(Route.Color color, int amount) {
        if (tracks.getOrDefault(color, 0) < amount) {
            return false;
        }
        tracks.put(color, tracks.get(color) - amount);
        totalTracks -= amount;
        return true;
    }

    /**
     * Returns the count stored for a color.
     *
     * @param color the color to check
     * @return the number of tracks for that color
     */
    public int getTrackCount(Route.Color color) {
        return tracks.getOrDefault(color, 0);
    }

    /**
     * Returns the count stored for a character color code.
     *
     * @param color the character code for the color
     * @return the number of tracks for that color
     */
    public int getTrackCount(char color) {
        return getTrackCount(Route.Color.valueOf(String.valueOf(color)));
    }

    /**
     * Creates and returns a duplicate of this bag.
     *
     * @return a copied track bag
     */
    public TrackBag copy() {
        return new TrackBag(this);
    }
}
