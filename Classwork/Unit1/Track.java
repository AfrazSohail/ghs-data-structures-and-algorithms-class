
/**
 * Represents a music track with a title, artist, duration, and a reference to
 * the next track.
 * Useful for creating linked lists of tracks.
 *
 * Fields:
 * <ul>
 * <li>title - The title of the track.</li>
 * <li>artist - The artist of the track.</li>
 * <li>duration - The duration of the track in seconds.</li>
 * <li>next - Reference to the next Track in the list.</li>
 * </ul>
 *
 * Constructors:
 * <ul>
 * <li>{@code Track(String title, String artist, int duration, Track next)} -
 * Creates a track with specified title, artist, duration, and next
 * reference.</li>
 * <li>{@code Track(String title, int duration)} - Creates a track with
 * specified title and duration, artist set to "Unknown", and next reference as
 * null.</li>
 * </ul>
 *
 * Methods:
 * <ul>
 * <li>{@code toString()} - Returns a string representation of the track.</li>
 * </ul>
 */
public class Track {

    String title;
    String artist;
    int duration;
    Track next;

    public Track(String title, String artist, int duration, Track next) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.next = next;
    }

    public Track(String title, int duration) {
        this(title, "Unknown", duration, null);
    }

    @Override
    public String toString() {
        return "Track [title=" + title + ", artist=" + artist + ", duration=" + duration + "]";
    }

    public boolean equals(Track other) {
        if (other == null) {
            return false;
        }

        return this.title.equals(other.title);
    }
}
